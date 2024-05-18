package com.ruoyi.dylan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CommonBoxVo {

    /** 标签id*/
    @Schema(description = "标签id")
    private Long id;

    /** 标签名称*/
    @Schema(description = "标签名称")
    private String name;
}
