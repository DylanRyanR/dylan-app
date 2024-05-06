package com.ruoyi.dylan.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dylan.domain.DylanCatagory;
import com.ruoyi.dylan.domain.DylanLiuliTag;
import com.ruoyi.dylan.domain.DylanTag;
import com.ruoyi.dylan.service.IDylanCatagoryService;
import com.ruoyi.dylan.service.IDylanLiuliTagService;
import com.ruoyi.dylan.service.IDylanTagService;
import com.ruoyi.dylan.utils.CommonUtils;
import com.ruoyi.dylan.vo.DylanLiuliPageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.ruoyi.dylan.mapper.DylanLiuliMapper;
import com.ruoyi.dylan.domain.DylanLiuli;
import com.ruoyi.dylan.service.IDylanLiuliService;

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

    @Value("myScrap.host")
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
    public List<DylanLiuli> selectDylanLiuliList(DylanLiuli dylanLiuli)
    {
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
    public void syncLiuliContent() {

    }

    @Override
    public List<DylanLiuliPageVo> generateVo(List<DylanLiuli> list) {
        List<DylanLiuliPageVo> vos = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(list)){
            // 获取对应的类型和标签
            List<Long> catIdList = list.stream().filter(val -> ObjectUtils.isNotNull(val.getLiuliCat())).map(DylanLiuli::getLiuliCat).distinct().collect(Collectors.toList());
            List<Long> idList = list.stream().map(DylanLiuli::getId).collect(Collectors.toList());
            List<DylanCatagory> dylanCatagories = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(catIdList)){
                dylanCatagories = dylanCatagoryService.listByIds(catIdList);
            }
            List<DylanLiuliTag> liuliTags = dylanLiuliTagService.list(new QueryWrapper<DylanLiuliTag>().lambda()
                    .in(DylanLiuliTag::getLiuliId, idList));
            List<DylanTag> dylanTags = new ArrayList<>();
            List<Long> tagIdList = new ArrayList<>();
            if (ObjectUtils.isNotEmpty(liuliTags)){
                tagIdList.addAll(liuliTags.stream().map(DylanLiuliTag::getTagId).collect(Collectors.toList()));
                if (ObjectUtils.isNotEmpty(tagIdList)){
                    dylanTags = dylanTagService.listByIds(tagIdList);
                }
            }
            List<DylanCatagory> finalDylanCatagories = dylanCatagories;
            List<DylanTag> finalDylanTags = dylanTags;
            list.forEach(liuli ->{
                DylanLiuliPageVo vo = BeanUtil.toBean(liuli, DylanLiuliPageVo.class);
                // 拼接类型
                Long liuliCat = liuli.getLiuliCat();
                if (ObjectUtils.isNotNull(liuliCat)){
                    DylanCatagory dylanCatagory = finalDylanCatagories.stream().filter(val -> liuliCat.equals(val.getId())).findFirst().orElse(null);
                    if (ObjectUtils.isNotNull(dylanCatagory)){
                        vo.setCatName(dylanCatagory.getName());
                    }
                }
                // 拼接标签
                if (ObjectUtils.isNotEmpty(liuliTags)){
                    List<DylanLiuliTag> subLiuliTagList = liuliTags.stream().filter(val -> liuli.getId().equals(val.getLiuliId())).distinct().collect(Collectors.toList());
                    if (ObjectUtils.isNotEmpty(subLiuliTagList)){
                        List<Long> subTagIdList = subLiuliTagList.stream().map(DylanLiuliTag::getTagId).distinct().collect(Collectors.toList());
                        if (ObjectUtils.isNotEmpty(subTagIdList)){
                            if (ObjectUtils.isNotEmpty(finalDylanTags)){
                                List<DylanTag> subDylanTags = finalDylanTags.stream().filter(val -> subTagIdList.contains(val.getId())).distinct().collect(Collectors.toList());
                                if (ObjectUtils.isNotEmpty(subDylanTags)){
                                    vo.setTagNames(subDylanTags.stream().map(DylanTag::getName).collect(Collectors.joining(",")));
                                }
                            }
                        }
                    }
                }
            });
        }

        return vos;
    }
}
