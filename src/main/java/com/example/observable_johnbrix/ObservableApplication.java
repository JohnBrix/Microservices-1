package com.example.observable_johnbrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ObservableApplication {

    public static void main(String[] args) {
        SpringApplication.run(ObservableApplication.class, args);
    }

    //injected already so you can use it
    //REST TEMPLATE IS TO Call other entity from other api(microservices)
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
