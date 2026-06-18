package org.example;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // TODO. 封装底层HikariDataSource -> com.p6spy.engine.spy.P6DataSource代理
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");
        return dataSource;
    }
}
