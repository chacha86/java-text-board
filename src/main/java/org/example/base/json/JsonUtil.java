package org.example.base.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonUtil {
    public static void writeWithJackson(String path, String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), json);
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기 실패");
        }
    }

    public static String readWithJackson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(path), String.class);
        } catch (IOException e) {
            throw new RuntimeException("파일 읽기 실패");
        }
    }

    public static String getJsonStr(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (IOException e) {
            throw new RuntimeException("json 변환 실패");
        }
    }

    public static <T> T getObjectFromJson(String json, Class<T> clz) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(json);
            return mapper.readValue(json, clz);
        } catch (IOException e) {
            throw new RuntimeException("json 변환 실패");
        }
    }
}
