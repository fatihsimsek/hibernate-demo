package com.simsek.hibernate;

import com.simsek.hibernate.config.OrderConfig;
import com.simsek.hibernate.entity.Order;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationRunner {
    private MeterRegistry meterRegistry;
    private OrderConfig orderConfig;
    private Number value = new Double(1.0);

    public ApplicationStartup(MeterRegistry meterRegistry, OrderConfig orderConfig) {
        this.meterRegistry = meterRegistry;
        this.orderConfig = orderConfig;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        meterRegistry.gauge("app.info", Tags.of("version", "0.0.1"), value);
    }
}
