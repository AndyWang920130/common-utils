package com.twsny;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class Starter {
    public static void main(String[] args) {
        System.out.println("HELLO TWSNY!");
        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(Starter.class, args);
    }
}
