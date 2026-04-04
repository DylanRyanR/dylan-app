package com.ruoyi.dylan.mapper;

import com.ruoyi.dylan.dto.NameValueDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StatisticsMapper {

    Long getLiuliCount(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    Long getTodayLiuliCount();

    Long getTagCount();

    Long getCategoryCount();

    Long getPeriodLiuliCount(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    Long getLiuliCountBefore(@Param("beginDate") String beginDate, @Param("diffDays") long diffDays);

    List<NameValueDto> getLiuliTrend(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<NameValueDto> getCategoryDistribution(@Param("beginDate") String beginDate, @Param("endDate") String endDate);

    List<NameValueDto> getTagRanking(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("limit") int limit);

    List<NameValueDto> getAuthorRanking(@Param("beginDate") String beginDate, @Param("endDate") String endDate, @Param("limit") int limit);
}
