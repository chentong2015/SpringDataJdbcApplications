package com.flyway.main;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@Profile("resetdb")
public class ResetFlywayStrategy implements FlywayMigrationStrategy {

    private final Environment environment;

    @Autowired
    public ResetFlywayStrategy(Environment environment) {
        this.environment = environment;
    }

    // TODO. 在测试环境执行DB迁移操作
    @Override
    public void migrate(Flyway flyway) {
        if (asList(environment.getActiveProfiles()).contains("production")) {
            // resetdb profile cannot be set in conjunction of production profile
            System.exit(0);
            return;
        }

        flyway.clean();
        flyway.migrate();
        if (asList(environment.getActiveProfiles()).contains("dba")) {
            System.exit(0);
        }
    }
}
