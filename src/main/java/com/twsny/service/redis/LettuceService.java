package com.twsny.service.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class LettuceService {
    private final RedisTemplate<String, String> template;

    public LettuceService(RedisTemplate<String, String> template) {
        this.template = template;
    }

    public void setStr(String value) {
        template.opsForValue().set("test", value);
        template.expire("test", 5, TimeUnit.MINUTES);
    }

    public String getStr() {
        return template.opsForValue().get("test");
    }
}
