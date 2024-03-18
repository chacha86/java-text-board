package org.example.base.dbms;

import org.example.domain.article.entity.Article;

import java.util.List;

public class SqlMapper {
    DBUtil dbUtil = new DBUtil();
    public List<Article> findAll() {
        String sql = "SELECT * FROM article";
        return dbUtil.getList(sql, new MyArticleMapper());
    }

    public Article findById(int id) {
        String sql = "SELECT * FROM article WHERE id = ?";
        return dbUtil.getOne(sql, new MyArticleMapper(), id);
    }

    public void save(String title, String content, int hit) {
        String sql = "INSERT INTO article (title, body, hit) VALUES (?, ?, ?)";
        dbUtil.execute(sql, title, content, hit);
    }

    public void update(int id, String title, String content, int hit) {
        String sql = "UPDATE article SET title = ?, body = ?, hit = ? WHERE id = ?";
        dbUtil.execute(sql, title, content, hit, id);
    }
    public void delete(int id) {
        String sql = "DELETE FROM article WHERE id = ?";
        dbUtil.execute(sql, id);
    }
}
