package org.knit.solutions.task20.config;

import org.knit.solutions.task20.repository.FilePasswordRepository;
import org.knit.solutions.task20.repository.InMemoryPasswordRepository;
import org.knit.solutions.task20.repository.PasswordRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan("org.knit.solutions.task20")
public class AppConfig {
    @Bean
    @Primary
    public PasswordRepository passwordRepository(FilePasswordRepository fileRepository) {
        return fileRepository;
    }
} 