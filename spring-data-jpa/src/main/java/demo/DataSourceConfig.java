package demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    // TODO: 使用纯JDBC DriverManager来创建DB的一个连接
    // 底层调用DriverManager.getConnection(url, props);来获取数据库连接
    @Bean
    @Qualifier("datasource-oracle")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.hibernate.dialect.OracleDialect");
        dataSource.setUrl("jdbc:oracle:thin:@//localhost:1531/orclcdb");
        dataSource.setUsername("fum");
        dataSource.setPassword("test1");
        return dataSource;
    }

    // TODO. 使用@Qualifier注解来区别同类型bean, 支持不同DataSource
    // 使用DataSourceBuilder构建DataSource
    // @Bean
    // @Qualifier("datasource-mysql")
    public DataSource dataSource2() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dbspringboot");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUsername("root");
        dataSource.setPassword("admin");
        return dataSource;
    }
}

