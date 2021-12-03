package com.training.spring.resilience;

import java.util.function.Predicate;

public class MyResultPredicate implements Predicate<String> {

    @Override
    public boolean test(final String tParam) {
        System.out.println("Retry Predicate çalıştı");
        return false;
    }

}
