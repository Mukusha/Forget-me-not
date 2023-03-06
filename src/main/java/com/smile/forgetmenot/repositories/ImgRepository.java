package com.smile.forgetmenot.repositories;

import com.smile.forgetmenot.models.Img;
import org.springframework.data.repository.CrudRepository;

public interface ImgRepository extends CrudRepository<Img,Long> {
    Img findByFilenameImage(String filenameImage);
}
