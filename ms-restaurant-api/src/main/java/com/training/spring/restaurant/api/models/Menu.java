package com.training.spring.restaurant.api.models;

import java.util.List;

public class Menu {

    private List<Meal> meals;


    public List<Meal> getMeals() {
        return this.meals;
    }


    public void setMeals(final List<Meal> mealsParam) {
        this.meals = mealsParam;
    }


}
