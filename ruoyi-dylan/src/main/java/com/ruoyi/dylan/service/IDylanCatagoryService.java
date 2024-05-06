package com.ruoyi.dylan.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.dylan.domain.DylanCatagory;

/**
 * 类型Service接口
 * 
 * @author dylan
 * @date 2024-05-03
 */
public interface IDylanCatagoryService extends IService<DylanCatagory>
{
    /**
     * 查询类型
     * 
     * @param id 类型主键
     * @return 类型
     */
    public DylanCatagory selectDylanCatagoryById(Long id);

    /**
     * 查询类型列表
     * 
     * @param dylanCatagory 类型
     * @return 类型集合
     */
    public List<DylanCatagory> selectDylanCatagoryList(DylanCatagory dylanCatagory);

    /**
     * 新增类型
     * 
     * @param dylanCatagory 类型
     * @return 结果
     */
    public int insertDylanCatagory(DylanCatagory dylanCatagory);

    /**
     * 修改类型
     * 
     * @param dylanCatagory 类型
     * @return 结果
     */
    public int updateDylanCatagory(DylanCatagory dylanCatagory);

    /**
     * 批量删除类型
     * 
     * @param ids 需要删除的类型主键集合
     * @return 结果
     */
    public int deleteDylanCatagoryByIds(Long[] ids);

    /**
     * 删除类型信息
     * 
     * @param id 类型主键
     * @return 结果
     */
    public int deleteDylanCatagoryById(Long id);
}
