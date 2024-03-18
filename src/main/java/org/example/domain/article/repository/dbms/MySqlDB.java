package org.example.domain.article.repository.dbms;

import org.example.base.dbms.SqlMapper;
import org.example.domain.article.entity.Article;
import org.example.domain.article.repository.ArticleDB;

import java.util.List;

public class MySqlDB implements ArticleDB {
    SqlMapper sqlMapper = new SqlMapper();
    @Override
    public void save(Article article) {
        if(sqlMapper.findById(article.getId()) == null) {
            sqlMapper.save(article.getTitle(), article.getBody(), article.getHit());
            return;
        }
        sqlMapper.update(article.getId(), article.getTitle(), article.getBody(), article.getHit());
    }

    @Override
    public void delete(Article article) {
        sqlMapper.delete(article.getId());
    }

    @Override
    public Article findById(int id) {
        return sqlMapper.findById(id);
    }

    @Override
    public List<Article> findAll() {
        return sqlMapper.findAll();
    }
}
