package org.example.base.json;

import org.example.base.file.FileUtil;
import org.example.domain.article.entity.Article;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ArticleJsonUtil {
    private static final String META_PATH = "data/article/meta/meta.json";
    private static final String BASE_PATH = "data/article/";
    private static final String FILE_NAME_PREFIX = "article_";
    private static final String FILE_NAME_SUFFIX = ".json";

    public static void writeArticleWithJackson(Article article) {

        assert article != null;

        String path = getArticleFilePath(article.getId());
        String json = JsonUtil.getJsonStr(article);

        if(!FileUtil.isExistFile(getArticleFilePath(article.getId())))  {
            int id = getCreatedForArticleId();
            article.setId(id);

            path = getArticleFilePath(id);
            json = JsonUtil.getJsonStr(article);
        }

        JsonUtil.writeWithJackson(path, json);

    }

    public static Article readArticleWithJackson(int id) {
        assert id > 0;

        String json = JsonUtil.readWithJackson(getArticleFilePath(id));
        return JsonUtil.getObjectFromJson(json, Article.class);
    }

    public static List<Article> readArticleListWithJackson() {

        List<File> files = FileUtil.getFiles(BASE_PATH);
        List<Article> articles = new ArrayList<>();

        for (File file : files) {
            String json = JsonUtil.readWithJackson(file.getPath());
            Article article = JsonUtil.getObjectFromJson(json, Article.class);
            articles.add(article);
        }

        return articles;
    }

    public static void deleteArticle(Article article) {
        String path = getArticleFilePath(article.getId());
        if (FileUtil.isExistFile(path)) {
            FileUtil.deleteFile(path);
        }
    }

    public static String getArticleFileName(int id) {
        return FILE_NAME_PREFIX.concat(String.valueOf(id))
                .concat(FILE_NAME_SUFFIX);
    }

    public static String getArticleFilePath(int id) {
        return BASE_PATH.concat(getArticleFileName(id));
    }

    public static int getLatestArticleId() {
        final int START_ID = 0;

        if (!FileUtil.isExistFile(META_PATH)) {
            return START_ID;
        }

        return Integer.parseInt(JsonUtil.readWithJackson(META_PATH));
    }

    public static int getCreatedForArticleId() {
        int latestId = getLatestArticleId() + 1;
        JsonUtil.writeWithJackson(META_PATH, String.valueOf(latestId));

        return latestId;
    }
}
