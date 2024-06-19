package com.microservice.library;

import com.microservice.library.service.other.AddDataServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceLibraryApplication implements CommandLineRunner {
    @Autowired
    private AddDataServiceImpl addDataService;

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceLibraryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        addDataService.addDataBase();
    }
}
