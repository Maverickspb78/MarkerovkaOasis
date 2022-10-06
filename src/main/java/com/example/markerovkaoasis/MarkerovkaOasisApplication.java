package com.example.markerovkaoasis;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@RequiredArgsConstructor
public class MarkerovkaOasisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarkerovkaOasisApplication.class, args);
    }

}
