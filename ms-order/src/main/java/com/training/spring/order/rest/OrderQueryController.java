package com.training.spring.order.rest;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.order.rest.models.OrderRestObj;

@RestController
@RequestMapping("/api/v1/order/query")
public class OrderQueryController {

    @GetMapping("/get/one/{oid}")
    public OrderRestObj getOne(@PathVariable("oid") final Long orderId) {
        return null;
    }

    @GetMapping("/get/all")
    public List<OrderRestObj> getAll() {
        return null;
    }

}
