package com.training.spring.order.rest.models;


public class Meal {

    private String  meal;
    private Integer amount;

    public String getMeal() {
        return this.meal;
    }

    public void setMeal(final String mealParam) {
        this.meal = mealParam;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(final Integer amountParam) {
        this.amount = amountParam;
    }


}
