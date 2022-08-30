package com.xjt.source;

import com.xjt.source.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTests {

    @Autowired
    private Account account;

    @Test
    public void tt(){
        System.out.println(account.getName());
    }

}
