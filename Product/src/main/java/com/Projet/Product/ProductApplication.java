package com.Projet.Product;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class ProductApplication {

	/**
	 * Entry point for the application.
	 * Initialise spring configuration.
	 *
	 * @param args The arguments of the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	/**
	 * Because ModelMapper is not spring aware, instruct spring on how to create it.
	 * Spring will now be ale to inject it into our beans.
	 * @return A new model mapper.
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
