package com.xjt.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xjt.admin.bean.User;
import com.xjt.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class TableController {
    @GetMapping("/basic_table")
    public String basic_table() {
        //模拟异常
        int i = 10 / 0;

        return "table/basic_table";
    }

    @Autowired
    UserService userService;

    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value="pn",defaultValue = "1") Integer pn, Model model) {
        //表格内容的遍历
        //response.sendError
//        List<User> users = Arrays.asList(
//                new User("zhangsan", "123456"),
//                new User("lisi", "123444"),
//                new User("haha", "aaaaa"),
//                new User("hehe ", "aaddd"));
//        model.addAttribute("users", users);

        Page<User> page = new Page<User>(pn,3);
        Page<User> userPage = userService.page(page);
        log.info("分页查找用户===>{}",userPage);
        model.addAttribute("userPage",userPage);

        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id,
                             @RequestParam(value = "pn",defaultValue = "1")Integer pn,
                             RedirectAttributes ra){

        userService.removeById(id);

        ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table() {
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table() {
        return "table/editable_table";
    }

}
