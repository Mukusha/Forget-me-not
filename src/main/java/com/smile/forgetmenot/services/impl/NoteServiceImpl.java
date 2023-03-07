package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Img;
import com.smile.forgetmenot.models.Note;
import com.smile.forgetmenot.repositories.NoteRepository;
import com.smile.forgetmenot.services.ImgServise;
import com.smile.forgetmenot.services.NoteService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {
    @Value("${upload.path}")
    private String uploadPath;

    private final NoteRepository noteRepository;
    private final ImgServise imgServise;
    //private String typeSort = "modification";
    private TypeSort typeSort = TypeSort.modification;
    public NoteServiceImpl(NoteRepository noteRepository, ImgServise imgServise) {

        this.noteRepository = noteRepository;
        this.imgServise = imgServise;
    }

    @Override
    public Iterable<Note> getAllNotes() {
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
            Img imgNewName = imgServise.saveNewImg(file);
            if (imgNewName != null ) {
                images.add(imgNewName);
            }
        }

        newNote.setImages(images);
        noteRepository.save(newNote);
    }

    @Override
    public void updateNote(Long id, Note noteNew, boolean isImportant, MultipartFile[] files) throws IOException {
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

        Set<Img> images = new HashSet<>();
        //обработка картинки
        for (MultipartFile file : files) {
            Img imgNewName = imgServise.saveNewImg(file);
            if (imgNewName != null ) {
                images.add(imgNewName);
            }
        }

        if(images.size()!=0){
            Set<Img> oldImg = note.getImages();
            note.setImages(images);
            imgServise.removeImg(oldImg); //удалить старые
        }

        noteRepository.save(note);
    }

    @Override
    public List<Note> getSortListNotes() {
        System.out.println("get tSort = "+typeSort);
      //  if(typeSort == "modification") {           }
        if(typeSort.equals(TypeSort.modification)) {   return noteRepository.findAllByOrderByDateModificationDesc();      }
        if(typeSort.equals(TypeSort.abc)) { return noteRepository.findAllByOrderBySubjectNotes();    }
        return noteRepository.findAllByOrderByIdDesc();
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

        Set<Img> delImg=note.getImages();
        noteRepository.deleteById(note.getId());
        imgServise.removeImg(delImg);
    }

    @Override
    public void setImportantNoteById(long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        note.setImportant(!note.isImportant());
        noteRepository.save(note);
    }

    @Override
    public void changeTypeSort(String typSort) {
        System.out.println("changeTypeSort d "+typeSort.toString());
        if(typSort != null && typSort.equals("create")) {  typeSort=TypeSort.create;      }
        if(typSort != null && typSort.equals("abc")) {  typeSort=TypeSort.abc;      }
        if(typSort != null && typSort.equals("modification")){  typeSort=TypeSort.modification;      }
        System.out.println("changeTypeSort p "+typeSort.toString());
    }


}
