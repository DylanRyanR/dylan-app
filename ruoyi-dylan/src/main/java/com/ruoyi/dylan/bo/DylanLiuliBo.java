package com.ruoyi.dylan.bo;

import com.ruoyi.dylan.domain.DylanLiuli;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DylanLiuliBo extends DylanLiuli {

    /** 标签id合集*/
    @Schema(description = "琉璃标签ids")
    private String liuliTagIds;

}
