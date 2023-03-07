package com.smile.forgetmenot.repositories;

import com.smile.forgetmenot.models.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note,Long> {
    List<Note>  findAllByOrderByIdDesc();
    List<Note>  findAllByOrderBySubjectNotes();
    List<Note>  findAllByOrderByDateModificationDesc();
    List<Note> findBySubjectNotesContainingIgnoreCaseOrFullTextNotesContainingIgnoreCase(String SubjectNotes, String FullTextNotes);
    List<Note>  findByIsImportantOrderByDateModificationDesc(boolean IsImportant);
}
