package com.bottle.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by PC on 09/10/2016.
 */
@SpringBootApplication
public class IdealApplication {

    public static void main(String args[]) {
        SpringApplication.run(IdealApplication.class);
    }
}
