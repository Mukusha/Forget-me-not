package com.smile.forgetmenot.models;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;


@Data
@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String subjectNotes;
    private String fullTextNotes;

    private Timestamp dateModification ;
    private Timestamp dateCreate;

    public Note() {
    }

    public Note(String subjectNotes, String fullTextNotes) {
        this.subjectNotes = subjectNotes;
        this.fullTextNotes = fullTextNotes;
        this.dateCreate = new Timestamp(System.currentTimeMillis());
        this.dateModification=this.dateCreate;
    }
}
