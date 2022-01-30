package com.countryservice.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories("com.countryservice.demo.*")
//@ComponentScan(basePackages= {"com.countryservice.demo.*"})
//@EntityScan("com.countryservice.demo.*")
public class SpringBootCountryServiceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootCountryServiceProjectApplication.class, args);
	}

}
