package com.ruoyi.dylan.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dylan.bo.DylanLiuliBo;
import com.ruoyi.dylan.bo.DylanQueryBo;
import com.ruoyi.dylan.domain.*;
import com.ruoyi.dylan.dto.LiuliInfoDto;
import com.ruoyi.dylan.dto.LiuliListDto;
import com.ruoyi.dylan.service.*;
import com.ruoyi.dylan.utils.CommonUtils;
import com.ruoyi.dylan.vo.DylanLiuliPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.ruoyi.dylan.mapper.DylanLiuliMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 琉璃-内容Service业务层处理
 * 
 * @author dylan
 * @date 2024-03-17
 */
@Service
public class DylanLiuliServiceImpl extends ServiceImpl<DylanLiuliMapper, DylanLiuli> implements IDylanLiuliService
{
    @Autowired
    private DylanLiuliMapper dylanLiuliMapper;

    @Autowired
    private IDylanTagService dylanTagService;

    @Autowired
    private IDylanCatagoryService dylanCatagoryService;

    @Autowired
    private IDylanLiuliTagService dylanLiuliTagService;

    @Autowired
    private IDylanLiuliAnnexService dylanLiuliAnnexService;

    @Autowired
    private IDylanAnnexService dylanAnnexService;

    @Value("${myScrap.host}")
    private String scrapHost;

    /**
     * 查询琉璃-内容
     * 
     * @param id 琉璃-内容主键
     * @return 琉璃-内容
     */
    @Override
    public DylanLiuli selectDylanLiuliById(Long id)
    {
        return dylanLiuliMapper.selectDylanLiuliById(id);
    }

    /**
     * 查询琉璃-内容列表
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 琉璃-内容
     */
    @Override
    public List<DylanLiuli> selectDylanLiuliList(DylanLiuliBo dylanLiuli)
    {
        // 设置相关查询条件
        if (StringUtils.isNotBlank(dylanLiuli.getLiuliCatIds())){
            List<Long> collect = Arrays.asList(dylanLiuli.getLiuliCatIds().split(",")).stream().map(Long::parseLong).distinct().collect(Collectors.toList());
            if (ObjectUtils.isNotEmpty(collect)){
                dylanLiuli.setLiuliCatIdList(collect);
            }
        }
        if (StringUtils.isNotBlank(dylanLiuli.getLiuliTagIds())){
            List<Long> collect = Arrays.asList(dylanLiuli.getLiuliTagIds().split(",")).stream().map(Long::parseLong).distinct().collect(Collectors.toList());
            if (ObjectUtils.isNotEmpty(collect)){
                dylanLiuli.setLiuliTagIdList(collect);
            }
        }
        return dylanLiuliMapper.selectDylanLiuliList(dylanLiuli);
    }

    /**
     * 新增琉璃-内容
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 结果
     */
    @Override
    public int insertDylanLiuli(DylanLiuli dylanLiuli)
    {
        dylanLiuli.setCreateTime(DateUtils.getNowDate());
        dylanLiuli.setBtLink(CommonUtils.fixBtLink(dylanLiuli.getBtLink()));
        return dylanLiuliMapper.insertDylanLiuli(dylanLiuli);
    }

    /**
     * 修改琉璃-内容
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 结果
     */
    @Override
    public int updateDylanLiuli(DylanLiuli dylanLiuli)
    {
        dylanLiuli.setUpdateTime(DateUtils.getNowDate());
        dylanLiuli.setBtLink(CommonUtils.fixBtLink(dylanLiuli.getBtLink()));
        return dylanLiuliMapper.updateDylanLiuli(dylanLiuli);
    }

    /**
     * 批量删除琉璃-内容
     * 
     * @param ids 需要删除的琉璃-内容主键
     * @return 结果
     */
    @Override
    public int deleteDylanLiuliByIds(Long[] ids)
    {
        return dylanLiuliMapper.deleteDylanLiuliByIds(ids);
    }

    /**
     * 删除琉璃-内容信息
     * 
     * @param id 琉璃-内容主键
     * @return 结果
     */
    @Override
    public int deleteDylanLiuliById(Long id)
    {
        return dylanLiuliMapper.deleteDylanLiuliById(id);
    }

    /**
     * 每日同步琉璃社内容
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void syncLiuliContent(String mainLink) {
        List<DylanLiuli> list = new ArrayList<>();
        // 获取今天最新的内容
        List<LiuliListDto> liuliList = CommonUtils.getLiuliList(scrapHost, mainLink);
        if (ObjectUtils.isNotEmpty(liuliList)) {
            // 查询是否已经存在
            List<String> titles = liuliList.stream().filter(val -> StringUtils.isNotBlank(val.getTitle())).map(LiuliListDto::getTitle).distinct().collect(Collectors.toList());
            List<DylanLiuli> existList = list(new QueryWrapper<DylanLiuli>().lambda()
                    .in(DylanLiuli::getLiuliTitle, titles));
            if (ObjectUtils.isNotEmpty(existList)){
                if (existList.size() != liuliList.size()){
                    List<String> collect = existList.stream().map(DylanLiuli::getLiuliTitle).collect(Collectors.toList());
                    liuliList = liuliList.stream().filter(val -> !collect.contains(val.getTitle())).collect(Collectors.toUnmodifiableList());
                }else {
                    return;
                }
            }
            // 进行类型初始化
            List<DylanCatagory> catagories = dylanCatagoryService.list();
            catagories = initCatagories(catagories, liuliList);
            // 进行标签初始化
            List<DylanTag> tags = dylanTagService.list();
            tags = initTags(tags, liuliList);
            // 循环查询对应的详情
            List<DylanCatagory> finalCatagories = catagories;
            List<DylanTag> finalTags = tags;
            Random random = new Random();
            Map<String, List<String>> imgMap = new HashMap<>();
            liuliList.stream().forEach(liuli -> {
                try {
                    Thread.sleep(random.nextInt(11));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                String infoLink = liuli.getInfoLink();
                LiuliInfoDto liuliInfo = CommonUtils.getLiuliInfo(scrapHost, infoLink);
                if (ObjectUtils.isNotNull(liuliInfo)){
                    DylanLiuli dylanLiuli = initLiuli(liuli, liuliInfo, finalCatagories, finalTags);
                    list.add(dylanLiuli);
                }
                List<String> imgLinks = new ArrayList<>();
                imgLinks.add(liuli.getImgLink());
                imgMap.put(liuli.getTitle(), imgLinks);
            });
            // 二次筛选，去掉重复的btlink
            List<DylanLiuli> insertList = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(list)){
                List<String> btLinkList = list.stream().map(DylanLiuli::getBtLink).distinct().collect(Collectors.toUnmodifiableList());
                List<DylanLiuli> btExistLiuliList = list(new QueryWrapper<DylanLiuli>().lambda()
                        .in(DylanLiuli::getBtLink, btLinkList));
                if (ObjectUtils.isNotEmpty(btExistLiuliList)){
                    Set<String> existBtList = btExistLiuliList.stream().map(DylanLiuli::getBtLink).collect(Collectors.toSet());
                    if (ObjectUtils.isNotEmpty(existBtList)){
                        List<DylanLiuli> subList = list.stream().filter(val -> !existBtList.contains(val.getBtLink())).collect(Collectors.toList());
                        if (ObjectUtils.isNotEmpty(subList)){
                            insertList.addAll(subList);
                        }
                    }else {
                        insertList.addAll(list);
                    }
                }else {
                    insertList.addAll(list);
                }
            }
            // 执行内容更新
            if (ObjectUtils.isNotEmpty(insertList)){
                saveBatch(insertList);
                // 将标签内容整理
                List<DylanLiuliTag> liuliTags = new ArrayList<>();
                List<LiuliListDto> finalLiuliList = liuliList;
                List<DylanAnnex> annexList = new ArrayList<>();
                List<DylanLiuliAnnex> liuliAnnexList = new ArrayList<>();
                insertList.forEach(liuli ->{
                    Long id = liuli.getId();
                    LiuliListDto liuliListDto = finalLiuliList.stream().filter(val -> liuli.getLiuliTitle().equals(val.getTitle())).findFirst().orElse(null);
                    if (ObjectUtils.isNotNull(liuliListDto)){
                        // 拼接标签关联
                        List<String> dtoTags = liuliListDto.getTags();
                        if (ObjectUtils.isNotEmpty(dtoTags)){
                            List<DylanTag> subTags = finalTags.stream().filter(val -> dtoTags.contains(val.getName())).collect(Collectors.toUnmodifiableList());
                            if (ObjectUtils.isNotEmpty(subTags)){
                                subTags.forEach(subTag -> {
                                    Long tagId = subTag.getId();
                                    DylanLiuliTag liuliTag = new DylanLiuliTag();
                                    liuliTag.setLiuliId(id);
                                    liuliTag.setTagId(tagId);
                                    liuliTags.add(liuliTag);
                                });
                            }
                        }
                        // 拼接附件
                        List<String> imgLinks = imgMap.get(liuli.getLiuliTitle());
                        if (ObjectUtils.isNotEmpty(imgLinks)){
                            imgLinks.forEach(imgLink -> {
                                DylanAnnex dylanAnnex = initAnnex(imgLink);
                                annexList.add(dylanAnnex);
                            });
                        }
                    }
                });
                // 保存标签关联
                if (ObjectUtils.isNotEmpty(liuliTags)){
                    dylanLiuliTagService.saveBatch(liuliTags);
                }
                // 保存附件信息
                if (ObjectUtils.isNotEmpty(annexList)){
                    dylanAnnexService.saveBatch(annexList);
                }
                // 关联琉璃和图片信息
                insertList.forEach(liuli -> {
                    List<String> imgLinks = imgMap.get(liuli.getLiuliTitle());
                    if (ObjectUtils.isNotEmpty(imgLinks)){
                        Set<DylanAnnex> subAnnexList = annexList.stream().filter(val -> imgLinks.contains(val.getUrl())).collect(Collectors.toSet());
                        if (ObjectUtils.isNotEmpty(subAnnexList)){
                            subAnnexList.forEach(subAnnex -> {
                                Long annexId = subAnnex.getId();
                                DylanLiuliAnnex dylanLiuliAnnex = new DylanLiuliAnnex();
                                dylanLiuliAnnex.setAnnexId(annexId);
                                dylanLiuliAnnex.setLiuliId(liuli.getId());
                                liuliAnnexList.add(dylanLiuliAnnex);
                            });
                        }
                    }
                });
                // 保存琉璃-图片关联关系
                if (ObjectUtils.isNotEmpty(liuliAnnexList)){
                    dylanLiuliAnnexService.saveBatch(liuliAnnexList);
                }
            }
            // 将相关内容释放
            titles.clear();
            existList.clear();
            catagories.clear();
            tags.clear();
            finalCatagories.clear();
            finalTags.clear();
            imgMap.clear();
            insertList.clear();
        }
        list.clear();
    }

    /**
     * 初始化附件信息
     * @param imgLink
     * @return
     */
    private DylanAnnex initAnnex(String imgLink) {
        DylanAnnex dylanAnnex = new DylanAnnex();
        dylanAnnex.setUrl(imgLink);
        dylanAnnex.setName(imgLink.substring(imgLink.lastIndexOf("/") + 1));
        dylanAnnex.setDelFlag("0");
        dylanAnnex.setCreateBy("1");
        dylanAnnex.setUpdateBy("1");
        Date nowDate = DateUtils.getNowDate();
        dylanAnnex.setCreateTime(nowDate);
        dylanAnnex.setUpdateTime(nowDate);
        return dylanAnnex;
    }

    /**
     * 初始化标签
     * @param tags
     * @param liuliList
     * @return
     */
    private List<DylanTag> initTags(List<DylanTag> tags, List<LiuliListDto> liuliList) {
        Date nowDate = DateUtils.getNowDate();
        Long userId = 1L;
        Set<String> dtoTags = new HashSet<>();
        liuliList.forEach(liuliListDto -> {
            List<String> subDtoTags = liuliListDto.getTags();
            dtoTags.addAll(subDtoTags);
        });
        if (ObjectUtils.isNotEmpty(dtoTags)){
            // 筛选其中是否存在已有的标签
            List<DylanTag> insertList = new ArrayList<>();
            dtoTags.forEach(tag ->{
                long count = tags.stream().filter(val -> tag.equals(val.getName())).count();
                if (count == 0){
                    DylanTag dylanTag = new DylanTag();
                    dylanTag.setName(tag);
                    dylanTag.setDelFlag("0");
                    dylanTag.setCreateTime(nowDate);
                    dylanTag.setCreateBy(String.valueOf(userId));
                    dylanTag.setUpdateTime(nowDate);
                    dylanTag.setUpdateBy(String.valueOf(userId));
                    insertList.add(dylanTag);
                }
            });
            if (ObjectUtils.isNotEmpty(insertList)){
                // 执行类型新增操作
                dylanTagService.saveBatch(insertList);
                tags.addAll(insertList);
            }
        }
        return tags;
    }

    /**
     * 初始化类型
     * @param catagories
     * @param liuliList
     * @return
     */
    private List<DylanCatagory> initCatagories(List<DylanCatagory> catagories, List<LiuliListDto> liuliList) {
        Date nowDate = DateUtils.getNowDate();
        Long userId = 1L;
        Set<String> catList = liuliList.stream().filter(val -> StringUtils.isNotBlank(val.getCat())).map(LiuliListDto::getCat).collect(Collectors.toSet());
        if (ObjectUtils.isNotEmpty(catList)){
            // 筛选其中是否存在已有的类型
            List<DylanCatagory> insertList = new ArrayList<>();
            catList.forEach(cat ->{
                long count = catagories.stream().filter(val -> cat.equals(val.getName())).count();
                if (count == 0){
                    DylanCatagory dylanCatagory = new DylanCatagory();
                    dylanCatagory.setName(cat);
                    dylanCatagory.setDelFlag("0");
                    dylanCatagory.setCreateTime(nowDate);
                    dylanCatagory.setCreateBy(String.valueOf(userId));
                    dylanCatagory.setUpdateTime(nowDate);
                    dylanCatagory.setUpdateBy(String.valueOf(userId));
                    insertList.add(dylanCatagory);
                }
            });
            if (ObjectUtils.isNotEmpty(insertList)){
                // 执行类型新增操作
                dylanCatagoryService.saveBatch(insertList);
                catagories.addAll(insertList);
            }
        }
        return catagories;
    }

    /**
     * 初始化琉璃内容
     * @param liuli
     * @param liuliInfo
     * @return
     */
    private DylanLiuli initLiuli(LiuliListDto liuli, LiuliInfoDto liuliInfo, List<DylanCatagory> catagories, List<DylanTag> tags) {
        Date nowDate = DateUtils.getNowDate();
        Long userId = 1L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Date publishTime = null;
        try {
            publishTime = sdf.parse(liuli.getPublishTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        DylanLiuli dylanLiuli = new DylanLiuli();
        dylanLiuli.setLiuliLink(liuli.getInfoLink());
        dylanLiuli.setLiuliTitle(liuli.getTitle());
        dylanLiuli.setSubContent(liuli.getDesc());
        dylanLiuli.setPublishAuthor(liuli.getAuthor());
        dylanLiuli.setPublishTime(publishTime);
        if (StringUtils.isNotBlank(liuli.getCat())){
            dylanLiuli.setLiuliCat(catagories.stream().filter(val -> liuli.getCat().equals(val.getName())).map(DylanCatagory::getId).findFirst().orElse(null));
        }
        dylanLiuli.setContent(liuliInfo.getName());
        dylanLiuli.setBtLink(CommonUtils.fixBtLink(liuliInfo.getBtLink()));
        dylanLiuli.setCreateTime(nowDate);
        dylanLiuli.setUpdateTime(nowDate);
        dylanLiuli.setCreateBy(String.valueOf(userId));
        dylanLiuli.setUpdateBy(String.valueOf(userId));
        return dylanLiuli;
    }

    @Override
    public List<DylanLiuliPageVo> generateVo(List<DylanLiuli> list) {
        List<DylanLiuliPageVo> vos = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)) {
            List<Long> catIdList = list.stream()
                    .filter(val -> ObjectUtils.isNotNull(val.getLiuliCat()))
                    .map(DylanLiuli::getLiuliCat)
                    .distinct()
                    .collect(Collectors.toList());
            List<Long> idList = list.stream().map(DylanLiuli::getId).collect(Collectors.toList());

            Map<Long, DylanCatagory> catagoryMap = new HashMap<>();
            if (ObjectUtils.isNotEmpty(catIdList)) {
                List<DylanCatagory> dylanCatagories = dylanCatagoryService.listByIds(catIdList);
                if (ObjectUtils.isNotEmpty(dylanCatagories)) {
                    catagoryMap = dylanCatagories.stream().collect(Collectors.toMap(DylanCatagory::getId, val -> val, (a, b) -> a));
                }
            }

            Map<Long, List<Long>> liuliTagIdMap = new HashMap<>();
            Set<Long> tagIdSet = new HashSet<>();
            List<DylanLiuliTag> liuliTags = dylanLiuliTagService.list(new QueryWrapper<DylanLiuliTag>().lambda()
                    .in(DylanLiuliTag::getLiuliId, idList));
            if (ObjectUtils.isNotEmpty(liuliTags)) {
                liuliTagIdMap = liuliTags.stream().collect(Collectors.groupingBy(
                        DylanLiuliTag::getLiuliId,
                        Collectors.mapping(DylanLiuliTag::getTagId, Collectors.toList())
                ));
                tagIdSet = liuliTags.stream().map(DylanLiuliTag::getTagId).collect(Collectors.toSet());
            }

            Map<Long, String> tagNameMap = new HashMap<>();
            if (ObjectUtils.isNotEmpty(tagIdSet)) {
                List<DylanTag> dylanTags = dylanTagService.listByIds(tagIdSet);
                if (ObjectUtils.isNotEmpty(dylanTags)) {
                    tagNameMap = dylanTags.stream().collect(Collectors.toMap(DylanTag::getId, DylanTag::getName, (a, b) -> a));
                }
            }

            Map<Long, Long> liuliAnnexIdMap = new HashMap<>();
            Set<Long> annexIdSet = new HashSet<>();
            List<DylanLiuliAnnex> liuliAnnexList = dylanLiuliAnnexService.list(new QueryWrapper<DylanLiuliAnnex>().lambda()
                    .in(DylanLiuliAnnex::getLiuliId, idList));
            if (ObjectUtils.isNotEmpty(liuliAnnexList)) {
                liuliAnnexIdMap = liuliAnnexList.stream().collect(Collectors.toMap(
                        DylanLiuliAnnex::getLiuliId,
                        DylanLiuliAnnex::getAnnexId,
                        (a, b) -> a
                ));
                annexIdSet = liuliAnnexList.stream().map(DylanLiuliAnnex::getAnnexId).collect(Collectors.toSet());
            }

            Map<Long, String> annexUrlMap = new HashMap<>();
            if (ObjectUtils.isNotEmpty(annexIdSet)) {
                List<DylanAnnex> dylanAnnexList = dylanAnnexService.listByIds(annexIdSet);
                if (ObjectUtils.isNotEmpty(dylanAnnexList)) {
                    annexUrlMap = dylanAnnexList.stream().collect(Collectors.toMap(DylanAnnex::getId, DylanAnnex::getUrl, (a, b) -> a));
                }
            }

            for (DylanLiuli liuli : list) {
                DylanLiuliPageVo vo = BeanUtil.toBean(liuli, DylanLiuliPageVo.class);

                Long liuliCat = liuli.getLiuliCat();
                if (ObjectUtils.isNotNull(liuliCat)) {
                    DylanCatagory dylanCatagory = catagoryMap.get(liuliCat);
                    if (ObjectUtils.isNotNull(dylanCatagory)) {
                        vo.setCatName(dylanCatagory.getName());
                    }
                }

                List<Long> subTagIdList = liuliTagIdMap.get(liuli.getId());
                if (ObjectUtils.isNotEmpty(subTagIdList)) {
                    String tagNames = subTagIdList.stream()
                            .map(tagNameMap::get)
                            .filter(StringUtils::isNotBlank)
                            .distinct()
                            .collect(Collectors.joining(","));
                    vo.setTagNames(tagNames);
                }

                Long annexId = liuliAnnexIdMap.get(liuli.getId());
                if (ObjectUtils.isNotNull(annexId)) {
                    vo.setImgUrl(annexUrlMap.get(annexId));
                }

                vos.add(vo);
            }
        }

        return vos;
    }

    @Override
    public List<Map<String, Object>> getCommonList(DylanQueryBo bo) {
        String sql = bo.getSql();
        if (StringUtils.isBlank(sql)){
            return null;
        }
        return dylanLiuliMapper.getCommonList(sql);
    }
}
