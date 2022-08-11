package org.xjt.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xjt.bean.User;
import org.xjt.service.UserService;

@Slf4j
@Controller
public class TableController {
    @Autowired
    private UserService userService;

    @GetMapping("/pages/tables/simple")
    public String pagesTablesSimple(){
        return "pages/tables/simple";
    }

    @GetMapping("/pages/tables/data")
    public String pagesTablesData(@RequestParam(value = "page",defaultValue = "1") String page, Model model){
        Page<User> pagination = new Page<>(Integer.parseInt(page), 3);
        Page<User> usersPage = userService.page(pagination);
        model.addAttribute("usersPage",usersPage);
        log.info("users当前页码=={}，总页数=={}，总记录数=={}，记录列表=={}",usersPage.getCurrent(),usersPage.getPages(),usersPage.getTotal(),usersPage.getRecords());

        return "pages/tables/data";
    }

    @GetMapping("/pages/tables/jsgrid")
    public String pagesTablesJsgrid(){
        return "pages/tables/jsgrid";
    }

}
