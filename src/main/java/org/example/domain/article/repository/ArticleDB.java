package org.example.domain.article.repository;

import org.example.domain.article.entity.Article;

import java.util.List;

public interface ArticleDB {
    void save(Article article);
    void delete(Article article);
    Article findById(int id);
    List<Article> findAll();
}
