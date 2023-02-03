package com.online.shopping;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.online.shopping.mapper")
@EnableEurekaClient
public class PageMsApp {
    public static void main(String[] args) {
        SpringApplication.run(PageMsApp.class,args);
    }
}
