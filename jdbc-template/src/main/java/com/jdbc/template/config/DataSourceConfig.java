package com.jdbc.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // 注入DataSource被传递给JdbcTemplate用于连接到指定数据库
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");

        // dataSource.setDriverClassName("org.postgresql.Driver");
        // dataSource.setUsername("postgres");
        // dataSource.setPassword("admin");
        // dataSource.setUrl("jdbc:postgresql://localhost:5432/hibernate_demo");
        return dataSource;
    }
}
