package com.training.spring.resilience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.EventPublisher;
import io.github.resilience4j.circuitbreaker.CircuitBreaker.Metrics;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;

// @Component
public class MyCBTest implements CommandLineRunner {

    @Autowired
    private MyCalleeBean           mcb;

    @Autowired
    private CircuitBreakerRegistry cbr;

    @Override
    public void run(final String... argsParam) throws Exception {
        CircuitBreaker circuitBreakerLoc = this.cbr.circuitBreaker("cbrestaurant");
        EventPublisher eventPublisherLoc = circuitBreakerLoc.getEventPublisher();
        eventPublisherLoc.onSuccess(e -> System.out.println(e));
        Metrics metricsLoc = circuitBreakerLoc.getMetrics();
        for (int iLoc = 0; iLoc < 50; iLoc++) {
            System.out.println("Index : "
                               + iLoc
                               + " state : "
                               + circuitBreakerLoc.getState()
                               + " fr : "
                               + metricsLoc.getFailureRate()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCalls()
                               + " p : "
                               + metricsLoc.getNumberOfNotPermittedCalls()
                               + " s : "
                               + metricsLoc.getNumberOfSuccessfulCalls());
            try {
                // retryLoc.executeRunnable(() -> this.mcb.test("osman"));
                this.mcb.test2("osman" + iLoc);
            } catch (Exception eLoc) {
                System.out.println("Exp : " + eLoc.getMessage());
            }
        }
    }

}
