package com.training.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.error.rest.error.ErrorConfig;

@SpringBootApplication
@Import(ErrorConfig.class)
public class MsOrderApplication {

    public static void main(final String[] args) {
        SpringApplication.run(MsOrderApplication.class,
                              args);
    }

}
