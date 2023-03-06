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
     * Создать новую заметку с указанием важности
     * @param note - новая заметка
     * @param isImportant - важная (true) или нет (null/false)
     */
    void saveNewNote(Note note, boolean isImportant);

    /**
     * Обновить заметку
     *
     * @param id            - id редактируемой заметки
     * @param note  - заметка
     * @param isImportant - важная (true) или нет (null/false)
     */
    void updateNote(Long id,Note note, boolean isImportant);

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

    /**
     * Удаляет заметку по ее id
     *
     * @param id - id  заметки
     */
    void removeNoteById(long id);

    /**
     * Изменить важность заметки по ее id
     *
     * @param id - id  заметки
     */
    void setImportantNoteById(long id);
}
