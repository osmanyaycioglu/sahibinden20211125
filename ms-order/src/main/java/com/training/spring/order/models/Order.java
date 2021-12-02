package com.training.spring.order.models;

import java.time.LocalDateTime;
import java.util.List;

import com.training.spring.order.rest.models.Meal;

public class Order {

    private String        name;
    private String        surname;
    private String        phone;
    private List<Meal>    meals;
    private LocalDateTime deliveryTime;

    public String getName() {
        return this.name;
    }

    public void setName(final String nameParam) {
        this.name = nameParam;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surnameParam) {
        this.surname = surnameParam;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(final String phoneParam) {
        this.phone = phoneParam;
    }

    public List<Meal> getMeals() {
        return this.meals;
    }

    public void setMeals(final List<Meal> mealsParam) {
        this.meals = mealsParam;
    }

    public LocalDateTime getDeliveryTime() {
        return this.deliveryTime;
    }

    public void setDeliveryTime(final LocalDateTime deliveryTimeParam) {
        this.deliveryTime = deliveryTimeParam;
    }


}
