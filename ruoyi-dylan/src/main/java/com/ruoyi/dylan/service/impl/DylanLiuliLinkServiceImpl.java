package com.ruoyi.dylan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylan.mapper.DylanLiuliLinkMapper;
import com.ruoyi.dylan.domain.DylanLiuliLink;
import com.ruoyi.dylan.service.IDylanLiuliLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * 琉璃链接Service业务层处理
 * 
 * @author dylan
 * @date 2024-05-12
 */
@Service
public class DylanLiuliLinkServiceImpl extends ServiceImpl<DylanLiuliLinkMapper,  DylanLiuliLink> implements IDylanLiuliLinkService
{
    @Autowired
    private DylanLiuliLinkMapper dylanLiuliLinkMapper;

    /**
     * 查询琉璃链接
     * 
     * @param id 琉璃链接主键
     * @return 琉璃链接
     */
    @Override
    public DylanLiuliLink selectDylanLiuliLinkById(Long id)
    {
        return dylanLiuliLinkMapper.selectDylanLiuliLinkById(id);
    }

    /**
     * 查询琉璃链接列表
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 琉璃链接
     */
    @Override
    public List<DylanLiuliLink> selectDylanLiuliLinkList(DylanLiuliLink dylanLiuliLink)
    {
        return dylanLiuliLinkMapper.selectDylanLiuliLinkList(dylanLiuliLink);
    }

    /**
     * 新增琉璃链接
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 结果
     */
    @Override
    public int insertDylanLiuliLink(DylanLiuliLink dylanLiuliLink)
    {
        dylanLiuliLink.setCreateTime(DateUtils.getNowDate());
        return dylanLiuliLinkMapper.insertDylanLiuliLink(dylanLiuliLink);
    }

    /**
     * 修改琉璃链接
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 结果
     */
    @Override
    public int updateDylanLiuliLink(DylanLiuliLink dylanLiuliLink)
    {
        dylanLiuliLink.setUpdateTime(DateUtils.getNowDate());
        return dylanLiuliLinkMapper.updateDylanLiuliLink(dylanLiuliLink);
    }

    /**
     * 批量删除琉璃链接
     * 
     * @param ids 需要删除的琉璃链接主键
     * @return 结果
     */
    @Override
    public int deleteDylanLiuliLinkByIds(Long[] ids)
    {
        return dylanLiuliLinkMapper.deleteDylanLiuliLinkByIds(ids);
    }

    /**
     * 删除琉璃链接信息
     * 
     * @param id 琉璃链接主键
     * @return 结果
     */
    @Override
    public int deleteDylanLiuliLinkById(Long id)
    {
        return dylanLiuliLinkMapper.deleteDylanLiuliLinkById(id);
    }
}
