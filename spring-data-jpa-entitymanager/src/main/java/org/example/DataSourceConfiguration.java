package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    // 自动注入的Bean对象满足@ConditionalOnSingleCandidate(DataSource.class)注解条件
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setDriverClassName("org.hibernate.dialect.OracleDialect");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");
        return dataSource;
    }
}
