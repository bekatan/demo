package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User bekatan = new User(
                    "Bekatan",
                    "bekatans@gmail.com"
            );
            User aida = new User(
                    "aida",
                    "zhaemida@gmail.com"
            );

            userRepository.saveAll(
                    List.of(bekatan, aida)
            );
        };
    }
}
