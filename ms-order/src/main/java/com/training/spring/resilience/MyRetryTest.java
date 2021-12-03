package com.training.spring.resilience;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.Retry.EventPublisher;
import io.github.resilience4j.retry.Retry.Metrics;
import io.github.resilience4j.retry.RetryRegistry;

//@Component
public class MyRetryTest implements CommandLineRunner {

    @Autowired
    private MyCalleeBean  mcb;

    @Autowired
    private RetryRegistry rr;

    @Override
    public void run(final String... argsParam) throws Exception {
        Retry retryLoc = this.rr.retry("restaurantmenu");
        EventPublisher eventPublisherLoc = retryLoc.getEventPublisher();
        eventPublisherLoc.onRetry(e -> System.out.println("retry c : " + e.getNumberOfRetryAttempts() + " " + e));
        Metrics metricsLoc = retryLoc.getMetrics();
        for (int iLoc = 0; iLoc < 50; iLoc++) {
            System.out.println("Index : "
                               + iLoc
                               + " rs : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " rsw : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " fw : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt());
            try {
                // retryLoc.executeRunnable(() -> this.mcb.test("osman"));
                this.mcb.test("osman" + iLoc);
            } catch (Exception eLoc) {
            }
        }
    }

}
