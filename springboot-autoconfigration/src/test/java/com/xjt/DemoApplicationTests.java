package com.xjt;

import com.xjt.domain.Person;
import com.xjt.domain.PersonProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private Person person; //将person自动注入进来

    @Autowired
    private PersonProperties personProperties;

    @Test
    void contextLoads() {
//        System.out.println(person);

        System.out.println(personProperties);
    }
}
