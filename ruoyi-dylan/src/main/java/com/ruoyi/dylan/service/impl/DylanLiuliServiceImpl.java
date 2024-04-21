package com.ruoyi.dylan.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dylan.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class DylanLiuliServiceImpl implements IDylanLiuliService 
{
    @Autowired
    private DylanLiuliMapper dylanLiuliMapper;

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
}
