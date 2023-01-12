package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.models.Designer;
import com.rodriguez.boardGameslibrary.client.models.Publisher;
import com.rodriguez.boardGameslibrary.client.servicies.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    @Autowired
    PublisherService publisherService;

    @GetMapping(path="/list")
    public String list(Model model){

        model.addAttribute("publishers", publisherService.list());

        return "/views/publisher-list";
    }

    @GetMapping(path ="/form")
    public String form(Model model){
        model.addAttribute("publisher", new Publisher());
        return "/views/publisher-add";
    }

    @GetMapping(path ="/edit/{id}")
    public String editForm(Model model, @PathVariable Long id){
            Publisher publisher = publisherService.byId(id);
            model.addAttribute("publisher", publisher);
        return "views/publisher-edit";
    }

    @PostMapping(path = "/add")
    public String add(Model model,
                    @ModelAttribute("publisher") Publisher publisher){
        publisherService.save(publisher);
        return  "redirect:/publisher/list";
    }

    @PostMapping(path = "/edit")
    public String edit(Model model,
                       @ModelAttribute("publisher") Publisher publisher){
            publisherService.edit(publisher, publisher.getId());
        return "redirect:/publisher/list";
    }
}
