package org.example.domain.article.model;

import java.util.ArrayList;

public abstract class Repository {
    public abstract void makeTestData();
    public abstract ArrayList<Article> findArticleByKeyword(String keyword);
    public abstract Article findArticleById(int id);
    public abstract void deleteArticle(Article article);

    public abstract void updateArticle(Article article, String newTitle, String newBody);
    public abstract ArrayList<Article> findAll();
    public abstract Article saveArticle(String title, String body);


}
