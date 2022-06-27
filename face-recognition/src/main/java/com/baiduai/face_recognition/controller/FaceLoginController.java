package com.baiduai.face_recognition.controller;

import com.baiduai.face_recognition.service.UserFaceService;
import com.baiduai.face_recognition.utils.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/face")
public class FaceLoginController {
    @Autowired
    private UserFaceService userFaceService;

    @GetMapping("/page/register")
    public String toRegisterPage(){
        return "registerPage.html";
    }

    @GetMapping("/page/login")
    public String toLoginPage(){
        return "loginPage.html";
    }

    @GetMapping("/index")
    public String toIndexPage(){
        return "index.html";
    }


    /**
     * 处理登录逻辑
     * @param imagebase64
     * @param model
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String handleLogin(@RequestParam(name = "imagebase64") String imagebase64, Model model) {
        RespBean respBean = userFaceService.loginByFace(imagebase64);
        System.out.println("==========>"+respBean);

        model.addAttribute("response",respBean);
        return "loginPage";
    }

    /**
     * 处理人脸注册
     * @param imagebase64 base64图片
     * @param userinfo  用户信息
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public String registerFace(@RequestParam(name = "imagebase64") String imagebase64, @RequestParam(name = "userinfo") String userinfo, Model model) throws Exception {
        RespBean respBean = userFaceService.registerFace(imagebase64, userinfo);
        model.addAttribute("response",respBean);

        return "registerPage";
    }

}
