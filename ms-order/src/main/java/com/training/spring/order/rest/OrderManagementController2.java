package com.training.spring.order.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.order.rest.models.OrderRestObj;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController2 {

    @PostMapping
    public String place(@RequestBody final OrderRestObj order) {
        return "OK";
    }

    @DeleteMapping
    public String remove(@PathVariable("oid") final Long orderId) {
        return "OK";
    }

}
