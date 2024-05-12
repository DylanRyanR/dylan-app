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
import com.ruoyi.dylan.domain.DylanLiuliLink;
import com.ruoyi.dylan.service.IDylanLiuliLinkService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 琉璃链接Controller
 * 
 * @author dylan
 * @date 2024-05-12
 */
@RestController
@RequestMapping("/dylan/link")
@Tag(name = "【琉璃链接】管理")
public class DylanLiuliLinkController extends BaseController
{
    @Autowired
    private IDylanLiuliLinkService dylanLiuliLinkService;

    /**
     * 查询琉璃链接列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:list')")
    @GetMapping("/list")
    @Operation(summary = "查询琉璃链接列表")
    public TableDataInfo list(DylanLiuliLink dylanLiuliLink)
    {
        startPage();
        List<DylanLiuliLink> list = dylanLiuliLinkService.selectDylanLiuliLinkList(dylanLiuliLink);
        return getDataTable(list);
    }

    /**
     * 导出琉璃链接列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:export')")
    @Log(title = "琉璃链接", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出琉璃链接列表")
    public void export(HttpServletResponse response, DylanLiuliLink dylanLiuliLink)
    {
        List<DylanLiuliLink> list = dylanLiuliLinkService.selectDylanLiuliLinkList(dylanLiuliLink);
        ExcelUtil<DylanLiuliLink> util = new ExcelUtil<DylanLiuliLink>(DylanLiuliLink.class);
        util.exportExcel(response, list, "琉璃链接数据");
    }

    /**
     * 获取琉璃链接详细信息
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:query')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取琉璃链接详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dylanLiuliLinkService.selectDylanLiuliLinkById(id));
    }

    /**
     * 新增琉璃链接
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:add')")
    @Log(title = "琉璃链接", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增琉璃链接")
    public AjaxResult add(@RequestBody DylanLiuliLink dylanLiuliLink)
    {
        return toAjax(dylanLiuliLinkService.insertDylanLiuliLink(dylanLiuliLink));
    }

    /**
     * 修改琉璃链接
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:edit')")
    @Log(title = "琉璃链接", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改琉璃链接")
    public AjaxResult edit(@RequestBody DylanLiuliLink dylanLiuliLink)
    {
        return toAjax(dylanLiuliLinkService.updateDylanLiuliLink(dylanLiuliLink));
    }

    /**
     * 删除琉璃链接
     */
    // @PreAuthorize("@ss.hasPermi('dylan:link:remove')")
    @Log(title = "琉璃链接", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Operation(summary = "删除琉璃链接")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dylanLiuliLinkService.deleteDylanLiuliLinkByIds(ids));
    }
}
