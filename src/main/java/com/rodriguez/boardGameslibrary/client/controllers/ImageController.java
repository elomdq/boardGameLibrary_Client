package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.models.Image;
import com.rodriguez.boardGameslibrary.client.servicies.BoardGameService;
import com.rodriguez.boardGameslibrary.client.servicies.ImageService;
import com.rodriguez.boardGameslibrary.client.uploadingfiles.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(path="/image")
@CrossOrigin("http://localhost:8081")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private BoardGameService boardGameService;

    /*@Autowired
    private StorageService storageService;*/

    @Autowired
    HttpServletRequest request;

    @GetMapping("/form")
    public String form(Model model){

        List<BoardGame> boardGames = boardGameService.list();
        model.addAttribute("games",boardGames);

        return "/views/image-add";
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String add(@RequestPart("file") MultipartFile file, @RequestParam("gameId") Long id, Model model) throws IOException {

        BoardGame game = boardGameService.byId(id);

        Image image = UploadService.consumeImageFile(file,id);

        image.setGame(game);

        image = imageService.create(image);

        model.addAttribute("game", game);
        //model.addAttribute("path", Paths.get(uploadPath).toString());

        return "/views/game-view";
    }



}
