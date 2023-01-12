package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.Designer;
import com.rodriguez.boardGameslibrary.client.models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class PublisherService {

    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

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

    public Publisher byId(Long id){
        return restTemplate.getForObject("/publishers/"+id, Publisher.class);
    }

    public Publisher save(Publisher publisher){

        HttpEntity<Publisher> publisherEntity = new HttpEntity<>(publisher);


        return restTemplate.postForObject("/publishers", publisherEntity,Publisher.class);
    }

    public Publisher edit(Publisher publisher, Long id){
        HttpEntity<Publisher> requestUpdate = new HttpEntity<>(publisher);

        Publisher editedPublisher = restTemplate.exchange("/publishers/"+id, HttpMethod.PUT, requestUpdate, Publisher.class).getBody();

        return editedPublisher;
    }
}
