package com.gongdaeoppa.demo8888888888888.controller;

import com.gongdaeoppa.demo8888888888888.dto.Article;
import com.gongdaeoppa.demo8888888888888.service.ArticleService;
import com.gongdaeoppa.demo8888888888888.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class MpaUsrHomeController {

    private final ArticleService articleService;

//    @RequestMapping("/mpaUsr/home/main")
//    @ResponseBody
//    public String showMain() {
//        return "안녕";
//    }
//
//    @RequestMapping("/mpaUsr/home/main2")
//    @ResponseBody
//    public String showMain2() {
//        return "잘가";
//    }
//
//    @RequestMapping("/mpaUsr/home/main3")
//    @ResponseBody
//    public int showMain3(int a, int b) {
//        return a + b;
//    }
//
//    @RequestMapping("/mpaUsr/home/main4")
//    @ResponseBody
//    public int showMain4(int a, int b) {
//        return a - b;
//    }
//
//    @RequestMapping("/mpaUsr/home/main5")
//    @ResponseBody
//    public String showMain5() {
//        return "-20";
//    }
//
//    @RequestMapping("/mpaUsr/home/main6")
//    @ResponseBody
//    public boolean showMain6() {
//        return 1 > 2;
//    }
//
//    @RequestMapping("/mpaUsr/home/main7")
//    @ResponseBody
//    public int[] showMain7() {
//        int[] arr = new int[] { 1, 2, 3 };
//        return arr;
//    }
//
//    @RequestMapping("/mpaUsr/home/main8")
//    @ResponseBody
//    public List<Integer> showMain8() {
//        List<Integer> l = new ArrayList<>();
//        l.add(10);
//        l.add(20);
//        l.add(30);
//
//        return l;
//    }
//
//    @RequestMapping("/mpaUsr/home/main9")
//    @ResponseBody
//    public Map<String, Object> showMain9() {
//        Map<String, Object> map = new HashMap<>();
//        map.put("철수나이", 22);
//        map.put("영희나이", 32);
//
//        List<Integer> l = new ArrayList<>();
//        l.add(10);
//        l.add(20);
//        l.add(30);
//
//        map.put("data", l);
//
//        return map;
//    }
//
//    private int num = 0;
//
//    @RequestMapping("/mpaUsr/home/main10")
//    @ResponseBody
//    public Map<String, Object> showMain10() {
//        num++;
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("숫자", num);
//        map.put("숫자1", UUID.randomUUID().toString());
//
//        return map;
//    }




    @RequestMapping("/mpaUsr/home/main")
    public String showMain(HttpServletRequest req) {
        System.out.println("======================== true = " + true);

        List<Article> articles10 = articleService.getArticles10();
        System.out.println("articles10 ================================ " + articles10);

        List<Map<String, Object>> relId = articleService.getRelId();

        List<Map<String, Object>> listMapInsert = new ArrayList<>();

        for(Map<String, Object> strMap : relId){
            //System.out.println(strMap.get("relId"));
            //System.out.println(strMap.get("cnt"));

            Map<String, Object> map = new HashMap<>();
            int id = Integer.parseInt(strMap.get("relId").toString());
            map.put("id", Integer.parseInt(strMap.get("relId").toString()));
            map.put("cnt", Integer.parseInt(strMap.get("cnt").toString()));
            map.put("board", articleService.getForPrintArticleById(id));

            listMapInsert.add(map);
        }


        req.setAttribute("articles", articles10);
        req.setAttribute("article", listMapInsert);

        return "mpaUsr/home/main";
    }



    @RequestMapping("/")
    public String showMainRoot() {
        return "redirect:/mpaUsr/home/main";
    }

}