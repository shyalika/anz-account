package com.anz.shyalika.account;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Springboot application class used for startup and configuration.
 * 
 * @author Shyalika Benthotage
 *
 */
@SpringBootApplication
public class AnzAccountApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnzAccountApplication.class, args);
    }

    /**
     * ModelMapper configured for mapping between model classes and entity classes
     * @return instance of {@link ModelMapper}
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
