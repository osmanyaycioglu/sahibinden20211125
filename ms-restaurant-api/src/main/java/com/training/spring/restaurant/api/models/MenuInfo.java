package com.training.spring.restaurant.api.models;


public class MenuInfo {

    private String  message;
    private Integer price;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(final String messageParam) {
        this.message = messageParam;
    }

    public Integer getPrice() {
        return this.price;
    }

    public void setPrice(final Integer priceParam) {
        this.price = priceParam;
    }


}
