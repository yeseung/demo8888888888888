package com.gongdaeoppa.demo8888888888888.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UsrHomeController {


    @RequestMapping("/usr/home/main")
    @ResponseBody
    public String showMain (){
        System.out.println("1111 = " + 1111);
        return "hello";
    }

//    @RequestMapping("/ttt")
//    @ResponseBody
//    public Map showTtt (String q1){
//        return Map.of("qq", q1);
//    }


}
