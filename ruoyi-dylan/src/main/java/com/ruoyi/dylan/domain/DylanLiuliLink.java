package com.ruoyi.dylan.domain;

import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 琉璃链接对象 dylan_liuli_link
 * 
 * @author dylan
 * @date 2024-05-12
 */
@Data
public class DylanLiuliLink extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 主键id */
    @Schema(description = "主键id")
    private Long id;


    /** 琉璃链接 */
    @Schema(description = "琉璃链接")
    @Excel(name = "琉璃链接")
    private String mainLink;


    /** 是否可用（0否、1是） */
    @Schema(description = "是否可用（0否、1是）")
    @Excel(name = "是否可用", readConverterExp = "0=否、1是")
    private Integer chkUse;


    /** 删除标志（0代表存在 2代表删除） */
    @Schema(description = "删除标志（0代表存在 2代表删除）")
    @TableLogic(value = "0", delval = "2")
    private String delFlag;





    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setMainLink(String mainLink) 
    {
        this.mainLink = mainLink;
    }

    public String getMainLink() 
    {
        return mainLink;
    }
    public void setChkUse(Integer chkUse) 
    {
        this.chkUse = chkUse;
    }

    public Integer getChkUse() 
    {
        return chkUse;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("mainLink", getMainLink())
            .append("chkUse", getChkUse())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
