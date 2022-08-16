package com.akhp.app.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AkhpAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkhpAppDiscoveryServiceApplication.class, args);
	}

}
