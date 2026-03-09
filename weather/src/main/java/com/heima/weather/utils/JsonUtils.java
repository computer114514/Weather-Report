package com.heima.weather.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // 将 JSON 字符串转为指定类型的对象
//    public static <T> T fromJson(String json, Class<T> clazz) {
//        try {
//            return objectMapper.readValue(json, clazz);
//        } catch (Exception e) {
//            throw new RuntimeException("JSON 解析失败: " + e.getMessage(), e);
//        }
//    }
    public static <T> T fromJson(String json,Class<T> clazz){
        try {
            return objectMapper.readValue(json,clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("json解析失败,失败信息:",e);
        }
    }
}
