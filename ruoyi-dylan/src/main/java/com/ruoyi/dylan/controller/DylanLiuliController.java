package com.ruoyi.dylan.controller;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.ruoyi.dylan.bo.DylanLiuliBo;
import com.ruoyi.dylan.bo.DylanQueryBo;
import com.ruoyi.dylan.utils.DylanCacheUtils;
import com.ruoyi.dylan.vo.DylanLiuliPageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.dylan.domain.DylanLiuli;
import com.ruoyi.dylan.service.IDylanLiuliService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 琉璃-内容Controller
 * 
 * @author dylan
 * @date 2024-03-17
 */
@Slf4j
@RestController
@RequestMapping("/dylan/liuli")
@Tag(name = "【琉璃-内容】管理")
@CacheConfig(cacheNames = "liuliList")
public class DylanLiuliController extends BaseController
{
    @Autowired
    private IDylanLiuliService dylanLiuliService;


    /**
     * 查询琉璃-内容列表
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:list')")
    @GetMapping("/list")
    @Operation(summary = "查询琉璃-内容列表")
    @Cacheable(value = "liuliList", key = "#dylanLiuli.toString()")
    public TableDataInfo list(DylanLiuliBo dylanLiuli)
    {
        startPage();
        long start1 = System.currentTimeMillis();
        List<DylanLiuli> list = dylanLiuliService.selectDylanLiuliList(dylanLiuli);
        long end1 = System.currentTimeMillis();
        List<DylanLiuliPageVo> vos = dylanLiuliService.generateVo(list);
        long end2 = System.currentTimeMillis();
        log.info("查询时间：{}ms", end1 - start1);
        log.info("拼接时间：{}ms", end2 - end1);
        return getDataTable(vos, new PageInfo(list).getTotal());
    }

    /**
     * 导出琉璃-内容列表
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:export')")
    @Log(title = "琉璃-内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出琉璃-内容列表")
    public void export(HttpServletResponse response, DylanLiuliBo dylanLiuli)
    {
        List<DylanLiuli> list = dylanLiuliService.selectDylanLiuliList(dylanLiuli);
        ExcelUtil<DylanLiuli> util = new ExcelUtil<DylanLiuli>(DylanLiuli.class);
        util.exportExcel(response, list, "琉璃-内容数据");
    }

    /**
     * 获取琉璃-内容详细信息
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:query')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取琉璃-内容详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dylanLiuliService.selectDylanLiuliById(id));
    }

    /**
     * 新增琉璃-内容
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:add')")
    @Log(title = "琉璃-内容", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增琉璃-内容")
    public AjaxResult add(@RequestBody DylanLiuli dylanLiuli)
    {
        int rows = dylanLiuliService.insertDylanLiuli(dylanLiuli);
        // 执行完成后刷新缓存
        if (rows > 0) {
            DylanCacheUtils.evictCache("liuliList");
        }
        return toAjax(rows);
    }

    /**
     * 修改琉璃-内容
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:edit')")
    @Log(title = "琉璃-内容", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改琉璃-内容")
    public AjaxResult edit(@RequestBody DylanLiuli dylanLiuli)
    {
        int rows = dylanLiuliService.updateDylanLiuli(dylanLiuli);
        if (rows > 0) {
            DylanCacheUtils.evictCache("liuliList");
        }
        return toAjax(rows);
    }

    /**
     * 删除琉璃-内容
     */
//    @PreAuthorize("@ss.hasPermi('dylan:liuli:remove')")
    @Log(title = "琉璃-内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Operation(summary = "删除琉璃-内容")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 进行删除操作
        int rows = dylanLiuliService.deleteDylanLiuliByIds(ids);
        if (rows > 0) {
            DylanCacheUtils.evictCache("liuliList");
        }
        return toAjax(rows);
    }

    /**
     * 大屏通用脚本
     */
    @PatchMapping("/commonQuery")
    public AjaxResult getCommonList(@RequestBody DylanQueryBo bo){
        List<Map<String, Object>> list = dylanLiuliService.getCommonList(bo);
        return AjaxResult.success(list);
    }
}
