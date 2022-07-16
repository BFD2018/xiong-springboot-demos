package com.baiduai.face_recognition.controller;

import com.baiduai.face_recognition.service.UserFaceService;
import com.baiduai.face_recognition.utils.BaiduAiUtils;
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
    public String toRegisterPage() {
        return "register";
    }

    @GetMapping("/page/login")
    public String toLoginPage() {
        return "login";
    }

    @GetMapping("/page/home")
    public String toHomePage() {
        return "home";
    }

    @GetMapping("/index")
    public String toIndexPage() {
        return "index.html";
    }


    /**
     * 处理登录逻辑
     *
     * @param imagebase64
     * @param model
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public RespBean handleLogin(@RequestParam(name = "imagebase64") String imagebase64, Model model) {
        RespBean respBean = userFaceService.loginByFace(imagebase64);
        System.out.println("respBean======>" + respBean);
        model.addAttribute("respBean", respBean);
        return respBean;
    }

    /**
     * 处理人脸注册
     *
     * @param imagebase64 base64图片
     * @param userinfo    用户信息
     * @return
     * @throws Exception
     */
    @PostMapping("/register")
    public String registerFace(@RequestParam(name = "imagebase64") String imagebase64, @RequestParam(name = "userinfo") String userinfo, Model model) throws Exception {
        RespBean respBean = userFaceService.registerFace(imagebase64, userinfo);
        model.addAttribute("response", respBean);

        return "registerPage";
    }

    @Autowired
    private BaiduAiUtils baiduAiUtils;

    @GetMapping("/test")
    @ResponseBody
    public RespBean test1() {
        RespBean respBean = baiduAiUtils.faceSearch("https://xiong-test-srt.oss-cn-shenzhen.aliyuncs.com/typoraimages/20220629223736.png", "URL");
        return respBean;
    }

}
