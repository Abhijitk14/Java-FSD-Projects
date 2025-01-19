package com.wipro.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.wipro.ms.dto.AccountsContactInfoDto;

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
