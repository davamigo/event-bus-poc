package com.privalia.poc.eventbus.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application
 *
 * @author david.amigo
 */
@SpringBootApplication
public class UserApplication {

    /**
     * Entry point of the UserApplication
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
