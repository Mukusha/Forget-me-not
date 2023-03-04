package com.smile.forgetmenot.repositories;

import com.smile.forgetmenot.models.Note;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends CrudRepository<Note,Long> {
}
