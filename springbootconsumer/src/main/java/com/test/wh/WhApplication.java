package com.test.wh;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@EnableDubboConfig
public class WhApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhApplication.class, args);
    }
}
