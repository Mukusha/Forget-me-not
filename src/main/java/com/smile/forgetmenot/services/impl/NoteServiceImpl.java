package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Img;
import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.repositories.ImgRepository;
import com.smile.forgetmenot.repositories.NoteRepository;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {
    @Value("${upload.path}")
    private String uploadPath;

    private final NoteRepository noteRepository;
    private final ImgRepository imgRepository;

    public NoteServiceImpl(NoteRepository noteRepository, ImgRepository imgRepository) {

        this.noteRepository = noteRepository;
        this.imgRepository = imgRepository;
    }

    @Override
    public Iterable<Note> getAllNotes() {
        //   noteRepository.save(new Note("Тема1","Текст 1"));
        return noteRepository.findAll();
    }

    @Override
    public void saveNewNote(Note note) {
        Note newNote = new Note(note.getSubjectNotes(), note.getFullTextNotes(), note.isImportant());
        noteRepository.save(newNote);
    }

    @Override
    public void saveNewNote(Note note, boolean isImportant, MultipartFile[] files) throws IOException {
        Note newNote = new Note(note.getSubjectNotes(), note.getFullTextNotes(), isImportant);
        Set<Img> images = new HashSet<>();
        //обработка картинки
        for (MultipartFile file : files) {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();
                file.transferTo(new File(uploadPath + "/" + resultFilename));

                Img image = new Img(resultFilename);
                imgRepository.save(image);
                images.add(imgRepository.findByFilenameImage(resultFilename));
            }
        }

        newNote.setImages(images);
        System.out.println("images = "+images);
        System.out.println("newNote = "+newNote);
        noteRepository.save(newNote);
    }

    @Override
    public void updateNote(Long id, Note noteNew, boolean isImportant) {
        if (!noteRepository.existsById(id)) { //если такой заметки нет, то ничего не делаем
            return;
        }

        Optional<Note> notes = noteRepository.findById(id);
        ArrayList<Note> res = new ArrayList<>();
        notes.ifPresent(res::add);
        Note note = res.get(0);

        note.setSubjectNotes(noteNew.getSubjectNotes());
        note.setFullTextNotes(noteNew.getFullTextNotes());
        note.setImportant(isImportant);
        note.setDateModification(new Timestamp(System.currentTimeMillis()));

        noteRepository.save(note);
    }

    @Override
    public List<Note> getSortListNotes(String typeSort) {
        return null;
    }

    @Override
    public List<Note> findKeyInNotes(String key) {
        List<Note> notesFind;
        notesFind = noteRepository.findBySubjectNotesContainingIgnoreCaseOrFullTextNotesContainingIgnoreCase(key, key);
        return notesFind;
    }

    @Override
    public Note getNoteById(long id) {
        if (!noteRepository.existsById(id)) {
            return null;
        }
        Optional<Note> notes = noteRepository.findById(id);
        ArrayList<Note> res = new ArrayList<>();
        notes.ifPresent(res::add);
        return res.get(0);
    }

    @Override
    public void removeNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        noteRepository.delete(note);
    }

    @Override
    public void setImportantNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setImportant(!note.isImportant());
        noteRepository.save(note);
    }
}
