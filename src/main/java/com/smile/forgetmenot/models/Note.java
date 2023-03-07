package com.smile.forgetmenot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectNotes;
    @Column(length=2048)
    private String fullTextNotes;
    private boolean isImportant;
    @Column(nullable = false)
    private Timestamp dateModification;
    @Column(nullable = false)
    private Timestamp dateCreate;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Img> images;
    public Note() {
    }

    public Note(String subjectNotes, String fullTextNotes,  boolean isImportant, Set<Img> images) {
        this.subjectNotes = subjectNotes;
        this.fullTextNotes = fullTextNotes;
        this.isImportant = isImportant;
        this.images = images;
        this.dateCreate = new Timestamp(System.currentTimeMillis());
        this.dateModification = this.dateCreate;
    }

    public Note(String subjectNotes, String fullTextNotes,  boolean isImportant) {
        this.subjectNotes = subjectNotes;
        this.fullTextNotes = fullTextNotes;
        this.isImportant = isImportant;
        this.dateCreate = new Timestamp(System.currentTimeMillis());
        this.dateModification = this.dateCreate;
    }
}
