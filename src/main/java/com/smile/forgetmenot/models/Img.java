package com.smile.forgetmenot.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "images")
public class Img {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String filenameImage;//  наименование картинки
    public Img() {}

    public Img(String filenameImage) {
        this.filenameImage = filenameImage;
    }
}
