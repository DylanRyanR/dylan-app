package com.ruoyi.dylan.utils;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.dylan.dto.RequestDto;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @ClassName RequestUtils
 * @Description TODO
 * @Author Dylan
 * @Date 2024/5/3 12:50
 * @Version 1.0
 */
@Slf4j
public class RequestUtils {

    /**
     * 发送请求通用接口
     * @return
     * @throws Exception
     */
    public static String sendRequest(RequestDto requestDto) throws Exception {
        String urlApi = requestDto.getUrlApi();
        Map<String, String> urlData = requestDto.getUrlData();
        if (ObjectUtil.isNotEmpty(urlData)){
            List<String> data = new ArrayList<>();
            urlData.forEach((k, v) -> data.add(k+"="+v));
            if (ObjectUtil.isNotEmpty(data)){
                String collect = data.stream().collect(Collectors.joining("&"));
                if (StringUtils.isNotBlank(collect)){
                    urlApi = urlApi + "?" + collect;
                }
            }
        }
        log.info("请求链接：{}", urlApi);
        URL requestUrl = new URL(urlApi);
        HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
        log.info("请求方法：{}", requestDto.getMethod());
        connection.setRequestMethod(requestDto.getMethod());
        connection.setDoOutput(true);
        Map<String, String> headers = requestDto.getHeaders();
        if (ObjectUtil.isNotEmpty(headers)){
            headers.forEach(connection::setRequestProperty);
        }
        JSONObject jsonBody = requestDto.getJsonBody();
        if (ObjectUtil.isNotNull(jsonBody)){
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.write(jsonBody.toJSONString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            outputStream.close();
        }

        int responseCode = connection.getResponseCode();
        BufferedReader reader;
        log.info("请求结果code：{}", responseCode);
        if (responseCode >= 200 && responseCode < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        return response.toString();
    }
}
