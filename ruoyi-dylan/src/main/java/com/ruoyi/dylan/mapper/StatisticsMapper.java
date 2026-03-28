package com.ruoyi.dylan.mapper;

import com.ruoyi.dylan.dto.NameValueDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StatisticsMapper {

    Long getLiuliCount();

    Long getTodayLiuliCount();

    Long getTagCount();

    Long getCategoryCount();

    List<NameValueDto> getLiuliTrend(@Param("days") int days);

    List<NameValueDto> getCategoryDistribution();

    List<NameValueDto> getTagRanking(@Param("limit") int limit);

    List<NameValueDto> getAuthorRanking(@Param("limit") int limit);
}
