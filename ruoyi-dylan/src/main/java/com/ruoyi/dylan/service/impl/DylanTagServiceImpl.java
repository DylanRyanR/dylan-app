package com.ruoyi.dylan.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.dylan.mapper.DylanTagMapper;
import com.ruoyi.dylan.domain.DylanTag;
import com.ruoyi.dylan.service.IDylanTagService;

/**
 * 标签Service业务层处理
 * 
 * @author dylan
 * @date 2024-05-03
 */
@Service
public class DylanTagServiceImpl extends ServiceImpl<DylanTagMapper, DylanTag> implements IDylanTagService
{
    @Autowired
    private DylanTagMapper dylanTagMapper;

    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    @Override
    public DylanTag selectDylanTagById(Long id)
    {
        return dylanTagMapper.selectDylanTagById(id);
    }

    /**
     * 查询标签列表
     * 
     * @param dylanTag 标签
     * @return 标签
     */
    @Override
    public List<DylanTag> selectDylanTagList(DylanTag dylanTag)
    {
        return dylanTagMapper.selectDylanTagList(dylanTag);
    }

    /**
     * 新增标签
     * 
     * @param dylanTag 标签
     * @return 结果
     */
    @Override
    public int insertDylanTag(DylanTag dylanTag)
    {
        dylanTag.setCreateTime(DateUtils.getNowDate());
        return dylanTagMapper.insertDylanTag(dylanTag);
    }

    /**
     * 修改标签
     * 
     * @param dylanTag 标签
     * @return 结果
     */
    @Override
    public int updateDylanTag(DylanTag dylanTag)
    {
        dylanTag.setUpdateTime(DateUtils.getNowDate());
        return dylanTagMapper.updateDylanTag(dylanTag);
    }

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的标签主键
     * @return 结果
     */
    @Override
    public int deleteDylanTagByIds(Long[] ids)
    {
        return dylanTagMapper.deleteDylanTagByIds(ids);
    }

    /**
     * 删除标签信息
     * 
     * @param id 标签主键
     * @return 结果
     */
    @Override
    public int deleteDylanTagById(Long id)
    {
        return dylanTagMapper.deleteDylanTagById(id);
    }
}
