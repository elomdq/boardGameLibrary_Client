package com.rodriguez.boardGameslibrary.client.controllers;

import com.rodriguez.boardGameslibrary.client.models.BoardGame;
import com.rodriguez.boardGameslibrary.client.models.Designer;
import com.rodriguez.boardGameslibrary.client.servicies.DesignerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping(path ="/form")
    public String form(Model model){
        model.addAttribute("designer", new Designer());
        return "views/designer-add";
    }

    @GetMapping(path ="/edit/{id}")
    public String editForm(Model model, @PathVariable Long id){
        Designer designer = designerService.byId(id);
        model.addAttribute("designer", designer);
        return "views/designer-edit";
    }

    @PostMapping(path = "/add")
    public String add(Model model,
                      @ModelAttribute("designer") Designer designer){
        designerService.save(designer);
        return "redirect:/designer/list";
    }

    @PostMapping(path = "/edit")
    public String edit(Model model,
                    @ModelAttribute("designer") Designer designer){
            designerService.edit(designer, designer.getId());
        return "redirect:/designer/list";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable() Long id){
        designerService.delete(id);
        return "redirect:/designer/list";
    }
}
