package com.ruoyi.dylan.bo;

import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.dylan.domain.DylanLiuli;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

@Data
public class DylanLiuliBo extends DylanLiuli {

    /**
     * 标签id合集
     */
    @Schema(description = "琉璃标签ids")
    private String liuliTagIds;

    /**
     * 类型id合集
     */
    @Schema(description = "类型id合集")
    private String liuliCatIds;

    /**
     * 标签idList
     */
    private List<Long> liuliTagIdList;

    /**
     * 类型idList
     */
    private List<Long> liuliCatIdList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("id", getId())
                .append("liuliLink", getLiuliLink())
                .append("btLink", getBtLink())
                .append("liuliTitle", getLiuliTitle())
                .append("content", getContent())
                .append("delFlag", getDelFlag())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .append("liuliTagIds", getLiuliTagIds())
                .append("liuliCatIds", getLiuliCatIds())
                .append("pageNum", TableSupport.buildPageRequest().getPageNum())
                .append("pageSize", TableSupport.buildPageRequest().getPageSize())
                .toString();
    }

}
