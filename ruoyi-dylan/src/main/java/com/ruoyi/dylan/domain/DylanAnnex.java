package com.ruoyi.dylan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.ruoyi.common.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class DylanAnnex extends BaseEntity {

    /** 附件id*/
    @Schema(description = "附件id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 附加名称*/
    @Schema(description = "附件名称")
    private String name;

    /** 附件url*/
    @Schema(description = "附件url")
    private String url;

    /** 删除标识（0未删除，2已删除）*/
    @Schema(description = "删除标识（0未删除，2已删除）")
    @TableLogic(value = "0", delval = "2")
    private String delFlag;
}
