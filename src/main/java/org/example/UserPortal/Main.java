package org.example.UserPortal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication
public class Main {
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        // Press Opt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.

            SpringApplication.run(Main.class, args);

        }
    }
