package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.Designer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DesignerService {

    private WebClient webClient;

    @Autowired
    RestTemplate restTemplate;

    public DesignerService(){
        this.webClient = WebClient.create("http://localhost:8080/api/library/designers");
    }

    public List<Designer> list(){
        /*Mono<List<Designer>> response = webClient.get().uri("/list")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Designer>>() {});

        List<Designer> designers = response.block();*/

        ResponseEntity<List<Designer>> rateResponse =
                restTemplate.exchange("/designers/list",
                        HttpMethod.GET,null, new ParameterizedTypeReference<List<Designer>>() {
                        });
        List<Designer> designers = rateResponse.getBody();

        return designers;
    }

}
