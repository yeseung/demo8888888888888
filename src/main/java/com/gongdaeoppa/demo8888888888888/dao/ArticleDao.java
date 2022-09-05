package com.gongdaeoppa.demo8888888888888.dao;

import com.gongdaeoppa.demo8888888888888.dto.Article;
import com.gongdaeoppa.demo8888888888888.dto.Board;
import com.gongdaeoppa.demo8888888888888.util.Util;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Component
@Mapper
public interface ArticleDao {
//    private List<Article> articles;
//    private int articleLastId;
//
//    public ArticleDao() {
//        articles = new ArrayList<>();
//        articleLastId = 0;
//        makeTestData();
//    }
//
//    public void makeTestData() {
//        for (int i = 0; i < 3; i++) {
//            writeArticle("제목1", "내용1");
//        }
//    }
//
//    public boolean modifyArticle(int id, String title, String body) {
//        Article article = getArticleById(id);
//
//        if (article == null) {
//            return false;
//        }
//
//        article.setUpdateDate(Util.getNowDateStr());
//        article.setTitle(title);
//        article.setBody(body);
//
//        return true;
//    }
//
//    public int writeArticle(String title, String body) {
//        int id = articleLastId + 1;
//        String regDate = Util.getNowDateStr();
//        String updateDate = Util.getNowDateStr();
//
//        Article article = new Article(id, regDate, updateDate, title, body);
//        articles.add(article);
//
//        articleLastId = id;
//
//        return id;
//    }
//
//    public Article getArticleById(int id) {
//        for (Article article : articles) {
//            if (article.getId() == id) {
//                return article;
//            }
//        }
//
//        return null;
//    }
//
//    public void deleteArticleById(int id) {
//        Article article = getArticleById(id);
//
//        articles.remove(article);
//    }




    boolean modifyArticle(@Param("id") int id,
                          @Param("title") String title,
                          @Param("body") String body);

//    @Insert("INSERT INTO article\n" +
//            "        SET regDate = NOW(),\n" +
//            "        updateDate = NOW(),\n" +
//            "        boardId = #{boardId},\n" +
//            "        memberId = #{memberId},\n" +
//            "        title = #{title},\n" +
//            "        body = #{body}")
    void writeArticle(@Param("boardId") int boardId,
                      @Param("memberId") int memberId,
                      @Param("title") String title,
                      @Param("body") String body);



    Article getArticleById(@Param("id") int id);

    void deleteArticleById(@Param("id") int id);

    int getLastInsertId();





    Board getBoardById(@Param("id") int id);


    //int getArticlesTotalCount(@Param("boardId") int boardId);
    int getArticlesTotalCount(@Param("boardId") int boardId,
                              @Param("searchKeywordTypeCode") String searchKeywordType,
                              @Param("searchKeyword") String searchKeyword);


    List<Article> getForPrintArticles(@Param("boardId") int boardId,
                                      @Param("searchKeywordTypeCode") String searchKeywordType,
                                      @Param("searchKeyword") String searchKeyword,
                                      @Param("limitFrom") int limitFrom,
                                      @Param("limitTake") int limitTake);



    Article getForPrintArticleById(@Param("id") int id);


    List<Article> getArticles();





    List<Article> getArticles10();


    List<Map<String, Object>> getRelId();
}