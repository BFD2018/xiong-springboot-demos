package com.example.springbootmail.utils;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.extra.mail.MailUtil;
import org.junit.jupiter.api.Test;

public class MyMailUtils {
    public static void main(String[] args) {
        String to = "1351655382@qq.com";
//        String to = "xjt2022@petalmail.com";
        String code = RandomUtil.randomString(4);
        String content = "您正在登陆小熊的空间，确认验证码为：" + "【" + code + "】";

        MailUtil.sendText(to, "这是一封测试邮件", content);
    }


    @Test
    public void test01() {
        System.out.println(RandomUtil.randomString(4));

        System.out.println(RandomUtil.randomNumbers(4));
    }
}
