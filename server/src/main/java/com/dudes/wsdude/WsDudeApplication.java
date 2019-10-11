package com.dudes.wsdude;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@Configuration
@SpringBootApplication
@EnableSpringDataWebSupport
public class WsDudeApplication {

	public static void main(String[] args) {
		SpringApplication.run(WsDudeApplication.class, args);
	}

}
