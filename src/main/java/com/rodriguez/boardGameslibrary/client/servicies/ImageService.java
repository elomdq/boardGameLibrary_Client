package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.models.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ImageService {

    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    public ImageService(){
        this.webClient = WebClient.create("http://localhost:8080/api/library/images");
    }

    public List<Image> list(){
        Mono<List<Image>> response = webClient.get().uri("/list").accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(new ParameterizedTypeReference<List<Image>>() {
        });
        List<Image> images = response.block();

        return images;
    }

    public List<Image> listByGameId(Long gameId){
        Mono<List<Image>> response = webClient.get().uri("/list/"+gameId).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(new ParameterizedTypeReference<List<Image>>() {
        });
        List<Image> images = response.block();
        return images;
    }

    public Image byId(Long id){
        Mono<Image> response = webClient.get().uri("/{id}",id).accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(Image.class);

        return response.block();
    }



    public Image create(Image image){
        //Mono<Image> response = webClient.post().accept(MediaType.APPLICATION_JSON).bodyValue(image).retrieve().bodyToMono(Image.class);

        HttpEntity<Image> entity = new HttpEntity<>(image);
        Image img = restTemplate.postForObject("/images",entity, Image.class);

        return img;
    }

}
