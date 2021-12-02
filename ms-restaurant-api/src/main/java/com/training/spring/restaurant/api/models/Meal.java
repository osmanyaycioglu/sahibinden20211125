package com.training.spring.restaurant.api.models;


public class Meal {

    private String  meal;
    private Integer amount;

    public String getMeal() {
        return this.meal;
    }

    public Meal setMeal(final String mealParam) {
        this.meal = mealParam;
        return this;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public Meal setAmount(final Integer amountParam) {
        this.amount = amountParam;
        return this;
    }


}
