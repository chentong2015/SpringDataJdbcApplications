package com.flyway.main;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

    @Value("${spring.datasource.url}")
    String dbUrl;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }

    // 测试启动后是否从属性文件加载DB连接字符串
    @PostConstruct
    public void checkConfig() {
        System.out.println("DB URL: " + dbUrl);
    }
}
