package com.ruoyi.dylan.service.impl;

import com.ruoyi.dylan.dto.KpiDataDto;
import com.ruoyi.dylan.dto.NameValueDto;
import com.ruoyi.dylan.mapper.StatisticsMapper;
import com.ruoyi.dylan.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    private static final int DEFAULT_RANGE_DAYS = 30;
    private static final int TAG_RANK_LIMIT = 10;
    private static final int AUTHOR_RANK_LIMIT = 10;

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public KpiDataDto getKpiData(String rangeType, String beginDate, String endDate) {
        LocalDateRange range = resolveRange(rangeType, beginDate, endDate);
        KpiDataDto dto = new KpiDataDto();
        dto.setLiuliCount(statisticsMapper.getLiuliCount(null, null));
        Long todayCount = statisticsMapper.getTodayLiuliCount();
        dto.setTodayLiuliCount(todayCount == null ? 0L : todayCount);
        Long tagCount = statisticsMapper.getTagCount();
        dto.setTagCount(tagCount == null ? 0L : tagCount);
        Long categoryCount = statisticsMapper.getCategoryCount();
        dto.setCategoryCount(categoryCount == null ? 0L : categoryCount);

        Long periodCount = statisticsMapper.getPeriodLiuliCount(range.getBeginDate(), range.getEndDate());
        if (periodCount == null) {
            periodCount = 0L;
        }
        dto.setPeriodNewCount(periodCount);

        long diffDays = range.getDiffDays();
        if (diffDays > 0) {
            dto.setDailyAverage(roundDouble(periodCount / (double) diffDays));
        } else {
            dto.setDailyAverage(0d);
        }

        Long beforeCount = statisticsMapper.getLiuliCountBefore(range.getBeginDate(), diffDays);
        if (beforeCount == null) {
            beforeCount = 0L;
        }
        if (beforeCount > 0) {
            dto.setGrowthRate(roundDouble((periodCount - beforeCount) * 100d / beforeCount));
        } else {
            dto.setGrowthRate(0d);
        }
        return dto;
    }

    @Override
    public Map<String, Object> getLiuliTrend(String rangeType, String beginDate, String endDate) {
        LocalDateRange range = resolveRange(rangeType, beginDate, endDate);
        List<NameValueDto> series = statisticsMapper.getLiuliTrend(range.getBeginDate(), range.getEndDate());

        Map<String, Object> summary = new HashMap<>();
        long total = series.stream().filter(Objects::nonNull).mapToLong(item -> item.getValue() == null ? 0 : item.getValue()).sum();
        long diffDays = range.getDiffDays();
        double average = diffDays > 0 ? roundDouble(total / (double) diffDays) : 0d;

        NameValueDto peak = series.stream()
            .filter(item -> item != null && item.getValue() != null)
            .max(Comparator.comparingLong(NameValueDto::getValue))
            .orElse(null);
        NameValueDto low = series.stream()
            .filter(item -> item != null && item.getValue() != null)
            .min(Comparator.comparingLong(NameValueDto::getValue))
            .orElse(null);

        summary.put("total", total);
        summary.put("average", average);
        summary.put("peakLabel", peak == null ? "" : peak.getName());
        summary.put("peakValue", peak == null ? 0 : peak.getValue());
        summary.put("lowLabel", low == null ? "" : low.getName());
        summary.put("lowValue", low == null ? 0 : low.getValue());

        Map<String, Object> result = new HashMap<>();
        result.put("series", series);
        result.put("summary", summary);
        return result;
    }

    @Override
    public List<NameValueDto> getCategoryDistribution(String rangeType, String beginDate, String endDate) {
        LocalDateRange range = resolveRange(rangeType, beginDate, endDate);
        return statisticsMapper.getCategoryDistribution(range.getBeginDate(), range.getEndDate());
    }

    @Override
    public List<NameValueDto> getTagRanking(String rangeType, String beginDate, String endDate) {
        LocalDateRange range = resolveRange(rangeType, beginDate, endDate);
        return statisticsMapper.getTagRanking(range.getBeginDate(), range.getEndDate(), TAG_RANK_LIMIT);
    }

    @Override
    public List<NameValueDto> getAuthorRanking(String rangeType, String beginDate, String endDate) {
        LocalDateRange range = resolveRange(rangeType, beginDate, endDate);
        return statisticsMapper.getAuthorRanking(range.getBeginDate(), range.getEndDate(), AUTHOR_RANK_LIMIT);
    }

    private LocalDateRange resolveRange(String rangeType, String beginDate, String endDate) {
        LocalDate end = LocalDate.now();
        LocalDate start;
        if ("7d".equals(rangeType)) {
            start = end.minusDays(6);
        } else if ("30d".equals(rangeType)) {
            start = end.minusDays(29);
        } else if ("90d".equals(rangeType)) {
            start = end.minusDays(89);
        } else if ("month".equals(rangeType)) {
            start = end.withDayOfMonth(1);
        } else if ("custom".equals(rangeType) && beginDate != null && endDate != null) {
            start = LocalDate.parse(beginDate);
            end = LocalDate.parse(endDate);
        } else {
            start = end.minusDays(DEFAULT_RANGE_DAYS - 1);
        }
        return new LocalDateRange(start, end);
    }

    private double roundDouble(double value) {
        return Math.round(value * 100d) / 100d;
    }

    private static class LocalDateRange {
        private final LocalDate start;
        private final LocalDate end;

        private LocalDateRange(LocalDate start, LocalDate end) {
            this.start = start;
            this.end = end;
        }

        private String getBeginDate() {
            return start.toString();
        }

        private String getEndDate() {
            return end.toString();
        }

        private long getDiffDays() {
            return ChronoUnit.DAYS.between(start, end) + 1;
        }
    }
}
