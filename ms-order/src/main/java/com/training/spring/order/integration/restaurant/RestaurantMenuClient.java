package com.training.spring.order.integration.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.training.spring.order.models.Order;
import com.training.spring.restaurant.api.models.Meal;
import com.training.spring.restaurant.api.models.Menu;
import com.training.spring.restaurant.api.models.MenuInfo;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class RestaurantMenuClient {

    @Autowired
    private RestTemplate           rt;
    @Autowired
    private IReastaurantMenuClient rmc;

    @Autowired
    private EurekaClient           eurekaClient;

    public void name() {
        Applications applicationsLoc = this.eurekaClient.getApplications();
        List<Application> registeredApplicationsLoc = applicationsLoc.getRegisteredApplications();
        for (Application applicationLoc : registeredApplicationsLoc) {
            List<InstanceInfo> instancesLoc = applicationLoc.getInstances();
            for (Application application2Loc : registeredApplicationsLoc) {

            }
        }
    }

    @Retry(name = "restaurantmenu")
    public String calculateMenu(final Order orderParam) {
        Menu menuLoc = new Menu();
        List<Meal> listLoc = new ArrayList<>();
        List<com.training.spring.order.rest.models.Meal> mealsLoc = orderParam.getMeals();
        for (com.training.spring.order.rest.models.Meal mealLoc : mealsLoc) {
            listLoc.add(new Meal().setMeal(mealLoc.getMeal())
                                  .setAmount(mealLoc.getAmount()));
        }
        menuLoc.setMeals(listLoc);
        MenuInfo menuInfoLoc = this.rmc.calculate(menuLoc);
        return menuInfoLoc.getMessage() + " " + menuInfoLoc.getPrice();
    }

    @Retry(name = "restaurantmenu", fallbackMethod = "calculateMenuFallback")
    public String calculateMenu2(final Order orderParam) {
        Menu menuLoc = new Menu();
        List<Meal> listLoc = new ArrayList<>();
        List<com.training.spring.order.rest.models.Meal> mealsLoc = orderParam.getMeals();
        for (com.training.spring.order.rest.models.Meal mealLoc : mealsLoc) {
            listLoc.add(new Meal().setMeal(mealLoc.getMeal())
                                  .setAmount(mealLoc.getAmount()));
        }
        menuLoc.setMeals(listLoc);
        MenuInfo menuInfoLoc = this.rt.postForObject("http://RESTAURANT-API/api/v1/restaurant/menu/calculate",
                                                     menuLoc,
                                                     MenuInfo.class);
        return menuInfoLoc.getMessage() + " " + menuInfoLoc.getPrice();
    }

    public String calculateMenuFallback(final Order orderParam,
                                        final Throwable tht) {
        return "Fallback yapıldı";
    }

}

