package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.repositories.NoteRepository;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {
    private final NoteRepository noteRepository;

    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Iterable<Note> getAllNotes() {
     //   noteRepository.save(new Note("Тема1","Текст 1"));
        return noteRepository.findAll();
    }
}
