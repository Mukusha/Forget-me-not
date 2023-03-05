package com.smile.forgetmenot.services;

import com.smile.forgetmenot.models.Note;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoteService {
    /**
     * Возвращает список всех заметок
     */
    Iterable<Note> getAllNotes();

    /**
     * Создать новую заметку
     *
     * @param note - новая заметка
     */
    void saveNewNote(Note note);

    /**
     * Обновить заметку
     *
     * @param id            - id редактируемой заметки
     * @param subjectNotes  - тема
     * @param fullTextNotes - полный текст
     */
    void updateNote(Long id, String subjectNotes, String fullTextNotes);

    /**
     * Возвращает список всех заметок отсортированный определенным образом
     *
     * @param typeSort - тип сортировки:
     *                 modification - по дате изменения
     *                 create - по дате создания
     *                 abc - по алфавиту
     *                 color - по цвету
     */
    List<Note> getSortListNotes(String typeSort);

    /**
     * Возвращает список заметок в которых найден ключ
     *
     * @param key - ключ
     */
    List<Note> findKeyInNotes(String key);

    /**
     * Возвращает заметку по ее id
     *
     * @param id - id искомой заметки
     */
    Note getNoteById(long id);
}
