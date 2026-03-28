package com.ruoyi.dylan.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.dylan.dto.KpiDataDto;
import com.ruoyi.dylan.dto.NameValueDto;
import com.ruoyi.dylan.service.IStatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dylan/statistics")
@Tag(name = "【琉璃-统计】管理")
public class StatisticsController extends BaseController {

    @Autowired
    private IStatisticsService statisticsService;

    @GetMapping("/kpi")
    @Operation(summary = "获取核心KPI指标")
    public AjaxResult getKpiData() {
        KpiDataDto kpiData = statisticsService.getKpiData();
        return AjaxResult.success(kpiData);
    }

    @GetMapping("/liuli-trend")
    @Operation(summary = "获取内容新增趋势")
    public AjaxResult getLiuliTrend() {
        List<NameValueDto> trendData = statisticsService.getLiuliTrend();
        return AjaxResult.success(trendData);
    }

    @GetMapping("/category-distribution")
    @Operation(summary = "获取分类分布")
    public AjaxResult getCategoryDistribution() {
        List<NameValueDto> distributionData = statisticsService.getCategoryDistribution();
        return AjaxResult.success(distributionData);
    }

    @GetMapping("/tag-ranking")
    @Operation(summary = "获取标签排行")
    public AjaxResult getTagRanking() {
        List<NameValueDto> rankingData = statisticsService.getTagRanking();
        return AjaxResult.success(rankingData);
    }

    @GetMapping("/author-ranking")
    @Operation(summary = "获取作者发布排行")
    public AjaxResult getAuthorRanking() {
        List<NameValueDto> rankingData = statisticsService.getAuthorRanking();
        return AjaxResult.success(rankingData);
    }
}
