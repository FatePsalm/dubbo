package com.solace.mqtransactionservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo
//MQ定时投递开启
//@EnableScheduling
public class MqTransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqTransactionServiceApplication.class, args);
    }

}
