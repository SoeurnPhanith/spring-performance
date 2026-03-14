package com.example.spring_api_loadbalancer_nginx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringApiLoadbalancerNginxApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiLoadbalancerNginxApplication.class, args);
	}

}
