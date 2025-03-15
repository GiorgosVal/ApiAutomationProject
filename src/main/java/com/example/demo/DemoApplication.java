package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class DemoApplication {

    /**
     * The entry point of a spring boot application.
     *
     * @param args args passed
     */
    public static void main(final String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
