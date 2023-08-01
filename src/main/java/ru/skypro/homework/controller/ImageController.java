package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.ImageService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class ImageController {
    private final ImageService imageService;
    private final AvatarService avatarService;
    Logger logger = LoggerFactory.getLogger(ImageController.class);

    public ImageController(ImageService imageService, AvatarService avatarService) {
        this.imageService = imageService;
        this.avatarService = avatarService;
    }

    @GetMapping(value = "/images/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getImage(@PathVariable Integer id) {
        logger.info("Was invoked method getImage - adId: {}", id);
        ImageEntity image = imageService.findImage(id).get();
        return image.getData();
    }
    @GetMapping(value = "/avatar/{id}", produces = {MediaType.IMAGE_PNG_VALUE})
    public byte[] getAvatar(@PathVariable Integer id) {
        logger.info("Was invoked method getAvatar - Id: {}", id);
        AvatarEntity image = avatarService.findAvatar(id).get();
        return image.getData();
    }
}
