package com.ruoyi.dylan.dto;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;

import java.util.Map;

/**
 * @author Dylan
 * @version 1.0.0
 * @ClassName RequestDto.java
 * @createTime 2024年01月25日 09:17:00
 */
@Data
public class RequestDto {

    /** 请求链接*/
    private String urlApi;

    /** 请求方法*/
    private String method;

    /** body传参*/
    private JSONObject jsonBody;

    /** 字符编码*/
    private String charset;

    /** 请求头*/
    private Map<String, String> headers;

    /** URL参数*/
    private Map<String, String> urlData;

}
