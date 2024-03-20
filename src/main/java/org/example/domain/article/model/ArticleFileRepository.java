package org.example.domain.article.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.base.CommonUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ArticleFileRepository {
    private int latestId = 1;
    private ArrayList<Article> articleList = new ArrayList<>(); // 저장소
    public void saveArticle(String title, String body) {
        // 번호는 latestId, 제목이 title, 내용이 body, 조회수 0, 등록날짜 현재시간인 게시물을
        // json 파일로 저장

        CommonUtil commonUtil = new CommonUtil();
        Article a1 = new Article(latestId, title, body, 0, commonUtil.getCurrentDateTime());
        articleList.add(a1);
        latestId++;

        ObjectMapper mapper = new ObjectMapper();

        try {
            // 객체를 JSON 형태로 변환하여 파일에 저장
            mapper.writeValue(new File("article.json"), articleList);
            System.out.println("객체가 JSON 형태로 파일에 저장되었습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Article> findAll() {
        // json 파일을 읽어와서 ArrayList로 반환
    }

}
