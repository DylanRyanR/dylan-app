package com.ruoyi.dylan.service;

import java.util.List;
import com.ruoyi.dylan.domain.DylanLiuli;

/**
 * 琉璃-内容Service接口
 * 
 * @author dylan
 * @date 2024-03-17
 */
public interface IDylanLiuliService 
{
    /**
     * 查询琉璃-内容
     * 
     * @param id 琉璃-内容主键
     * @return 琉璃-内容
     */
    public DylanLiuli selectDylanLiuliById(Long id);

    /**
     * 查询琉璃-内容列表
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 琉璃-内容集合
     */
    public List<DylanLiuli> selectDylanLiuliList(DylanLiuli dylanLiuli);

    /**
     * 新增琉璃-内容
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 结果
     */
    public int insertDylanLiuli(DylanLiuli dylanLiuli);

    /**
     * 修改琉璃-内容
     * 
     * @param dylanLiuli 琉璃-内容
     * @return 结果
     */
    public int updateDylanLiuli(DylanLiuli dylanLiuli);

    /**
     * 批量删除琉璃-内容
     * 
     * @param ids 需要删除的琉璃-内容主键集合
     * @return 结果
     */
    public int deleteDylanLiuliByIds(Long[] ids);

    /**
     * 删除琉璃-内容信息
     * 
     * @param id 琉璃-内容主键
     * @return 结果
     */
    public int deleteDylanLiuliById(Long id);
}
