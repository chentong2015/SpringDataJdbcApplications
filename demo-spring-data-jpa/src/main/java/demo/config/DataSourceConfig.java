package demo.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // TODO: DriverManagerDataSource使用纯JDBC DriverManager来创建DB的一个连接
    // DriverManager.getConnection(url, props);
    @Bean
    @Qualifier("mysql-datasource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbspringboot");
        return dataSource;
    }

    // TODO. 使用@Qualifier注解来区别注入的同类型bean, 支持不同DataSource
    // 使用DataSourceBuilder构建DataSource
    @Bean
    @Qualifier("mysql-db2")
    public DataSource dataSource2() {
        DataSourceBuilder<?> builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/dbspringboot");
        return builder.build();
    }

    @Bean
    @Qualifier("psql-datasource")
    public DataSource getAnotherDatasource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgresql");
        dataSource.setPassword("admin");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/my_database");
        return dataSource;
    }

    // TODO: 推荐使用连接池的实现来配置DataSource
    @Bean
    public DataSource dataSourceCP(JdbcProperties jdbcProperties) {
        // jdbcProperties.getUsername()
        // jdbcProperties.getPassword()
        return null;
    }
}

