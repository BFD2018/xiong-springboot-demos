package com.xjt.filesupload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class PageController {
    @GetMapping("/user/files")
    public String toUserFilesPage() {
        return "user-files";
    }

    @GetMapping("/bigfile")
    public String bigFilesPage() {
        return "bigFileUpload";
    }
}
