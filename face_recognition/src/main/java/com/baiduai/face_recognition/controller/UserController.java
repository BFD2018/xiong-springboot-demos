package com.baiduai.face_recognition.controller;

import com.baiduai.face_recognition.service.UserFaceService;
import com.baiduai.face_recognition.utils.BaiduAiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/face")
public class UserController {
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
        //获取token
        new BaiduAiUtils().getAuth();

        return "index.html";
    }


    /**
     * 处理登录逻辑
     * @param faceBase
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String handleLogin(@RequestParam(name = "imagebase64") String imagebase64, HttpServletRequest request) {
        String res = userFaceService.loginByFace(imagebase64);
        System.out.println("==========>"+res);

        return res;
    }

    /**
     * 人脸注册
     * @param imagebase64 base64图片
     * @param request
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    @ResponseBody
    public String registerFace(@RequestParam(name = "imagebase64") StringBuffer imagebase64, HttpServletRequest request) throws Exception {
        String str = userFaceService.registerFace("1004",imagebase64,request);

        return str;
    }

}
