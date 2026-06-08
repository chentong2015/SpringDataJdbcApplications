package org.example;

import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.example.listener.MyQueryExecutionListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setDriverClassName("org.hibernate.dialect.OracleDialect");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");
        return dataSource;
    }

    // TODO. 添加DataSource层的代理
    @Bean
    public ProxyDataSource proxyDataSource(DataSource original) {
        return ProxyDataSourceBuilder
                .create(original)
                .name("MyDataSource")
                .countQuery()
                .logQueryBySlf4j(SLF4JLogLevel.INFO)
                .listener(new MyQueryExecutionListener())
                .build();
    }
}
