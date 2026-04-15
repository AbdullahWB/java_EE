package com.cwnu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringjdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringjdbcApplication.class, args);
    }
}