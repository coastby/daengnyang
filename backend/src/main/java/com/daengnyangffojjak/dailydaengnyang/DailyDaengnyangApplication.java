package com.daengnyangffojjak.dailydaengnyang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class DailyDaengnyangApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyDaengnyangApplication.class, args);
    }

}
