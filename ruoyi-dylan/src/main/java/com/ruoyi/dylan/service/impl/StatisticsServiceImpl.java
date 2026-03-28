package com.ruoyi.dylan.service.impl;

import com.ruoyi.dylan.dto.KpiDataDto;
import com.ruoyi.dylan.dto.NameValueDto;
import com.ruoyi.dylan.mapper.StatisticsMapper;
import com.ruoyi.dylan.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public KpiDataDto getKpiData() {
        KpiDataDto dto = new KpiDataDto();
        dto.setLiuliCount(statisticsMapper.getLiuliCount());
        dto.setTodayLiuliCount(statisticsMapper.getTodayLiuliCount());
        dto.setTagCount(statisticsMapper.getTagCount());
        dto.setCategoryCount(statisticsMapper.getCategoryCount());
        return dto;
    }

    @Override
    public List<NameValueDto> getLiuliTrend() {
        // 默认查询最近30天
        return statisticsMapper.getLiuliTrend(30);
    }

    @Override
    public List<NameValueDto> getCategoryDistribution() {
        return statisticsMapper.getCategoryDistribution();
    }

    @Override
    public List<NameValueDto> getTagRanking() {
        // 默认查询TOP 10
        return statisticsMapper.getTagRanking(10);
    }

    @Override
    public List<NameValueDto> getAuthorRanking() {
        // 默认查询TOP 10
        return statisticsMapper.getAuthorRanking(10);
    }
}
