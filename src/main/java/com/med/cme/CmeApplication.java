package com.med.cme;

import com.med.cme.model.Customer;
import com.med.cme.service.DefaultCustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CmeApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmeApplication.class, args);
    }

    @Bean
    CommandLineRunner run(DefaultCustomerService customerService) {
        return args -> {
//            customerService.saveCustomer(new Customer(1L, "vasu", "hello", "some@gmail.com", "1124325432"));
        };
    }  }