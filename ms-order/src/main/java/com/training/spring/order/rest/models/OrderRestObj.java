package com.training.spring.order.rest.models;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.training.spring.validation.StartWith;

public class OrderRestObj {

    @NotEmpty
    @Size(min = 2, max = 15, message = "name {min} ile {max} arasında olmalı")
    private String     name;
    @NotEmpty
    @Size(min = 3, max = 20, message = "surname {min} ile {max} arasında olmalı")
    @StartWith("s:")
    private String     surname;
    @NotEmpty
    @Size(min = 11, max = 11)
    private String     phone;
    @Size(min = 1, message = "Min 1 tane yemek olmalı")
    private List<Meal> meals;
    @Max(24)
    @Min(1)
    private Integer    hour;

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

    public Integer getHour() {
        return this.hour;
    }

    public void setHour(final Integer hourParam) {
        this.hour = hourParam;
    }


}
