package com.xjt.controller;

import com.xjt.domain.Author;
import com.xjt.domain.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;


@Controller
@RequestMapping("/hello")
public class HelloCongtroller {
    private final static Logger logger = LoggerFactory.getLogger(HelloCongtroller.class);

    @GetMapping("test01")
    public String test01(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);        //{md={gender=男, age=99}}

        int i = 10 / 0;

        return "hello";
    }

    @ResponseBody
    @GetMapping("test02")
    public void test02(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);        //{md={gender=男, age=99}}
    }

    // localhost:8080/hello/addbook?book.name=三国演义&book.price=99&author.name=罗贯中&author.age=120
    @ResponseBody
    @PostMapping("/addbook")
    public void addBook(@ModelAttribute("book") Book book, @ModelAttribute("author") Author author) {
        logger.warn(book.toString());
        logger.warn(author.toString());
    }

    @Scheduled(cron = "0/2 * * * * *")
    @ResponseBody
    @GetMapping("cron")
    public void testCron() {
        logger.warn(new Date().toString());
    }
}
