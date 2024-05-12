package com.ruoyi.dylan.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.dylan.domain.DylanLiuliLink;

/**
 * 琉璃链接Mapper接口
 * 
 * @author dylan
 * @date 2024-05-12
 */
public interface DylanLiuliLinkMapper extends BaseMapper<DylanLiuliLink>
{
    /**
     * 查询琉璃链接
     * 
     * @param id 琉璃链接主键
     * @return 琉璃链接
     */
    public DylanLiuliLink selectDylanLiuliLinkById(Long id);

    /**
     * 查询琉璃链接列表
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 琉璃链接集合
     */
    public List<DylanLiuliLink> selectDylanLiuliLinkList(DylanLiuliLink dylanLiuliLink);

    /**
     * 新增琉璃链接
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 结果
     */
    public int insertDylanLiuliLink(DylanLiuliLink dylanLiuliLink);

    /**
     * 修改琉璃链接
     * 
     * @param dylanLiuliLink 琉璃链接
     * @return 结果
     */
    public int updateDylanLiuliLink(DylanLiuliLink dylanLiuliLink);

    /**
     * 删除琉璃链接
     * 
     * @param id 琉璃链接主键
     * @return 结果
     */
    public int deleteDylanLiuliLinkById(Long id);

    /**
     * 批量删除琉璃链接
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDylanLiuliLinkByIds(Long[] ids);
}
