package com.example.chapter01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * This class starts the application as a small web app.
 * Spring Boot gives us an embedded server, while the imported XML file
 * still demonstrates the traditional Spring bean configuration style.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ImportResource("classpath:applicationContext.xml")
public class SpringIntroApplication {

    /**
     * main is the entry point when we run the program from the command line.
     */
    public static void main(String[] args) {
        SpringApplication.run(SpringIntroApplication.class, args);
    }
}
