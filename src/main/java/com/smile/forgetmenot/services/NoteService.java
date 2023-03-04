package com.smile.forgetmenot.services;

import com.smile.forgetmenot.models.Note;
import org.springframework.stereotype.Component;

@Component
public interface  NoteService {
    /**
     * Возвращает список всех заметок
     * */
    Iterable<Note>  getAllNotes();

}
