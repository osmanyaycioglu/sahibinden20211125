package com.training.spring.order.integration.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.training.spring.order.models.Order;
import com.training.spring.restaurant.api.models.Meal;
import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

@Service
public class RestaurantMenuClient {

    @Autowired
    private RestTemplate rt;

    public String calculateMenu(final Order orderParam) {
        Menu menuLoc = new Menu();
        List<Meal> listLoc = new ArrayList<>();
        List<com.training.spring.order.rest.models.Meal> mealsLoc = orderParam.getMeals();
        for (com.training.spring.order.rest.models.Meal mealLoc : mealsLoc) {
            listLoc.add(new Meal().setMeal(mealLoc.getMeal())
                                  .setAmount(mealLoc.getAmount()));
        }
        menuLoc.setMeals(listLoc);
        MenuInfo menuInfoLoc = this.rt.postForObject("http://RESTAURANT/api/v1/restaurant/menu/calculate",
                                                     menuLoc,
                                                     MenuInfo.class);
        return menuInfoLoc.getMessage() + " " + menuInfoLoc.getPrice();
    }

}
