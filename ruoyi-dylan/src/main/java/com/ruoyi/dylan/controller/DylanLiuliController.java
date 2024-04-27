package com.ruoyi.dylan.controller;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
@RestController
@RequestMapping("/dylan/liuli")
@Tag(name = "【琉璃-内容】管理")
public class DylanLiuliController extends BaseController
{
    @Autowired
    private IDylanLiuliService dylanLiuliService;

    /**
     * 查询琉璃-内容列表
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:list')")
    @GetMapping("/list")
    @Operation(summary = "查询琉璃-内容列表")
    public TableDataInfo list(DylanLiuli dylanLiuli)
    {
        startPage();
        List<DylanLiuli> list = dylanLiuliService.selectDylanLiuliList(dylanLiuli);
        return getDataTable(list);
    }

    /**
     * 导出琉璃-内容列表
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:export')")
    @Log(title = "琉璃-内容", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出琉璃-内容列表")
    public void export(HttpServletResponse response, DylanLiuli dylanLiuli)
    {
        List<DylanLiuli> list = dylanLiuliService.selectDylanLiuliList(dylanLiuli);
        ExcelUtil<DylanLiuli> util = new ExcelUtil<DylanLiuli>(DylanLiuli.class);
        util.exportExcel(response, list, "琉璃-内容数据");
    }

    /**
     * 获取琉璃-内容详细信息
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:query')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取琉璃-内容详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dylanLiuliService.selectDylanLiuliById(id));
    }

    /**
     * 新增琉璃-内容
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:add')")
    @Log(title = "琉璃-内容", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增琉璃-内容")
    public AjaxResult add(@RequestBody DylanLiuli dylanLiuli)
    {
        return toAjax(dylanLiuliService.insertDylanLiuli(dylanLiuli));
    }

    /**
     * 修改琉璃-内容
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:edit')")
    @Log(title = "琉璃-内容", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改琉璃-内容")
    public AjaxResult edit(@RequestBody DylanLiuli dylanLiuli)
    {
        return toAjax(dylanLiuliService.updateDylanLiuli(dylanLiuli));
    }

    /**
     * 删除琉璃-内容
     */
    @PreAuthorize("@ss.hasPermi('dylan:liuli:remove')")
    @Log(title = "琉璃-内容", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Operation(summary = "删除琉璃-内容")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        // 进行删除操作
        return toAjax(dylanLiuliService.deleteDylanLiuliByIds(ids));
    }
}
