package com.shopping.content;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.shopping.content.mapper")
public class ContentMsApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(ContentMsApp.class,args);
    }
}
