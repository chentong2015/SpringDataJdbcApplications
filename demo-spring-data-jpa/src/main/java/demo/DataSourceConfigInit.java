package demo;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

public class DataSourceConfigInit {

    // 在APP应用启动后自动执行SQL脚本
    @Bean
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
