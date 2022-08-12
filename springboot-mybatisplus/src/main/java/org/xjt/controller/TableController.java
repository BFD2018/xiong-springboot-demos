package org.xjt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.xjt.bean.User;
import org.xjt.service.UserService;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Controller
public class TableController {
    @Autowired
    private UserService userService;

    @GetMapping("/pages/tables/simple")
    public String pagesTablesSimple() {
        return "pages/tables/simple";
    }

    @GetMapping("/pages/tables/data")
    public String pagesTablesData(@RequestParam(value = "page", defaultValue = "1") String page, Model model) {
        Page<User> pagination = new Page<>(Integer.parseInt(page), 3);
        Page<User> usersPage = userService.page(pagination);
        model.addAttribute("usersPage", usersPage);
        log.info("users当前页码=={}，总页数=={}，总记录数=={}，记录列表=={}", usersPage.getCurrent(), usersPage.getPages(), usersPage.getTotal(), usersPage.getRecords());

        return "pages/tables/data";
    }

    @GetMapping("/user/delete")
    public String userDelete(@RequestParam("userid") String userid, @RequestParam("page") String page) {
        boolean b = userService.removeById(userid);

        return "redirect:/pages/tables/data?page=" + page;
    }

    @PostMapping("/user/add")
    public String userAdd(@RequestParam("name") String name,
                          @RequestParam("age") String age,
                          @RequestParam("email") String email,
                          @RequestPart("avatar") MultipartFile avatar
    ) throws IOException {
        String originalFilename = avatar.getOriginalFilename();
        String newFileName = UUID.randomUUID().toString().replace("-", "") + "-" + originalFilename;
        String uploadDir = System.getProperty("user.dir") + "\\src\\main\\resources\\public\\myupload\\";
        File file = new File(uploadDir);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
        String fileurl = uploadDir + newFileName;
        avatar.transferTo(new File(fileurl));

        User user = new User().setName(name).setAge(Integer.parseInt(age)).setEmail(email).setAvatar("/myupload/" + newFileName);
        log.info("user===>{}", user);
        boolean save = userService.save(user);

        if (save) {
            return "redirect:/pages/tables/data";
        } else {
            return "redirect:/error";
        }
    }

    @GetMapping("/pages/tables/jsgrid")
    public String pagesTablesJsgrid() {
        return "pages/tables/jsgrid";
    }

}
