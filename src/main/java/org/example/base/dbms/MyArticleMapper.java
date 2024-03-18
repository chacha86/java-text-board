package org.example.base.dbms;

import org.example.domain.article.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyArticleMapper implements MyMapper<Article> {
    @Override
    public List<Article> getList(ResultSet rs) throws SQLException {
        List<Article> articles = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String title = rs.getString("title");
            String body = rs.getString("body");
            int hit = rs.getInt("hit");

            Article article = new Article(id, title, body, hit);
            articles.add(article);
        }

        return articles;
    }
}
