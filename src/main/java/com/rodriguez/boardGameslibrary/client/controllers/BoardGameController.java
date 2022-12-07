package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.models.Image;
import com.rodriguez.boardGameslibrary.client.servicies.BoardGameService;
import com.rodriguez.boardGameslibrary.client.servicies.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.List;

@ApplicationScope
@Controller
@RequestMapping("/boardgames")
public class BoardGameController {

    @Autowired
    private BoardGameService boardGameService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/list")
    public String gamesList(Model model){

        List<BoardGame> games = boardGameService.list();

        if(!games.isEmpty()){
            games.forEach(g -> {
                g.setImages(imageService.listByGameId(g.getId()));
            });
        }

        model.addAttribute("games", games);

        return "/views/games-list";
    }

    @GetMapping(path= "/view/{id}")
    public String gameView(@PathVariable Long id, Model model){

        BoardGame game = boardGameService.byId(id);

        if(game!=null){
            game.setImages(imageService.listByGameId(game.getId()));
        }

        model.addAttribute("game", game);

        return "/views/game-view";
    }

}
