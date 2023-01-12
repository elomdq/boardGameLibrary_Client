package com.rodriguez.boardGameslibrary.client.servicies;

import com.rodriguez.boardGameslibrary.client.models.Designer;
import com.rodriguez.boardGameslibrary.client.models.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
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

    public Designer byId(Long id){
        return restTemplate.getForObject("/designers/"+id, Designer.class);
    }


    public Designer save(Designer designer){

        HttpEntity<Designer> designerEntity = new HttpEntity<>(designer);
        return restTemplate.postForObject("/designers", designerEntity,Designer.class);
    }

    public Designer edit(Designer designer, Long id){
        HttpEntity<Designer> requestUpdate = new HttpEntity<>(designer);

        Designer editedDesigner = restTemplate.exchange("/designers/"+id, HttpMethod.PUT, requestUpdate, Designer.class).getBody();

        return editedDesigner;
    }

    public void delete(Long id){

        restTemplate.delete("/designers/"+id);
    }

}
