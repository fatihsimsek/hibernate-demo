package com.simsek.hibernate;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationRunner {
    private MeterRegistry meterRegistry;
    private Number value = new Double(1.0);

    public ApplicationStartup(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        meterRegistry.gauge("app.info", Tags.of("version", "0.0.1"), value);
    }
}
