package com.training.spring.restaurant.api;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

@RequestMapping("/api/v1/restaurant/menu")
public interface IRestaurantMenuApi {

    @PostMapping("/calculate")
    public MenuInfo calculate(@RequestBody final Menu menu);
}
