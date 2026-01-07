package demo;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

public class DataSourceInitConfig {

    // TODO. 在启动时基于DataSource初始化DB中数据
    // @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
        resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
        dataSourceInitializer.setDataSource(dataSource);

        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
        // dataSourceInitializer.setDatabaseCleaner();
        return dataSourceInitializer;
    }

    public void loadData(DataSource dataSource) {
        ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(
                false,
                false,
                "UTF-8",
                new ClassPathResource("data.sql"));
        resourceDatabasePopulator.execute(dataSource);
    }
}
