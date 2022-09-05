package com.gongdaeoppa.demo8888888888888.controller;


import com.gongdaeoppa.demo8888888888888.dto.Test;
import com.gongdaeoppa.demo8888888888888.service.TestService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Controller
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

//    @PostConstruct
//    public void init() {
//        for (int i = 0; i < 2; i++) {
//            Test test = new Test();
//            test.setName("" + LocalDateTime.now() + " / " + UUID.randomUUID());
//            Long insert = testService.insert(test);
//            System.out.println("insert = " + insert);
//        }
//    }


    @PostConstruct
    public void init() {
        List<Test> testList = new ArrayList<>();
        LongStream.rangeClosed(1L, 100L)
                .forEach(i -> {
                    testList.add(
                            new Test(i, "title_" + i)
                    );
                });
        testList.forEach(System.out::println);
    }


    @GetMapping("/test")
    public String home(Model model) {

        //새 글
        Test test = new Test();
        test.setName("" + LocalDateTime.now() + " / " + UUID.randomUUID());
        Long insert = testService.insert(test);
        System.out.println(" #### insert = " + insert);
        System.out.println(" #### test = " + test);


        List<Test> list = testService.list();
        model.addAttribute("list", list);
        return "test";

    }
}