package com.example.cocomarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class CoCoMarketApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoCoMarketApplication.class, args);
      //  Date date= new Date(System.currentTimeMillis() + 1000 * 60 * 24);
      //  System.out.println("Date :"+date);
    }

}
