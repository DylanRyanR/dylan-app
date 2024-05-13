package com.ruoyi.dylan.controller;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.ruoyi.dylan.vo.DylanTagBoxVo;
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
import com.ruoyi.dylan.domain.DylanTag;
import com.ruoyi.dylan.service.IDylanTagService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 标签Controller
 * 
 * @author dylan
 * @date 2024-05-03
 */
@RestController
@RequestMapping("/dylan/tag")
@Tag(name = "【标签】管理")
public class DylanTagController extends BaseController
{
    @Autowired
    private IDylanTagService dylanTagService;

    /**
     * 查询标签列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:list')")
    @GetMapping("/list")
    @Operation(summary = "查询标签列表")
    public TableDataInfo list(DylanTag dylanTag)
    {
        startPage();
        List<DylanTag> list = dylanTagService.selectDylanTagList(dylanTag);
        return getDataTable(list);
    }

    /**
     * 导出标签列表
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:export')")
    @Log(title = "标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @Operation(summary = "导出标签列表")
    public void export(HttpServletResponse response, DylanTag dylanTag)
    {
        List<DylanTag> list = dylanTagService.selectDylanTagList(dylanTag);
        ExcelUtil<DylanTag> util = new ExcelUtil<DylanTag>(DylanTag.class);
        util.exportExcel(response, list, "标签数据");
    }

    /**
     * 获取标签详细信息
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:query')")
    @GetMapping(value = "/{id}")
    @Operation(summary = "获取标签详细信息")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(dylanTagService.selectDylanTagById(id));
    }

    /**
     * 新增标签
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:add')")
    @Log(title = "标签", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "新增标签")
    public AjaxResult add(@RequestBody DylanTag dylanTag)
    {
        return toAjax(dylanTagService.insertDylanTag(dylanTag));
    }

    /**
     * 修改标签
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:edit')")
    @Log(title = "标签", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改标签")
    public AjaxResult edit(@RequestBody DylanTag dylanTag)
    {
        return toAjax(dylanTagService.updateDylanTag(dylanTag));
    }

    /**
     * 删除标签
     */
    // @PreAuthorize("@ss.hasPermi('dylan:tag:remove')")
    @Log(title = "标签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @Operation(summary = "删除标签")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(dylanTagService.deleteDylanTagByIds(ids));
    }

    /**
     * 标签下拉列表
     */
    @Log(title = "标签下拉列表", businessType = BusinessType.OTHER)
    @GetMapping("/getBoxList")
    public AjaxResult getBoxList(){
        List<DylanTagBoxVo> vos = new ArrayList<>();
        List<DylanTag> list = dylanTagService.list();
        if (ObjectUtils.isNotEmpty(list)){
            for (DylanTag tag : list){
                DylanTagBoxVo vo = BeanUtil.toBean(tag, DylanTagBoxVo.class);
                vos.add(vo);
            }
        }
        return AjaxResult.success(vos);
    }
}
