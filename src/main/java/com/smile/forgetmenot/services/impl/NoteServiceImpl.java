package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.repositories.NoteRepository;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public void saveNewNote(String subjectNotes, String fullTextNotes) {

    }

    @Override
    public void updateNote(Long id, String subjectNotes, String fullTextNotes) {

    }

    @Override
    public List<Note> getSortListNotes(String typeSort) {
        return null;
    }

    @Override
    public List<Note> findKeyInNotes(String key) {
        return null;
    }
}
