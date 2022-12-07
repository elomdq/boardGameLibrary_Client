package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.config.Location;
import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.models.Image;
import com.rodriguez.boardGameslibrary.client.servicies.BoardGameService;
import com.rodriguez.boardGameslibrary.client.servicies.ImageService;
import com.rodriguez.boardGameslibrary.client.uploadingfiles.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/add")
    public String add(@RequestParam("file") MultipartFile file, @RequestParam("gameId") Long id, Model model) throws IOException {

        Image image = new Image();
        BoardGame game = boardGameService.byId(id);

        image.setGame(game);

        String fileName = file.getOriginalFilename();
        String uploadPath = "/assets/img/" + game.getId();
        File uploadDir = new File(uploadPath);

        if(!uploadDir.exists()){
            uploadDir.mkdir();
        }

        String fileLocation = new File(uploadPath).getPath() + File.separator +fileName;
        //File newFile =  new File(uploadPath+File.separator+fileName);
        //String encodedString = Base64.getEncoder().encodeToString(fileContent);

        Files.copy(file.getInputStream(), Paths.get(uploadPath).resolve(File.separator +fileName), StandardCopyOption.REPLACE_EXISTING);
        //file.transferTo(new File(fileLocation));

        image.setUrl(uploadPath);
        image.setName(fileName);

        //image.setImg(encodedString);

        image = imageService.create(image);

        model.addAttribute("game", game);
        model.addAttribute("path", Paths.get(uploadPath).toString());

        return "/views/game-view";
    }

}
