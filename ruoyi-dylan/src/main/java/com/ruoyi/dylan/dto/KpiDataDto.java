package com.ruoyi.dylan.dto;

import lombok.Data;

@Data
public class KpiDataDto {
    private Long liuliCount;
    private Long todayLiuliCount;
    private Long tagCount;
    private Long categoryCount;
    private Long periodNewCount;
    private Double growthRate;
    private Double dailyAverage;
}
