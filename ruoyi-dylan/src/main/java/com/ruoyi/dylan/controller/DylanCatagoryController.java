package com.ruoyi.dylan.controller;

import java.util.List;
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
import com.ruoyi.dylan.domain.DylanCatagory;
import com.ruoyi.dylan.service.IDylanCatagoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 类型Controller
 * 
 * @author dylan
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/dylan/cat")
@Tag(name = "【类型】管理")
public class DylanCatagoryController extends BaseController
{
    @Autowired
    private IDylanCatagoryService dylanCatagoryService;

    /**
     * 查询类型列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:list')")
    @GetMapping("/list")
    @Operation(summary = "查询类型列表")
    public TableDataInfo list(DylanCatagory dylanCatagory)
    {
        startPage();
        List<DylanCatagory> list = dylanCatagoryService.selectDylanCatagoryList(dylanCatagory);
        return getDataTable(list);
    }

    /**
     * 导出类型列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:export')")
    @Log(title = "类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出类型列表")
    public void export(HttpServletResponse response, DylanCatagory dylanCatagory)
    {
        List<DylanCatagory> list = dylanCatagoryService.selectDylanCatagoryList(dylanCatagory);
        ExcelUtil<DylanCatagory> util = new ExcelUtil<DylanCatagory>(DylanCatagory.class);
        util.exportExcel(response, list, "类型数据");
    }

    /**
     * 获取类型详细信息
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:query')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取类型详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dylanCatagoryService.selectDylanCatagoryById(id));
    }

    /**
     * 新增类型
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:add')")
    @Log(title = "类型", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增类型")
    public AjaxResult add(@RequestBody DylanCatagory dylanCatagory)
    {
        return toAjax(dylanCatagoryService.insertDylanCatagory(dylanCatagory));
    }

    /**
     * 修改类型
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:edit')")
    @Log(title = "类型", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改类型")
    public AjaxResult edit(@RequestBody DylanCatagory dylanCatagory)
    {
        return toAjax(dylanCatagoryService.updateDylanCatagory(dylanCatagory));
    }

    /**
     * 删除类型
     */
    // @PreAuthorize("@ss.hasPermi('dylan:cat:remove')")
    @Log(title = "类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Operation(summary = "删除类型")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dylanCatagoryService.deleteDylanCatagoryByIds(ids));
    }
}
