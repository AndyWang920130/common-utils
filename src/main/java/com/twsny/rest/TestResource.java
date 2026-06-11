package com.twsny.rest;

import com.twsny.service.redis.LettuceService;
import com.twsny.service.redis.RedissonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/redis")
public class TestResource {
    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    private final RedissonService redissonService;

    private final LettuceService lettuceService;

    public TestResource(RedissonService redissonService, LettuceService lettuceService) {
        this.redissonService = redissonService;
        this.lettuceService = lettuceService;
    }

    @GetMapping("/sleep")
    public void sleep() {
        String threadName = Thread.currentThread().getName();
        log.info("threadName: {}, sleep", threadName);
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/sleep/withLock")
    public void sleepWithLock() {
        String threadName = Thread.currentThread().getName();
        log.info("threadName: {}, sleep withLock", threadName);
        redissonService.executeWithLock(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

    }

    @GetMapping("/lettuceSetStr")
    public void lettuceSetStr() {
        lettuceService.setStr("aaaa");
    }

    @GetMapping("/lettuceGetStr")
    public String lettuceGetStr() {
        return lettuceService.getStr();
    }

}
