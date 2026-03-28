package com.ruoyi.dylan.service;

import com.ruoyi.dylan.dto.KpiDataDto;
import com.ruoyi.dylan.dto.NameValueDto;

import java.util.List;

public interface IStatisticsService {

    KpiDataDto getKpiData();

    List<NameValueDto> getLiuliTrend();

    List<NameValueDto> getCategoryDistribution();

    List<NameValueDto> getTagRanking();

    List<NameValueDto> getAuthorRanking();
}
