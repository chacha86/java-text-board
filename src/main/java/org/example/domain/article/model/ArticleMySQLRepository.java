package org.example.domain.article.model;

import java.util.ArrayList;

public class ArticleMySQLRepository implements Repository {
    @Override
    public void makeTestData() {

    }

    @Override
    public ArrayList<Article> findArticleByKeyword(String keyword) {
        return null;
    }

    @Override
    public Article findArticleById(int id) {
        return null;
    }

    @Override
    public void deleteArticle(Article article) {

    }

    @Override
    public void updateArticle(Article article, String newTitle, String newBody) {

    }

    @Override
    public ArrayList<Article> findAll() {
        return null;
    }

    @Override
    public Article saveArticle(String title, String body) {

        // article 테이블에 게시물 저장


        return null;
    }
}
