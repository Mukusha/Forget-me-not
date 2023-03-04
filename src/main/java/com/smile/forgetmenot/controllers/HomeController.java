package com.smile.forgetmenot.controllers;

import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/notes")
    public  String home(Model model){

        model.addAttribute("notes",noteService.getAllNotes()); //вывести все публикации
        return "home";
    }

    @PostMapping("/notes")
    public  String home(@RequestParam String key, Model model){
        //действия кнопок
        return "home";
    }
}
