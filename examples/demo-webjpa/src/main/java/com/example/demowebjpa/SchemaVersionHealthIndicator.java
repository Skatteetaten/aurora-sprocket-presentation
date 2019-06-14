package com.example.demowebjpa;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SchemaVersionHealthIndicator implements HealthIndicator {

    public static final String EXPECTED_SCHEMA_VERSION = "201803021300";

    private JdbcTemplate jdbcTemplate;

    public SchemaVersionHealthIndicator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Health health() {

        String currentVersion =
            jdbcTemplate.queryForObject("select max(\"version\") from \"flyway_schema_history\"", String.class);
        Integer migrationCount =
            jdbcTemplate.queryForObject("select count(1) from \"flyway_schema_history\"", Integer.class);
        Health.Builder healthBuilder = !EXPECTED_SCHEMA_VERSION.equals(currentVersion) ? Health.down() : Health.up();
        return healthBuilder
            .withDetail("expected_version", EXPECTED_SCHEMA_VERSION)
            .withDetail("actual_version", currentVersion)
            .withDetail("migration_count", migrationCount)
            .build();
    }
}