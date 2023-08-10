package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserEntityRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {
   private final ImageRepository imageRepository;
   private final UserEntityRepository userEntityRepository;

    public ImageService(ImageRepository imageRepository, UserEntityRepository userEntityRepository) {
        this.imageRepository = imageRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public ImageEntity saveImageForAd(AdEntity adEntity, MultipartFile file) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        try {
            imageEntity.setData(file.getBytes());
            imageEntity.setFileName(file.getName());
            imageEntity.setMediaType(file.getContentType());
            imageEntity.setAd(adEntity);
            imageRepository.save(imageEntity);
        } catch (IOException e) {
            throw new IOException();
        }
        return imageEntity;
    }
    public ImageEntity updateImageForAd(AdEntity adEntity, MultipartFile file) throws IOException {
        ImageEntity imageEntity = new ImageEntity();
        try {
            imageEntity.setData(file.getBytes());
            imageEntity.setFileName(file.getName());
            imageEntity.setMediaType(file.getContentType());
            imageEntity.setAd(adEntity);
            imageEntity.setId(adEntity.getImage().getId());
            imageRepository.save(imageEntity);
        } catch (IOException e) {
            throw new IOException();
        }
        return imageEntity;
    }


    public Optional<ImageEntity> findImage(Integer id) {
        return imageRepository.findById(id);
    }
}
