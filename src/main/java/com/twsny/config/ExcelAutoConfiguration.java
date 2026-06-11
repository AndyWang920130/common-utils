package com.twsny.config;

import com.twsny.service.ExcelService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
public class ExcelAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ExcelService excelService() {
        return new ExcelService();
    }
}
