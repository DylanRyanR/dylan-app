package com.ruoyi.dylan.mapper;

import java.util.List;
import com.ruoyi.dylan.domain.DylanLiuli;

/**
 * 琉璃-内容Mapper接口
 * 
 * @author dylan
 * @date 2024-03-17
 */
public interface DylanLiuliMapper 
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
     * 删除琉璃-内容
     * 
     * @param id 琉璃-内容主键
     * @return 结果
     */
    public int deleteDylanLiuliById(Long id);

    /**
     * 批量删除琉璃-内容
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDylanLiuliByIds(Long[] ids);
}
