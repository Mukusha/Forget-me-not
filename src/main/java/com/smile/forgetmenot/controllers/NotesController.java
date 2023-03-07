package com.smile.forgetmenot.controllers;

import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Здесь действия осуществляемые с записками: просмотр, редактирование, создание, удаление
 */
@Controller
public class NotesController {
    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/note/{id}/view")
    public String viewNote(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        return "noteWiev";
    }

    @GetMapping("/note/{id}/edit")
    public String editNoteGet(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        return "noteEdit";
    }

    @PostMapping("/note/{id}/edit")
    public String editNotePost(@PathVariable(value = "id") long id,
                               Note note,
                               @RequestParam(required = false) Boolean isImportant,
                               @RequestParam(name = "file", required = false) MultipartFile[] files) throws IOException {
        if (isImportant == null) {
            isImportant = false;
        }
        noteService.updateNote(id, note, isImportant, files);
        return "redirect:/notes";
    }

    @GetMapping("/note/add")
    public String addNote() {
        return "noteAdd";
    }

    @PostMapping("/note/add")
    public String addNote(Note note,
                          @RequestParam(required = false) Boolean isImportant,
                          @RequestParam(name = "file", required = false) MultipartFile[] file) throws IOException {

        if (isImportant == null) {
            isImportant = false;
        }
        noteService.saveNewNote(note, isImportant, file);
        return "redirect:/notes";
    }

    @GetMapping("/note/{id}/delete")
    public String removeNote(@PathVariable(value = "id") long id) {
        noteService.removeNoteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/note/{id}/important")
    public String importantNote(@PathVariable(value = "id") long id) {
        noteService.setImportantNoteById(id);
        return "redirect:/notes";
    }

    @GetMapping("/note/{id}/view/important")
    public String importantViewNote(@PathVariable(value = "id") long id) {
        noteService.setImportantNoteById(id);
        return "redirect:/note/" + id + "/view";
    }

}
