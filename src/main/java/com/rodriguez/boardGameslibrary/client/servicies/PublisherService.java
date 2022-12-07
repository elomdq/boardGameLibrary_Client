package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.Publisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PublisherService {

    private WebClient webClient;

    public PublisherService(){
        this.webClient = WebClient.create("http://localhost:8080/api/library/publishers");
    }

    public List<Publisher> list(){
        Mono<List<Publisher>> response = webClient.get().uri("/list")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Publisher>>() {});

        List<Publisher> publishers = response.block();

        return publishers;
    }
}
