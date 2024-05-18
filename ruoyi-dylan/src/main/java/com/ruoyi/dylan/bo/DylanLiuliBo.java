package com.ruoyi.dylan.bo;

import com.ruoyi.dylan.domain.DylanLiuli;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class DylanLiuliBo extends DylanLiuli {

    /** 标签id合集*/
    @Schema(description = "琉璃标签ids")
    private String liuliTagIds;

    /** 类型id合集*/
    @Schema(description = "类型id合集")
    private String liuliCatIds;

    /** 标签idList*/
    private List<Long> liuliTagIdList;

    /** 类型idList*/
    private List<Long> liuliCatIdList;

}
