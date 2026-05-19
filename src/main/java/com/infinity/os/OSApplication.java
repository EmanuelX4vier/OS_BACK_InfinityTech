package com.infinity.os;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OSApplication {

    public static void main(String[] args) {
        SpringApplication.run(OSApplication.class, args);
    }
}
