package com.sprta.expertschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ExpertScheduleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpertScheduleApplication.class, args);
    }

}
