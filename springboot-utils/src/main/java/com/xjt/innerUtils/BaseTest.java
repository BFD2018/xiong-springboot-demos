package com.xjt.innerUtils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class BaseTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testRedis() {
        System.out.println(redisTemplate);
    }
}
