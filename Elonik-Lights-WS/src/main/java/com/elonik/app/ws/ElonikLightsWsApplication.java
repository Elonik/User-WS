package com.elonik.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ElonikLightsWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElonikLightsWsApplication.class, args);
	}

}
