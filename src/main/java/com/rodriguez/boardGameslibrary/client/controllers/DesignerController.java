package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.servicies.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/designer")
public class DesignerController {

    @Autowired
    DesignerService designerService;

    @GetMapping(path="/list")
    public String list(Model model){

        model.addAttribute("designers", designerService.list());

        return "/views/designer-list";
    }

}
