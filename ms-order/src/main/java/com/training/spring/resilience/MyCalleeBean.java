package com.training.spring.resilience;

import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@Component
public class MyCalleeBean {

    private int counter = 0;

    @Retry(name = "restaurantmenu", fallbackMethod = "testFalback")
    public String test(final String str) {
        this.counter++;
        if ((this.counter % 2) == 0) {
            throw new IllegalArgumentException("xyz");
        }
        return "Hello " + str;
    }

    @CircuitBreaker(name = "cbrestaurant", fallbackMethod = "testCBFalback")
    @Retry(name = "restaurantmenu", fallbackMethod = "testFalback")
    public String test2(final String str) {
        this.counter++;
        if ((this.counter % 3) == 0) {
            throw new IllegalArgumentException("xyz");
        }
        return "Hello " + str;
    }

    public String testFalback(final String str,
                              final Throwable tht) {
        System.out.println("Fallback run : " + tht.getMessage());
        return "Hello Fallback";
    }

    public String testCBFalback(final String str,
                                final Throwable tht) {
        System.out.println("CB Fallback run : " + tht.getMessage());
        return "Hello Fallback";
    }

}
