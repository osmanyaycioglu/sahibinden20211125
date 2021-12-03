package com.training.spring.order.integration.restaurant;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class MyRequestInterceptor implements RequestInterceptor {

    @Override
    public void apply(final RequestTemplate templateParam) {
        templateParam.header("X-Origin",
                             "domain.subdomain.order.order");
        //        templateParam.header("Authorization",
        //                             Base64.getEncoder()
        //                                   .encodeToString("user:passwd".getBytes()));

    }

}
