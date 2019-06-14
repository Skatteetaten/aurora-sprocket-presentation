package com.example.demowebjpa;

import java.time.Duration;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;

@Configuration
public class MetricsConfig {

    private static final int MIN_MILLIS = 30;
    private static final int MAX_SECONDS = 2;

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCustomizer() {
        return registry -> registry.config()
            .meterFilter(MeterFilter.minExpected("http", Duration.ofMillis(MIN_MILLIS)))
            .meterFilter(MeterFilter.maxExpected("http", Duration.ofSeconds(MAX_SECONDS)));
    }
}