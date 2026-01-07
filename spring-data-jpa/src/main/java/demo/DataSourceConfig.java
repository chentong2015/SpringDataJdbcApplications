package demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // TODO: 使用纯JDBC DriverManager来创建DB的一个连接
    // 底层调用DriverManager.getConnection(url, props);来获取数据库连接
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

    // TODO. 使用@Qualifier注解来区别同类型bean, 支持不同DataSource
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
}

