package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.servicies.BoardGameService;
import com.rodriguez.boardGameslibrary.client.servicies.ImageService;
import com.rodriguez.boardGameslibrary.client.uploadingfiles.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@ApplicationScope
@Controller
@RequestMapping("/boardgames")
public class BoardGameController {

    @Autowired
    private BoardGameService boardGameService;

    @Autowired
    private ImageService imageService;

//    @GetMapping("/list")
//    public String gamesList(Model model){
//
//        List<BoardGame> games = boardGameService.list();
//
//        if(!games.isEmpty()){
//            games.forEach(g -> {
//                g.setImages(imageService.listByGameId(g.getId()));
//            });
//        }
//
//        model.addAttribute("games", games);
//
//        return "/views/games-list";
//    }
    @GetMapping("/list")
    public String gamesList(Model model){

        return "/views/bg-list";
    }

    @GetMapping("/list/by-designer/{id}")
    public String gamesListByDesignerId(Model model, @PathVariable(name = "id") Long designerId){

        List<BoardGame> games = boardGameService.listByDesignerId(designerId);

        if(!games.isEmpty()){
            games.forEach(g -> {
                g.setImages(imageService.listByGameId(g.getId()));
            });
        }

        model.addAttribute("games", games);

        return "/views/games-list";
    }

    @GetMapping("/list/by-publisher/{id}")
    public String gamesListByPublisherId(Model model, @PathVariable(name = "id") Long publisherId){

        List<BoardGame> games = boardGameService.listByPublisherId(publisherId);

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

    @GetMapping(path = "/form")
    public String addForm(Model model){
        model.addAttribute("game", new BoardGame());

        return "/views/game-add";
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(Model model,
                      @ModelAttribute("game") BoardGame game,
                      @RequestPart("file") MultipartFile file){

        game = boardGameService.save(game);

        if(!file.isEmpty()) {
            game.getImages().add(UploadService.consumeImageFile(file, game.getId()));
            game.getImages().get(game.getImages().size()-1).setGame(game);
            imageService.create(game.getImages().get(game.getImages().size()-1));
        }

        model.addAttribute("game", game);

        return "/views/game-view";
    }

}
