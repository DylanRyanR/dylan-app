package com.ruoyi.dylan.utils;

import com.ruoyi.common.utils.StringUtils;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author Dylan
 * @Date 2024/3/17 23:11
 * @Version 1.0
 */
public class CommonUtils {

    /**
     * 修复bt链接缺失项
     * @param link
     * @return
     */
    public static String fixBtLink(String link){
        if (StringUtils.isNotBlank(link)){
            if (!link.contains("magnet:?xt=urn:btih:")){
                StringBuilder sb = new StringBuilder();
                sb.append("magnet:?xt=urn:btih:").append(link);
                link = sb.toString();
            }
        }
        return link;
    }

}
