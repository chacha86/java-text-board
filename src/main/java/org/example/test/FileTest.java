package org.example.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;
import org.example.domain.Article;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class FileTest {
    public static void main(String[] args) {

        Article article = new Article();
        article.setId(1);
        article.setTitle("제목");
        article.setBody("내용");
//        article.setRegDate("2021-08-11 12:12:12");
        article.setHit(0);

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(article);
            writeJson("data/article/1.json", json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void writeJson(String path, String json) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), json);
        } catch (IOException e) {
            throw new RuntimeException("파일 쓰기 실패");
        }
    }
}
