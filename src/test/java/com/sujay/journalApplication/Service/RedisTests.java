package com.sujay.journalApplication.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testSendMail() {
        redisTemplate.opsForValue().set("email", "sujay@gmail.com");
        Object email = redisTemplate.opsForValue().get("email");


    }
}
