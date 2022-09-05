package com.gongdaeoppa.demo8888888888888.service;

import com.gongdaeoppa.demo8888888888888.dao.ArticleDao;
import com.gongdaeoppa.demo8888888888888.dto.Article;
import com.gongdaeoppa.demo8888888888888.dto.Board;
import com.gongdaeoppa.demo8888888888888.dto.ResultData;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ArticleService {

//    private List<Article> articles;
//    private int articleLastId;
//
//    public ArticleService() {
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
//    public boolean deleteArticleById(int id) {
//        Article article = getArticleById(id);
//
//        if (article == null) {
//            return false;
//        }
//
//        articles.remove(article);
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






    private final ArticleDao articleDao;

    public ResultData modifyArticle(int id, String title, String body) {
        Article article = getArticleById(id);

        //if ( article == null ) {
        if (isEmpty(article)) {
            return new ResultData("F-1", "존재하지 않는 게시물 번호입니다.", "id", id);
        }

        articleDao.modifyArticle(id, title, body);

        return new ResultData("S-1", "게시물이 수정되었습니다.", "id", id);
    }

    public ResultData deleteArticleById(int id) {
        Article article = getArticleById(id);

        //if (article == null) {
        if (isEmpty(article)) {
            return new ResultData("F-1", "게시물이 존재하지 않습니다.", "id", id);
        }

        articleDao.deleteArticleById(id);

        //return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id);
        return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.", "id", id, "boardId", article.getBoardId());
    }

    //public ResultData writeArticle(String title, String body) {
    public ResultData writeArticle(int boardId, int memberId, String title, String body) {
        //int id = articleDao.writeArticle(title, body);
        //int boardId = 3; // 가짜 데이터
        //int memberId = 3; // 가짜 데이터
        articleDao.writeArticle(boardId, memberId, title, body);
        //int id = 1; // 가짜 데이터
        int id = articleDao.getLastInsertId();

        return new ResultData("S-1", "게시물이 작성되었습니다.", "id", id);
    }

    public Article getArticleById(int id) {
        return articleDao.getArticleById(id);
    }


    private boolean isEmpty(Article article) {
        if (article == null) {
            return true;
        } else if (article.isDelStatus()) {
            return true;
        }

        return false;
    }


    public Board getBoardById(int id) {
        return articleDao.getBoardById(id);
    }


    public int getArticlesTotalCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
        if (searchKeyword != null && searchKeyword.length() == 0) {
            searchKeyword = null;
        }
        return articleDao.getArticlesTotalCount(boardId, searchKeywordTypeCode, searchKeyword);
    }




    public List<Article> getForPrintArticles(int boardId, String searchKeywordTypeCode, String searchKeyword, int itemsCountInAPage, int page) {
        if (searchKeyword != null && searchKeyword.length() == 0) {
            searchKeyword = null;
        }
        int limitFrom = (page - 1) * itemsCountInAPage;
        int limitTake = itemsCountInAPage;

        return articleDao.getForPrintArticles(boardId, searchKeywordTypeCode, searchKeyword, limitFrom, limitTake);
    }





    public Article getForPrintArticleById(int id) {
        return articleDao.getForPrintArticleById(id);
    }


    public List<Article> getArticles(){
        return articleDao.getArticles();
    }


    public List<Article> getArticles10(){
        return articleDao.getArticles10();
    }

    public List<Map<String, Object>> getRelId(){
        return articleDao.getRelId();
    }




}