package com.smile.forgetmenot.controllers;

import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*Здесь действия осуществляемые с записками: просмотр, редактирование, создание, удаление*/
@Controller
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    //!
    @GetMapping("/notes/{id}/view")
    public String viewNote(@PathVariable(value = "id") long id, Model model) {
        return "noteWiev";
    }

    //!
    @PostMapping("/notes/{id}/view")
    public String viewNote(@PathVariable(value = "id") long id, @RequestParam String key, Model model) {
        //действия кнопок
        return "noteWiev";
    }

    //!
    @GetMapping("/notes/{id}/edit")
    public String editNote(@PathVariable(value = "id") long id, Model model) {
        return "noteEdit";
    }

    //!
    @PostMapping("/notes/{id}/edit")
    public String editNote(@PathVariable(value = "id") long id, @RequestParam String key, Model model) {
        return "noteEdit";
    }

    //!
    @GetMapping("/notes/add")
    public String addNote(Model model) {
        return "noteAdd";
    }

    //!
    @PostMapping("/notes/add")
    public String addNote(Note note, Model model) {
        noteService.saveNewNote(note);
        return "redirect:/notes";
    }
}
