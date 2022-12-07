package com.rodriguez.boardGameslibrary.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@SpringBootApplication
public class BoardGamesClientApplication {


	//WebClient client = WebClient.create("http://localhost:8080/api/library");

	public static void main(String[] args) {
		SpringApplication.run(BoardGamesClientApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder){
		RestTemplate restTemplate = builder.build();
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080/api/library"));
		return restTemplate;
	}

}
