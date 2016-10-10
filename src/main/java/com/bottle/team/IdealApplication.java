package com.bottle.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by PC on 09/10/2016.
 */
@SpringBootApplication
@EnableAutoConfiguration
@EnableWebMvc
public class IdealApplication extends WebMvcAutoConfiguration {
    public static void main(String args[]) {
        SpringApplication.run(IdealApplication.class);
    }
}
