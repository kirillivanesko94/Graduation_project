package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserEntityRepository;

import java.security.Principal;
import java.util.Optional;

@Service
public class UserService {
    private final UserEntityRepository repository;
    private final UserDetailsManager manager;
    private final UserMapper userMapper;
    private final ImageService imageService;
    public UserService(UserEntityRepository repository, UserDetailsManager manager, UserMapper userMapper, ImageService imageService) {
        this.repository = repository;
        this.manager = manager;
        this.userMapper = userMapper;
        this.imageService = imageService;
    }

    public ResponseEntity<?> setPassword(Principal principal, String newPassword){
       // manager.changePassword("password", newPassword);//add db
        String currentPassword = "password";
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    public User getUser(Principal principal) {
        return userMapper.toDTO(repository.findByEmail(principal.getName()).orElse(new UserEntity()));
        //return UserMapper.toDTO(new UserEntity());
    }
    public UpdateUser updateUser(Principal principal, String firstName, String lastName, String phone)  {//ок
        Optional<UserEntity> userEntity = repository.findByEmail(principal.getName());
        if (userEntity.isPresent()){
            UserEntity newUserEntity = userEntity.get();
            newUserEntity.setFirstName(firstName);
            newUserEntity.setLastName(lastName);
            newUserEntity.setPhone(phone);
            repository.save(newUserEntity);
            return new UpdateUser(firstName, lastName, phone);
        }
        return new UpdateUser(firstName,lastName,phone);
    }

    public User updateImage(Principal principal, MultipartFile image) {
        Optional<UserEntity> userEntity = repository.findByEmail(principal.getName());
        if (userEntity.isPresent()){
            ImageEntity uploadImage = imageService.saveImage(image);
            UserEntity newUserEntity = userEntity.get();
            newUserEntity.setImage(uploadImage);
            repository.save(newUserEntity);
            return userMapper.toDTO(newUserEntity);
        }
        return null;
    }
}