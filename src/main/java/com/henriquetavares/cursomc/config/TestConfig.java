package com.henriquetavares.cursomc.config;

import com.henriquetavares.cursomc.services.DBService;
import com.henriquetavares.cursomc.services.EmailService;
import com.henriquetavares.cursomc.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instanciateDatabase() throws ParseException {
        dbService.instanciateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
