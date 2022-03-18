package com.interview.project.onlineticketsystem_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class OnlineTicketSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OnlineTicketSystemBackendApplication.class, args);
    }

}
