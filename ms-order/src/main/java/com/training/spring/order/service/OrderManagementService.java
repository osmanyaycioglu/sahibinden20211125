package com.training.spring.order.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.spring.order.integration.restaurant.RestaurantMenuClient;
import com.training.spring.order.models.Order;
import com.training.spring.order.models.SendMessage;

@Service
public class OrderManagementService {

    @Autowired
    private RestaurantMenuClient rmc;

    @Autowired
    private RabbitTemplate       rabbitTemplate;

    public String placeOrder(final Order order) {
        String response = this.rmc.calculateMenu2(order);
        SendMessage messageLoc = new SendMessage();
        messageLoc.setDest(order.getPhone());
        messageLoc.setMessage(response);
        this.rabbitTemplate.convertAndSend("notfication-exchange",
                                           "sms-notification",
                                           messageLoc);
        return response;
    }

}
