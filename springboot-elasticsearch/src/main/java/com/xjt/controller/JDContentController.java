package com.xjt.controller;

import com.xjt.service.JDContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class JDContentController {
    @Autowired
    private JDContentService jdContentService;

    @ResponseBody
    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword") String keyword) throws IOException {
        return jdContentService.parseContent(keyword);
    }

    @ResponseBody
    @GetMapping("/h_search/{keyword}/{pageIndex}/{pageSize}")
    public List<Map<String, Object>> highlightParse(@PathVariable("keyword") String keyword,
                                                    @PathVariable("pageIndex") Integer pageIndex,
                                                    @PathVariable("pageSize") Integer pageSize) throws IOException {
        return jdContentService.highlightSearch(keyword,pageIndex,pageSize);
    }
}
