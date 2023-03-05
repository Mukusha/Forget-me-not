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

    @GetMapping("/note/{id}/view")
    public String viewNote(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("note",noteService.getNoteById(id));
        return "noteWiev";
    }

    //!
    @GetMapping("/note/{id}/edit")
    public String editNoteGet(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("note",noteService.getNoteById(id));
        return "noteEdit";
    }

    //!
    @PostMapping("/note/{id}/edit")
    public String editNotePost(@PathVariable(value = "id") long id,
                               Note note,
                               Model model) {
        noteService.updateNote(id,note);
        return "redirect:/notes";
    }


    @GetMapping("/note/add")
    public String addNote(Model model) {
        return "noteAdd";
    }

    @PostMapping("/note/add")
    public String addNote(Note note, Model model) {
        noteService.saveNewNote(note);
        return "redirect:/notes";
    }

    @GetMapping("/note/{id}/delete")
    public String removeNote(@PathVariable(value = "id") long id, Model model) {
        noteService.removeNoteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/note/{id}/important")
    public String importantNote(@PathVariable(value = "id") long id) {
        noteService.setImportantNoteById(id);
        return "redirect:/notes";
    }
}
