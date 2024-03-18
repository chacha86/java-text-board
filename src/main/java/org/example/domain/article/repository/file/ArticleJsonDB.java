package org.example.domain.article.repository.file;

import org.example.base.json.ArticleJsonUtil;
import org.example.domain.article.entity.Article;
import org.example.domain.article.repository.ArticleDB;

import java.util.List;

public class ArticleJsonDB implements ArticleDB {
    public void save(Article article) {
        ArticleJsonUtil.writeArticleWithJackson(article);
    }

    public void delete(Article article) {
        ArticleJsonUtil.deleteArticle(article);
    }

    public Article findById(int id) {
        return ArticleJsonUtil.readArticleWithJackson(id);
    }

    public List<Article> findAll() {
        return ArticleJsonUtil.readArticleListWithJackson();
    }
}
