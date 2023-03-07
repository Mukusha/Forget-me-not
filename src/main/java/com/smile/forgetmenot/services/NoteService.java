package com.smile.forgetmenot.services;

import com.smile.forgetmenot.models.Note;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
     * @param files - картинки
     */
    void saveNewNote(Note note, boolean isImportant,  MultipartFile[] files) throws IOException;

    /**
     * Обновить заметку
     *
     * @param id          - id редактируемой заметки
     * @param note        - заметка
     * @param isImportant - важная (true) или нет (null/false)
     * @param files - картинки
     */
    void updateNote(Long id, Note note, boolean isImportant, MultipartFile[] files) throws IOException;

    /**
     * Возвращает список всех заметок отсортированный определенным образом
     */
    List<Note> getSortListNotes();

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
    /**
     * Задать сортировку на главной странице

     * @param typeSort - тип сортировки:
     *                 modification - по дате изменения
     *                 create - по дате создания
     *                 abc - по алфавиту
     */
    void changeTypeSort(String typeSort);
    /**
     * Вывести список отсортированный по важности: сначала важные по дате модификации,
     * а потом не отмеченные тоже отсортированные по дате модификации
     */
    List<Note> getNotesImportant();
}
