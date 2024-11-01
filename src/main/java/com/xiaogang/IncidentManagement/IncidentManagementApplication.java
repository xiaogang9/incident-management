package com.xiaogang.IncidentManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IncidentManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
    }
}