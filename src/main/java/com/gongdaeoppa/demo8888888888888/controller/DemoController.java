package com.gongdaeoppa.demo8888888888888.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DemoController {


    @RequestMapping("/demo")
    public String list(@RequestParam Map<String,Object> map,
                       @RequestParam(value="chk[]", required = false) List<String> checklist) {
        System.out.println("map = " + map);
        System.out.println("checklist = " + checklist);
        return "/demo/list";
    }

    @RequestMapping("/ajax")
    public String listajax(@RequestBody Map<String,Object> param){
        System.out.println("param = " + param);
        System.out.println("param.get(\"name\") = " + param.get("name"));
        System.out.println("param.get(\"key\") = " + param.get("key"));

        ArrayList<String> keys = (ArrayList<String>) param.get("key");
        for (String key : keys) {
            System.out.println("key = " + key);
        }
        
        Map<String,String> keys1 = (Map<String,String>) param.get("status");
        for (String s : keys1.keySet()) {
            System.out.println("s = " + s);
        }
        for (String s : keys1.values()) {
            System.out.println("s = " + s);
        }
        
        return "/demo/list";
    }

//    {
//        "name" : "111",
//        "name2" : "222",
//        "key" : ["test","test2"],
//        "status":
//        {
//            "location":"E1",
//             "mode":"2"
//        }
//    }



}
