package com.example.msbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsBookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBookingApplication.class, args);
    }

}
