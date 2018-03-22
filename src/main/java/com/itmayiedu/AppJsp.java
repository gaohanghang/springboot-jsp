package com.itmayiedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//@ComponentScan("com.itmayiedu.app")
//@ComponentScan("com.itmayiedu.controller")
@MapperScan(basePackages = {"com.itmayiedu.mapper"})
//@EnableAutoConfiguration
public class AppJsp {

    public static void main(String[] args) {
        SpringApplication.run(AppJsp.class, args);
    }
}
