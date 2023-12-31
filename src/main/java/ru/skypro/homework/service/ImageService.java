package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.repository.ImageRepository;
import ru.skypro.homework.repository.UserEntityRepository;

import java.io.IOException;
import java.util.Optional;

/**
 * This class implements the functionality of working with images
 */
@Service
public class ImageService {
   private final ImageRepository imageRepository;
   private final UserEntityRepository userEntityRepository;

    public ImageService(ImageRepository imageRepository, UserEntityRepository userEntityRepository) {
        this.imageRepository = imageRepository;
        this.userEntityRepository = userEntityRepository;
    }

    /**
     *   Method of saving the avatar for the ad
     * @param adEntity Learn more in {@link AdEntity}{}
     * @param file Image file
     * @return Image entity
     * @throws IOException default error for working with {@link MultipartFile}
     */
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

    /**
     *   Method of update the avatar for the ad
     * @param adEntity Learn more in {@link AdEntity}{}
     * @param file Image file
     * @return Image entity
     * @throws IOException default error for working with {@link MultipartFile}
     */
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

    /**
     * Method of getting an image by id
     * @param id must not be null
     * @return optional image
     */
    public Optional<ImageEntity> findImage(Integer id) {
        return imageRepository.findById(id);
    }
}
