package com.privalia.poc.eventbus.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * Configuration file for the app
 *
 * @author david.amigo
 */
@Configuration
public class AppConfig {

    @Bean
    Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
