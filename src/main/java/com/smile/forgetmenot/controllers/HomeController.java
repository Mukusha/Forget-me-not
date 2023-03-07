package com.smile.forgetmenot.controllers;

import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Действия на главной странице
 */
@Controller
public class HomeController {

    private final NoteService noteService;

    public HomeController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping("/notes")
    public String homeGet(Model model) {
        model.addAttribute("notes", noteService.getSortListNotes()); //вывести все публикации
        return "home";
    }

    @PostMapping("/notes")
    public String home(@RequestParam(required = false) String sort,
                       @RequestParam(value = "action", required = false) String action,
                       @RequestParam String key,
                       Model model) {
        System.out.println("sort = " + sort);
        System.out.println("action = " + action);
        //действия кнопок
        if (action != null && action.equals("findBtn")) {
            model.addAttribute("notes", noteService.findKeyInNotes(key)); //поиск по ключу
            return "home";
        }
        if (action != null && action.equals("sortBtn")) {//сортировка
            noteService.changeTypeSort(sort);
            model.addAttribute("notes", noteService.getSortListNotes());
            return "home";
        }
        if (action != null && action.equals("sortImportant")) {
            model.addAttribute("notes", noteService.getNotesImportant());
            return "home";
        }

        return "redirect:/notes";
    }
}
