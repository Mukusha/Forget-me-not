package com.smile.forgetmenot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue
    private Long id;
    private String subjectNotes;
    @Column(length=2048)
    private String fullTextNotes;

    private boolean isImportant;
    @Column(nullable = false)
    private Timestamp dateModification;
    @Column(nullable = false)
    private Timestamp dateCreate;

    public Note() {
    }

    public Note(String subjectNotes, String fullTextNotes,  boolean isImportant) {
        this.subjectNotes = subjectNotes;
        this.fullTextNotes = fullTextNotes;
        this.isImportant = isImportant;
        this.dateCreate = new Timestamp(System.currentTimeMillis());
        this.dateModification = this.dateCreate;
    }
}
