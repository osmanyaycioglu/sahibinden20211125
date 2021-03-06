package com.training.spring.restaurant.api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

public interface IRestaurantMenuApi {

    @PostMapping("/api/v1/restaurant/menu/calculate")
    public MenuInfo calculate(@RequestBody final Menu menu);
}
