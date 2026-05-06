package com.twsny.service.redis;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

@Service
public class TestRedissonService {
    private final RedissonClient redissonClient;

    public TestRedissonService(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    public <T> T executeWithLock(Supplier<T> supplier){
        RLock lock = redissonClient.getLock("lock:test");
        boolean locked;
        try {
            locked = lock.tryLock(0, 10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException("获取锁失败", e);
        }

        if (!locked) {
            throw new RuntimeException("已有任务执行中");
        }

        try {
            return supplier.get();
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
    }
}
