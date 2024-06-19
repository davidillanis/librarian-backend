package com.micorservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MicorserviceGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicorserviceGatewayApplication.class, args);
    }

}
