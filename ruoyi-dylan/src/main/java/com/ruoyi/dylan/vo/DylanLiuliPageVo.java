package com.ruoyi.dylan.vo;

import com.ruoyi.dylan.domain.DylanLiuli;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

/**
 * @ClassName DylanLiuliPageVo
 * @Description TODO
 * @Author Dylan
 * @Date 2024/5/3 11:34
 * @Version 1.0
 */
@Data
public class DylanLiuliPageVo extends DylanLiuli {

    /** 类型名称*/
    @Schema(description = "类型名称")
    private String catName;

    /** 标签名称*/
    @Schema(description = "标签名称")
    private String tagNames;

    /** 图片url*/
    @Schema(description = "图片url")
    private String imgUrl;
}
