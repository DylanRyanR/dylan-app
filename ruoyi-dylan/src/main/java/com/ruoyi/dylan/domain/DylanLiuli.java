package com.ruoyi.dylan.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * 琉璃-内容对象 dylan_liuli
 * 
 * @author dylan
 * @date 2024-03-17
 */
@Data
public class DylanLiuli extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 主键id */
    @Schema(description = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;


    /** 琉璃社链接 */
    @Schema(description = "琉璃社链接")
    @Excel(name = "琉璃社链接")
    private String liuliLink;


    /** BT链接 */
    @Schema(description = "BT链接")
    @Excel(name = "BT链接")
    private String btLink;


    /** 文章标题 */
    @Schema(description = "文章标题")
    @Excel(name = "文章标题")
    private String liuliTitle;

    /** 类型*/
    @Schema(description = "类型")
    private Long liuliCat;

    /** 发布日期*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(description = "发布日期")
    private Date publishTime;

    /** 发布人*/
    @Schema(description = "发布人")
    private String publishAuthor;

    /** 副标题*/
    @Schema(description = "副标题")
    private String subContent;


    /** 正文 */
    @Schema(description = "正文")
    @Excel(name = "正文")
    private String content;


    /** 删除标志（0代表存在 2代表删除） */
    @Schema(description = "删除标志（0代表存在 2代表删除）")
    @TableLogic(value = "0", delval = "2")
    private String delFlag;





//    public void setId(Long id)
//    {
//        this.id = id;
//    }
//
//    public Long getId()
//    {
//        return id;
//    }
//    public void setLiuliLink(String liuliLink)
//    {
//        this.liuliLink = liuliLink;
//    }
//
//    public String getLiuliLink()
//    {
//        return liuliLink;
//    }
//    public void setBtLink(String btLink)
//    {
//        this.btLink = btLink;
//    }
//
//    public String getBtLink()
//    {
//        return btLink;
//    }
//    public void setLiuliTitle(String liuliTitle)
//    {
//        this.liuliTitle = liuliTitle;
//    }
//
//    public String getLiuliTitle()
//    {
//        return liuliTitle;
//    }
//    public void setContent(String content)
//    {
//        this.content = content;
//    }
//
//    public String getContent()
//    {
//        return content;
//    }
//    public void setDelFlag(String delFlag)
//    {
//        this.delFlag = delFlag;
//    }
//
//    public String getDelFlag()
//    {
//        return delFlag;
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("id", getId())
//            .append("liuliLink", getLiuliLink())
//            .append("btLink", getBtLink())
//            .append("liuliTitle", getLiuliTitle())
//            .append("content", getContent())
//            .append("delFlag", getDelFlag())
//            .append("createBy", getCreateBy())
//            .append("createTime", getCreateTime())
//            .append("updateBy", getUpdateBy())
//            .append("updateTime", getUpdateTime())
//            .toString();
//    }
}
