package com.akhp.app.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AkhpAppGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AkhpAppGatewayServiceApplication.class, args);
	}

}
