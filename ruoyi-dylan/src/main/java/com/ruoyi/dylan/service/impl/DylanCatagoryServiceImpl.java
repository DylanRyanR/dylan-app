package com.ruoyi.dylan.service.impl;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ruoyi.dylan.mapper.DylanCatagoryMapper;
import com.ruoyi.dylan.domain.DylanCatagory;
import com.ruoyi.dylan.service.IDylanCatagoryService;

/**
 * 类型Service业务层处理
 *
 * @author dylan
 * @date 2024-05-03
 */
@Service
public class DylanCatagoryServiceImpl extends ServiceImpl<DylanCatagoryMapper, DylanCatagory> implements IDylanCatagoryService
{
    @Autowired
    private DylanCatagoryMapper dylanCatagoryMapper;

    /**
     * 查询类型
     *
     * @param id 类型主键
     * @return 类型
     */
    @Override
    public DylanCatagory selectDylanCatagoryById(Long id)
    {
        return dylanCatagoryMapper.selectDylanCatagoryById(id);
    }

    /**
     * 查询类型列表
     *
     * @param dylanCatagory 类型
     * @return 类型
     */
    @Override
    public List<DylanCatagory> selectDylanCatagoryList(DylanCatagory dylanCatagory)
    {
        return dylanCatagoryMapper.selectDylanCatagoryList(dylanCatagory);
    }

    /**
     * 新增类型
     */
    @Override
    @CacheEvict(value = "catagories", allEntries = true)
    public int insertDylanCatagory(DylanCatagory dylanCatagory)
    {
        dylanCatagory.setCreateTime(DateUtils.getNowDate());
        return dylanCatagoryMapper.insertDylanCatagory(dylanCatagory);
    }

    /**
     * 修改类型
     */
    @Override
    @CacheEvict(value = "catagories", allEntries = true)
    public int updateDylanCatagory(DylanCatagory dylanCatagory)
    {
        dylanCatagory.setUpdateTime(DateUtils.getNowDate());
        return dylanCatagoryMapper.updateDylanCatagory(dylanCatagory);
    }

    /**
     * 批量删除类型
     */
    @Override
    @CacheEvict(value = "catagories", allEntries = true)
    public int deleteDylanCatagoryByIds(Long[] ids)
    {
        return dylanCatagoryMapper.deleteDylanCatagoryByIds(ids);
    }

    /**
     * 删除类型信息
     */
    @Override
    @CacheEvict(value = "catagories", allEntries = true)
    public int deleteDylanCatagoryById(Long id)
    {
        return dylanCatagoryMapper.deleteDylanCatagoryById(id);
    }

    @Override
    @Cacheable(value = "catagories", key = "'all'")
    public List<DylanCatagory> getAllCatagories()
    {
        return list();
    }
}
