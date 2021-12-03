package com.training.spring.client;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class LoadBalancerConfig {

    @Bean
    public ServiceInstanceListSupplier listSupplier(final ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                                          .withBlockingDiscoveryClient()
                                          .withBlockingHealthChecks()
                                          .build(context);
    }

}
