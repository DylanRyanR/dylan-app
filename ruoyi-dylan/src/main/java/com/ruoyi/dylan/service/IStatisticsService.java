package com.ruoyi.dylan.service;

import com.ruoyi.dylan.dto.KpiDataDto;
import com.ruoyi.dylan.dto.NameValueDto;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    KpiDataDto getKpiData(String rangeType, String beginDate, String endDate);

    Map<String, Object> getLiuliTrend(String rangeType, String beginDate, String endDate);

    List<NameValueDto> getCategoryDistribution(String rangeType, String beginDate, String endDate);

    List<NameValueDto> getTagRanking(String rangeType, String beginDate, String endDate);

    List<NameValueDto> getAuthorRanking(String rangeType, String beginDate, String endDate);
}
