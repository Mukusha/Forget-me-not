package com.smile.forgetmenot.repositories;

import com.smile.forgetmenot.models.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NoteRepository extends CrudRepository<Note,Long> {
    List<Note> findBySubjectNotesContainingIgnoreCaseOrFullTextNotesContainingIgnoreCase(String SubjectNotes, String FullTextNotes);
}
