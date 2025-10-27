package com.sujay.journalApplication.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sujay.journalApplication.api.response.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(String Key, Class<T> entityClass) {
        try {
            // every class has Object as superclass
            Object object = redisTemplate.opsForValue().get(Key);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(object.toString(), entityClass);
        } catch (Exception e) {
            log.error("Exception", e);
            return null;
        }
    }

    public void set(String key, Object object, Long TTL) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonValue = objectMapper.writeValueAsString(object);
            redisTemplate.opsForValue().set(key, jsonValue, TTL, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }
}
