package com.axon.axondemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories("com.axon.axondemo.queryside")
//@EnableMongoRepositories(basePackages = {"com.axon.axondemo.queryside"})
@SpringBootApplication
public class AxondemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AxondemoApplication.class, args);
    }

}
