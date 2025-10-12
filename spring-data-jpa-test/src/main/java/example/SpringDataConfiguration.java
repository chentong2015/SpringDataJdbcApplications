package example;

import example.service.MyConfigurationService;
import example.service.MyConfigurationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class SpringDataConfiguration {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/hibernate_demo");
        return dataSource;
    }

    // 在@Configuration配置文件中注入Bean，特定接口的实现类
    @Bean("MY_CONFIG_SERVICE")
    public MyConfigurationService myConfigurationService() {
        return new MyConfigurationServiceImpl();
    }
}
