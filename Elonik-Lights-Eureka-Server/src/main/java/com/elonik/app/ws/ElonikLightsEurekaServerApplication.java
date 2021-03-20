package com.elonik.app.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ElonikLightsEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElonikLightsEurekaServerApplication.class, args);
	}

}
