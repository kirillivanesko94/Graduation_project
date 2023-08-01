package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.AvatarRepository;
import ru.skypro.homework.repository.UserEntityRepository;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

@Service
public class AvatarService {
   private final AvatarRepository avatarRepository;
   private final UserEntityRepository userEntityRepository;


    public AvatarService(AvatarRepository avatarRepository, UserEntityRepository userEntityRepository) {
        this.avatarRepository = avatarRepository;
        this.userEntityRepository = userEntityRepository;
    }
    public AvatarEntity saveAvatarForUser(Principal principal, MultipartFile file) throws IOException {
        AvatarEntity avatarEntity = new AvatarEntity();
        UserEntity userEntity = userEntityRepository.findByEmail(principal.getName()).get();
        try {
            avatarEntity.setData(file.getBytes());
            avatarEntity.setFileName(file.getName());
            avatarEntity.setMediaType(file.getContentType());
            avatarEntity.setUser(userEntity);
            avatarRepository.save(avatarEntity);
            userEntity.setAvatarEntity(avatarEntity);
        } catch (IOException e) {
            throw new IOException();
        }
        return avatarEntity;
    }
    public Optional<AvatarEntity> findAvatar(Integer id) {
        return avatarRepository.findById(id);
    }
}
