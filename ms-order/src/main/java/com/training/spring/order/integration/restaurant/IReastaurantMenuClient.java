package com.training.spring.order.integration.restaurant;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

@FeignClient(name = "RESTAURANT")
public interface IReastaurantMenuClient {

    @PostMapping("/api/v1/restaurant/menu/calculate")
    public MenuInfo calculate(@RequestBody final Menu menu);

}
