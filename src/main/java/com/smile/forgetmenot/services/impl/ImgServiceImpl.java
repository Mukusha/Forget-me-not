package com.smile.forgetmenot.services.impl;

import com.smile.forgetmenot.models.Img;
import com.smile.forgetmenot.repositories.ImgRepository;
import com.smile.forgetmenot.services.ImgServise;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class ImgServiceImpl implements ImgServise {
    @Value("${upload.path}")
    private String uploadPath;
    private final ImgRepository imgRepository;

    public ImgServiceImpl(ImgRepository imgRepository) {
        this.imgRepository = imgRepository;
    }

    private List<String> images = new ArrayList<>();
    @Override
    public Img saveNewImg(MultipartFile file) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));

            Img image = new Img(resultFilename);
            imgRepository.save(image);

            return imgRepository.findByFilenameImage(resultFilename);
    }
        return null;
}

    @Override
    public Set<Img> getSetImg(MultipartFile[] files) throws IOException {
        Set<Img> images = new HashSet<>();
        //обработка картинки
       /// if (files.length==1){images=Collections.singleton(saveNewImg(files[0]));}
        for (MultipartFile file : files) {
                images.add(saveNewImg(file));
        }
        return images;
    }

    @Override
    public void removeImg(Set<Img> images) {
        for (Img image: images) {
            File oldImage = new File(uploadPath+ "/" + image.getFilenameImage());
            imgRepository.delete(image); //!почему-то не работает!
            oldImage.delete();
        }
    }
}
