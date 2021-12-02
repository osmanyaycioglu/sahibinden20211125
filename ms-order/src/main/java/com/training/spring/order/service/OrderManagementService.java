package com.training.spring.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.spring.order.integration.restaurant.RestaurantMenuClient;
import com.training.spring.order.models.Order;

@Service
public class OrderManagementService {

    @Autowired
    private RestaurantMenuClient rmc;

    public String placeOrder(final Order order) {
        return this.rmc.calculateMenu(order);
    }

}
