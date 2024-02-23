package com.allxone.mybatisprojectbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
public class MybatisProjectBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisProjectBackendApplication.class, args);
	}

}
