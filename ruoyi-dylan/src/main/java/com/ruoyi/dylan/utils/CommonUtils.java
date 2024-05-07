package com.ruoyi.dylan.utils;

import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dylan.dto.LiuliInfoDto;
import com.ruoyi.dylan.dto.LiuliListDto;
import com.ruoyi.dylan.dto.RequestDto;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CommonUtils
 * @Description TODO
 * @Author Dylan
 * @Date 2024/3/17 23:11
 * @Version 1.0
 */
@Slf4j
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

    /**
     * 请求琉璃首页列表数据
     * @param host
     * @return
     */
    public static List<LiuliListDto> getLiuliList(String host){
        host = host + "/liuli/list";
        Map<String, String> urlData = new HashMap<>();
        urlData.put("url", "https://hacg.uno/wp/");
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        RequestDto dto = new RequestDto();
        dto.setCharset("UTF-8");
        dto.setMethod("GET");
        dto.setHeaders(headers);
        dto.setUrlApi(host);
        dto.setUrlData(urlData);
        try {
            String data = RequestUtils.sendRequest(dto);
            if (StringUtils.isNotBlank(data)){
                ObjectMapper objectMapper = new ObjectMapper();
                List<LiuliListDto> subContentList = objectMapper.readValue(data, new TypeReference<List<LiuliListDto>>() {});
                if (ObjectUtils.isNotEmpty(subContentList)){
                    return subContentList;
                }
            }
        }catch (Exception e){
            log.info("报错:"+e.getMessage());
        }
        return null;
    }

    /**
     * 请求详情
     */
    public static LiuliInfoDto getLiuliInfo(String host, String infoLink){
        host = host + "/liuli/info";
        Map<String, String> urlData = new HashMap<>();
        urlData.put("url", infoLink);
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");
        RequestDto dto = new RequestDto();
        dto.setCharset("UTF-8");
        dto.setMethod("GET");
        dto.setHeaders(headers);
        dto.setUrlApi(host);
        dto.setUrlData(urlData);
        try {
            String data = RequestUtils.sendRequest(dto);
            if (StringUtils.isNotBlank(data)){
                ObjectMapper objectMapper = new ObjectMapper();
                LiuliInfoDto subContentList = objectMapper.readValue(data, new TypeReference<LiuliInfoDto>() {});
                if (ObjectUtils.isNotEmpty(subContentList)){
                    return subContentList;
                }
            }
        }catch (Exception e){
            log.info("报错:"+e.getMessage());
        }
        return null;
    }

//    public static void main(String[] args) {
//        List<LiuliListDto> liuliList = getLiuliList("http://localhost:8081");
//        System.out.println(liuliList);
//    }

}
