package com.ruoyi.dylan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.dylan.domain.DylanTag;

/**
 * 标签Mapper接口
 * 
 * @author dylan
 * @date 2024-05-03
 */
public interface DylanTagMapper extends BaseMapper<DylanTag>
{
    /**
     * 查询标签
     * 
     * @param id 标签主键
     * @return 标签
     */
    public DylanTag selectDylanTagById(Long id);

    /**
     * 查询标签列表
     * 
     * @param dylanTag 标签
     * @return 标签集合
     */
    public List<DylanTag> selectDylanTagList(DylanTag dylanTag);

    /**
     * 新增标签
     * 
     * @param dylanTag 标签
     * @return 结果
     */
    public int insertDylanTag(DylanTag dylanTag);

    /**
     * 修改标签
     * 
     * @param dylanTag 标签
     * @return 结果
     */
    public int updateDylanTag(DylanTag dylanTag);

    /**
     * 删除标签
     * 
     * @param id 标签主键
     * @return 结果
     */
    public int deleteDylanTagById(Long id);

    /**
     * 批量删除标签
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDylanTagByIds(Long[] ids);
}
