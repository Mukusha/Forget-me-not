package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.repositories.NoteRepository;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void saveNewNote(Note note) {
        Note newNote = new Note(note.getSubjectNotes(), note.getFullTextNotes());
        noteRepository.save(newNote);
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

    @Override
    public Note getNoteById(long id) {
        if(!noteRepository.existsById(id)){
            return null;
        }
        Optional<Note> notes = noteRepository.findById(id);
        ArrayList<Note> res = new ArrayList<>();
        notes.ifPresent(res::add);
        return res.get(0);
    }
}
