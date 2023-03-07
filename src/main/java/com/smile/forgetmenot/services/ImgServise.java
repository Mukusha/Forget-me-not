package com.smile.forgetmenot.services;

import com.smile.forgetmenot.models.Img;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;

@Component
public interface ImgServise {


    /**
     * Сохранить ноименование новой картинки в бд и вернуть ее с присвоенным id
     *
     * @param file - новая картинка
     */
    Img saveNewImg(MultipartFile file) throws IOException;
    /**
     * Сохранить новые картинки в бд и вернуть сет с присвоенными id
     *
     * @param files -  картинки
     */
     Set<Img> getSetImg(MultipartFile[] files) throws IOException;
    /**
     * Удалить старые картинки из памяти и бд
     *
     * @param images -  картинки
     */
    void removeImg(Set<Img> images);
}
