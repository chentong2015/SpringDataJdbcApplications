package org.example;

import com.zaxxer.hikari.HikariDataSource;
import net.ttddyy.dsproxy.listener.logging.SLF4JLogLevel;
import net.ttddyy.dsproxy.support.ProxyDataSource;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.example.listener.MyQueryExecutionListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // 底层实际被包装的DataSource
    @Bean(name = "realDataSource")
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");
        return dataSource;
    }

    // 创建DataSource代理并作为首要数据源
    @Primary
    @Bean(name = "proxyDataSource")
    public ProxyDataSource proxyDataSource(@Qualifier("realDataSource") DataSource original) {
        return ProxyDataSourceBuilder
                .create(original)
                .name("MyProxyDataSourceName")
                .countQuery()
                .logQueryBySlf4j(SLF4JLogLevel.INFO)
                .listener(new MyQueryExecutionListener())
                .build();
    }
}
