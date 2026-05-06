package com.twsny.rest;

import com.twsny.service.redis.TestRedissonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestResource {
    private final Logger log = LoggerFactory.getLogger(TestResource.class);

    private final TestRedissonService testRedissonService;

    public TestResource(TestRedissonService testRedissonService) {
        this.testRedissonService = testRedissonService;
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
        testRedissonService.executeWithLock(() -> {
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        });

    }
}
