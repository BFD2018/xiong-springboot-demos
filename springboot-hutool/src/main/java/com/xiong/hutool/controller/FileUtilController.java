package com.xiong.hutool.controller;

import cn.hutool.core.io.FileUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("fileUtil")
public class FileUtilController {

    @GetMapping("/writeToStream")
    private void testWriteToStream(HttpServletResponse response) throws IOException {
        File file = FileUtil.file("d:\\图片\\111.jpg");
        ServletOutputStream outputStream = response.getOutputStream();

        outputStream.write(FileUtil.readBytes(file));
        outputStream.flush();
        outputStream.close();
    }
}
