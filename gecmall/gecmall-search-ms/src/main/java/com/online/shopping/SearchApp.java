package com.online.shopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SearchApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SearchApp.class,args);
    }
}
