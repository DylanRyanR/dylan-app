package com.ruoyi.dylan.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @ClassName DylanLiuliTag
 * @Description TODO
 * @Author Dylan
 * @Date 2024/5/3 11:41
 * @Version 1.0
 */
@Data
public class DylanLiuliTag {

    /** 琉璃内容id*/
    @Schema(description = "琉璃内容id")
    private Long liuliId;

    /** 标签id*/
    @Schema(description = "标签id")
    private Long tagId;
}
