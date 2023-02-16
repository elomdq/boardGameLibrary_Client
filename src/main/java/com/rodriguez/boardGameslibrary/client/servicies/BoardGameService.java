package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.config.BasicAuthConfig;
import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class BoardGameService {

    @Autowired
    private RestTemplate restTemplate;

    public BoardGameService(){
    }

    public List<BoardGame> list(){
        ResponseEntity<List<BoardGame>> response = restTemplate
                .exchange("/boardgames/list"
                        , HttpMethod.GET
                        , new HttpEntity<>(BasicAuthConfig.createHeaders("admin", "password2"))
                        , new ParameterizedTypeReference<List<BoardGame>>(){}
                        );

        return response.getBody();
    }

    public List<BoardGame> listByDesignerId(Long designerId){
        ResponseEntity<List<BoardGame>> response = restTemplate
                .exchange("/boardgames/list/by-designer/"+designerId
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<List<BoardGame>>(){}
                        );

        return response.getBody();
    }

    public List<BoardGame> listByPublisherId(Long publisherId){
        ResponseEntity<List<BoardGame>> response = restTemplate
                .exchange("/boardgames/list/by-publisher/"+publisherId
                        , HttpMethod.GET
                        , null
                        , new ParameterizedTypeReference<List<BoardGame>>(){}
                        );

        return response.getBody();
    }

    public BoardGame byId(Long id){
        BoardGame game = restTemplate.getForObject("/boardgames/"+id, BoardGame.class);
        return game;
    }

    public BoardGame save(BoardGame game){

        HttpEntity<BoardGame> gameEntity = new HttpEntity<>(game, BasicAuthConfig.createHeaders("admin", "password2"));

        BoardGame persistedGame = restTemplate.postForObject("/boardgames",gameEntity, BoardGame.class);

        return persistedGame;
    }

}
