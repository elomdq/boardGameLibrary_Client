package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
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
public class BoardGameService {

    //private WebClient client;

    @Autowired
    private RestTemplate restTemplate;

    public BoardGameService(){
        /*this.client = WebClient.create("http://localhost:8080/api/library/boardgames/");*/
    }

    public List<BoardGame> list(){
        /*Mono<List<BoardGame>> response = client.get().uri("/list").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(new ParameterizedTypeReference<List<BoardGame>>() {});
        return response.block();*/

        ResponseEntity<List<BoardGame>> response = restTemplate.exchange("/boardgames/list", HttpMethod.GET, null, new ParameterizedTypeReference<List<BoardGame>>() {
        });

        return response.getBody();
    }

    public BoardGame byId(Long id){
        /*Mono<BoardGame> response = client.get().uri("/{id}",id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(BoardGame.class);
        return response.block();*/

        BoardGame game = restTemplate.getForObject("/boardgames/"+id, BoardGame.class);
        return game;
    }

}
