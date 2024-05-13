package com.ruoyi.dylan.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DylanLiuliAnnex {

    /** 琉璃id*/
    @Schema(description = "琉璃id")
    private Long liuliId;

    /** 附件id*/
    @Schema(description = "附件id")
    private Long annexId;
}
