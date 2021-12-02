package com.training.spring.restaurant.rest;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.restaurant.api.IRestaurantMenuApi;
import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

@RestController
public class RestaurantMenuController implements IRestaurantMenuApi {

    @Value("${server.port}")
    private int port;

    @Override
    public MenuInfo calculate(final Menu menuParam) {
        MenuInfo menuInfoLoc = new MenuInfo();
        menuInfoLoc.setMessage("Port : " + this.port + " Fiyat Bilgisi ");
        menuInfoLoc.setPrice(100);
        return menuInfoLoc;
    }

}
