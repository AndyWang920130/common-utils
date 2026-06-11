package com.twsny.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(CustomRedisProperties.class)
public class RedissonAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public RedissonClient redissonClient(CustomRedisProperties properties) {

        Config config = new Config();

        String address = "redis://" + properties.getHost()
                + ":" + properties.getPort();

        config.useSingleServer()
                .setAddress(address)
                .setPassword(
                        (properties.getPassword() == null || properties.getPassword().isEmpty())
                                ? null
                                : properties.getPassword()
                );

        return Redisson.create(config);
    }
}
