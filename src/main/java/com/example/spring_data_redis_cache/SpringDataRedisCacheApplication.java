package com.example.spring_data_redis_cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringDataRedisCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisCacheApplication.class, args);
	}

}
