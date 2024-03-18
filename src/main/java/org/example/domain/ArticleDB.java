package org.example.domain;

import java.util.List;

public interface ArticleDB {
    void save(Article article);
    void delete(Article article);
    Article findById(int id);
    List<Article> findAll();
}
