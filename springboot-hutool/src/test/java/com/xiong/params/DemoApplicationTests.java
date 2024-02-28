package com.xiong.params;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
        DateTime dt = DateUtil.parse("2020-5-6");
        System.out.println(dt.toString());

        System.out.println(DateUtil.now());
    }

}
