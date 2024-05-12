package com.ruoyi.dylan.service;

import java.util.List;
import com.ruoyi.dylan.domain.DylanLiuliLink;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 琉璃链接Service接口
 * 
 * @author dylan
 * @date 2024-05-12
 */
public interface IDylanLiuliLinkService extends IService<DylanLiuliLink>
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
     * 批量删除琉璃链接
     * 
     * @param ids 需要删除的琉璃链接主键集合
     * @return 结果
     */
    public int deleteDylanLiuliLinkByIds(Long[] ids);

    /**
     * 删除琉璃链接信息
     * 
     * @param id 琉璃链接主键
     * @return 结果
     */
    public int deleteDylanLiuliLinkById(Long id);
}
