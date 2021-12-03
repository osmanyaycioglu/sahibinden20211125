package com.training.spring.resilience;

import java.util.function.Predicate;

public class MyExceptionPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable tParam) {
        System.out.println("Exception Predicate çalıştı");
        return true;
    }

}
