package com.elonik.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ElonikLightsWsApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElonikLightsWsApiGatewayApplication.class, args);
	}

}
