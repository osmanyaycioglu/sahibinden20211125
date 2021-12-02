package com.training.spring.order.mappers;

import java.time.LocalDateTime;

import com.training.spring.order.models.Order;
import com.training.spring.order.rest.models.OrderRestObj;

public class OrderObjectMapper {

    public static Order toOrder(final OrderRestObj orderParam) {
        Order orderLoc = new Order();
        orderLoc.setName(orderParam.getName());
        orderLoc.setSurname(orderParam.getSurname());
        orderLoc.setPhone(orderParam.getPhone());
        orderLoc.setMeals(orderParam.getMeals());
        orderLoc.setDeliveryTime(LocalDateTime.now()
                                              .plusHours(orderParam.getHour()));
        return orderLoc;
    }

}
