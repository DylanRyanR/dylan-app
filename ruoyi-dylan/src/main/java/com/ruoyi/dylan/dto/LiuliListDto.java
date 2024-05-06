package com.ruoyi.dylan.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName LiuliListDto
 * @Description TODO
 * @Author Dylan
 * @Date 2024/5/2 20:07
 * @Version 1.0
 */
@Data
public class LiuliListDto {

    /** 标题*/
    private String title;

    /** 发布时间*/
    private String publishTime;

    /** 发布人*/
    private String author;

    /** 图片地址*/
    private String imgLink;

    /** 简介*/
    private String desc;

    /** 类型*/
    private String cat;

    /** 标签合集*/
    private List<String> tags;

    /** 详细地址*/
    private String infoLink;
}
