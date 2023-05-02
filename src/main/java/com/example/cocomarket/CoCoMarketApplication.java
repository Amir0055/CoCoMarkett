package com.example.cocomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoCoMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoCoMarketApplication.class, args);
    }

}
