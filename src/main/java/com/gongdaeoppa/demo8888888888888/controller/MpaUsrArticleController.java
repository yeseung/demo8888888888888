package com.gongdaeoppa.demo8888888888888.controller;

import com.gongdaeoppa.demo8888888888888.dto.*;
import com.gongdaeoppa.demo8888888888888.service.ArticleService;
import com.gongdaeoppa.demo8888888888888.service.ReplyService;
import com.gongdaeoppa.demo8888888888888.util.Util;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Stream;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MpaUsrArticleController {


    private final ArticleService articleService;
    private final ReplyService replyService;

    //private int articleLastId = 0;


//    private List<Article> articles;
//    private int articleLastId;
//
//    public MpaUsrArticleController() {
//
//        articles = new ArrayList<>();
//        articleLastId = 0;
//        makeTestData();
//    }




    @RequestMapping("/mpaUsr/article/doWrite")
    //@ResponseBody
    //public Article doWrite(String title, String body) {
    //public Map<String, Object> doWrite(String title, String body) {
    //public ResultData doWrite(String title, String body) {
    public String doWrite(HttpServletRequest req,
                          int boardId,
                          String title,
                          String body) {


//        Req rq = (Req)req.getAttribute("req");
//
//        if ( rq.isNotLogined() ) {
//            return Util.msgAndBack(req, "로그인 후 이용해주세요.");
//        }


        if ( Util.isEmpty(title) ) {
            //return new ResultData("F-1", "제목을 입력해주세요.");
            return Util.msgAndBack(req, "제목을 입력해주세요.");
        }

        if ( Util.isEmpty(body) ) {
            //return new ResultData("F-2", "내용을 입력해주세요.");
            return Util.msgAndBack(req, "내용을 입력해주세요.");
        }


        //int id = articleService.writeArticle(title, body);
        //Article article = articleService.getArticleById(id);

        //return new ResultData("S-1", id + "번 글이 작성되었습니다.", "article", article);
        //return articleService.writeArticle(title, body);


        Rq rq = (Rq) req.getAttribute("rq");
        int memberId = rq.getLoginedMemberId();

        //int memberId = 1; // 임시

        //ResultData writeArticleRd = articleService.writeArticle(title, body);
        ResultData writeArticleRd = articleService.writeArticle(boardId, memberId, title, body);



        if ( writeArticleRd.isFail() ) {
            return Util.msgAndBack(req, writeArticleRd.getMsg());
        }

        String replaceUri = "detail?id=" + writeArticleRd.getBody().get("id");
        return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUri);
    }


//    private int writeArticle(String title, String body) {
//
//        int id = articleLastId + 1;
//        String regDate = Util.getNowDateStr();
//        String updateDate = Util.getNowDateStr();
//
//        Article article = new Article(id, regDate, updateDate, title, body);
//
//
//
////        Map<String, Object> rsData = new HashMap<>();
////        rsData.put("resultCode", "S-1");
////        rsData.put("msg", id + "번 글이 작___성되었습니다.");
////        rsData.put("article", article);
//
//        //return article;
//        //return rsData;
//        //return new ResultData("S-1", id + "번 글이 작성되었습니다.", article);
//        //return new ResultData("S-1",
//                //id + "번 글이 작성되었습니다.",
//                //"article___", article);
//
//        articles.add(article);
//
//        articleLastId = id;
//
//        return id;
//    }






    @RequestMapping("/mpaUsr/article/getArticle")
    @ResponseBody
    //public ResultData getArticle(int id) {
    public ResultData getArticle(Integer id) {
        if ( Util.isEmpty(id) ) {
            return new ResultData("F-1", "번호를 입력해주세요.");
        }

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return new ResultData("F-1", id + "번 글은 존재하지 않습니다.", "id", id);
        }

        return new ResultData("S-1", article.getId() + "번 글 입니다.", "article", article);
    }

//    private Article getArticleById(int id) {
//        for (Article article : articles) {
//            if (article.getId() == id) {
//                return article;
//            }
//        }
//
//        /*
//         * for ( int i = 0; i < articles.size(); i++ ) { Article article =
//         * articles.get(i);
//         *
//         * if ( article.getId() == id ) { return article; } }
//         */
//
//        return null;
//    }








    // 내부
//    private void makeTestData() {
//        for ( int i = 0; i < 3; i++ ) {
//            writeArticle("제목 " + UUID.randomUUID().toString(), "내용 " + UUID.randomUUID().toString());
//        }
//    }

//    private boolean deleteArticleById(int id) {
////        for (Article article : articles) {
////            if (article.getId() == id) {
////                articles.remove(article);
////                return true;
////            }
////        }
////        return false;
//
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
//    private int writeArticle(String title, String body) {
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








    @RequestMapping("/mpaUsr/article/doDelete")
    //@ResponseBody
    //public ResultData doDelete(int id) {
    //public ResultData doDelete(Integer id) {
    public String doDelete(HttpServletRequest req, Integer id) {
        if ( Util.isEmpty(id) ) {
            //return new ResultData("F-1", "번호를 입력해주세요.");
            return Util.msgAndBack(req, "id를 입력해주세요.");
        }

//        boolean deleted = articleService.deleteArticleById(id);
//
//        if (deleted == false) {
//            return new ResultData("F-1", id + "번 글이 존재하지 않습니다.", "id", id);
//        }
//
//        return new ResultData("S-1", id + "번 글이 삭제되었습니다.", "id", id);

        //return articleService.deleteArticleById(id);


        ResultData rd = articleService.deleteArticleById(id);

        if ( rd.isFail() ) {
            return Util.msgAndBack(req, rd.getMsg());
        }

        String redirectUri = "../article/list?boardId=" + rd.getBody().get("boardId");

        return Util.msgAndReplace(req, rd.getMsg(), redirectUri);
    }






//    private boolean modifyArticle(int id, String title, String body) {
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






    @RequestMapping("/mpaUsr/article/doModify")
    @ResponseBody
    //public ResultData doModify(int id, String title, String body) {
    public ResultData doModify(Integer id, String title, String body) {

        if ( Util.isEmpty(id) ) {
            return new ResultData("F-1", "번호를 입력해주세요.");
        }

        if ( Util.isEmpty(title) ) {
            return new ResultData("F-2", "제목을 입력해주세요.");
        }

        if ( Util.isEmpty(body) ) {
            return new ResultData("F-3", "내용을 입력해주세요.");
        }

//        boolean modified = articleService.modifyArticle(id, title, body);
//
//        if (modified == false) {
//            return new ResultData("F-1", id + "번 글이 존재하지 않습니다.", "id", id);
//        }
//
//        return new ResultData("S-1", id + "번 글이 수정되었습니다.", "article", articleService.getArticleById(id));

        Article article = articleService.getArticleById(id);

        if (article == null) {
            return new ResultData("F-4", "존재하지 않는 게시물 번호입니다.");
        }

        return articleService.modifyArticle(id, title, body);
    }




    @RequestMapping("/mpaUsr/article/list")
    //public String showList(HttpServletRequest req,  int boardId) {
    //public String showList(HttpServletRequest req, int boardId, @RequestParam(defaultValue = "1") int page) {
    public String showList(HttpServletRequest req,
                           @RequestParam(defaultValue = "1") int boardId,
                           String searchKeywordType,
                           String searchKeyword,
                           @RequestParam(defaultValue = "1") int page) {

        Board board = articleService.getBoardById(boardId);

        log.debug(" ************************ searchKeyword : " + searchKeyword);


        if ( Util.isEmpty(searchKeywordType) ) {
            searchKeywordType = "titleAndBody";
        }


        if (board == null) {
            //return "존재하지 않는 게시판 입니다.";
            //req.setAttribute("msg", boardId + "번 게시판이 존재하지 않습니다.");
            //return "common/redirect";
            return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        //int totalItemsCount = articleService.getArticlesTotalCount(boardId);

        int totalItemsCount = articleService.getArticlesTotalCount(boardId, searchKeywordType, searchKeyword);

        if ( searchKeyword == null || searchKeyword.trim().length() == 0 ) {

        }


        req.setAttribute("totalItemsCount", totalItemsCount);


        // 한 페이지에 보여줄 수 있는 게시물 최대 개수
        int itemsCountInAPage = 3;
        // 총 페이지 수
        int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);

        // 현재 페이지(임시)
        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);

        //List<Article> articles = articleService.getForPrintArticles(boardId, itemsCountInAPage, page);
        List<Article> articles = articleService.getForPrintArticles(boardId, searchKeywordType, searchKeyword, itemsCountInAPage, page);

        System.out.println("articles : " + articles);

        req.setAttribute("articles", articles);

        return "mpaUsr/article/list";
    }





//    private String msgAndBack(HttpServletRequest req, String msg) {
//        req.setAttribute("msg", msg);
//        req.setAttribute("historyBack", true);
//        return "common/redirect";
//    }
//
//    private String msgAndReplace(HttpServletRequest req, String msg, String replaceUrl) {
//        req.setAttribute("msg", msg);
//        req.setAttribute("replaceUrl", replaceUrl);
//        return "common/redirect";
//    }








    @RequestMapping("/mpaUsr/article/write")
    public String showWrite(HttpServletRequest req,
                            @RequestParam(defaultValue = "1") int boardId) {



//        Req rq = (Req)req.getAttribute("req");
//        if ( rq.isNotLogined() ) {
//            return Util.msgAndBack(req, "로그인 후 이용해주세요-____-;;");
//        }

        Board board = articleService.getBoardById(boardId);

        if (board == null) {
            return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
        }

        req.setAttribute("board", board);

        return "mpaUsr/article/write";
    }




    @RequestMapping("/mpaUsr/article/detail")
    public String showDetail(HttpServletRequest req, int id) {
        //Article article = articleService.getArticleById(id);
        Article article = articleService.getForPrintArticleById(id);

        List<Reply> replies = replyService.getForPrintRepliesByRelTypeCodeAndRelId("article", id);

        if (article == null) {
            return Util.msgAndBack(req, id + "번 게시물이 존재하지 않습니다______.");
        }

        Board board = articleService.getBoardById(article.getBoardId());

        req.setAttribute("article", article);

        System.out.println("article = " + article);

        req.setAttribute("board", board);


        req.setAttribute("replies", replies);


        return "mpaUsr/article/detail";
    }







//    @RequestMapping("/mpaUsr/article/1")
//    @ResponseBody
//    public Stream<Articles1> showStream() {
//        List<Article> articles = articleService.getArticles();
//        return articles.stream().map(entity -> new Articles1(entity));
//    }

}
