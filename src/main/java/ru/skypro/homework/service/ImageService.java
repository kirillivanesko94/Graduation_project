package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;

import java.io.IOException;
@Service
public class ImageService {
    ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }
    public ImageEntity saveImage(MultipartFile file){
        ImageEntity  imageEntity = new ImageEntity();
        imageEntity.setData(null);
        imageEntity.setId(null);
        imageEntity.setFileName(null);
        imageEntity.setMediaType(null);
        imageRepository.save(imageEntity);
        return imageEntity;
    }
}
