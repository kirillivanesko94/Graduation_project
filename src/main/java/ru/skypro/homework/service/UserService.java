package ru.skypro.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
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
    private final PasswordEncoder passwordEncoder;
    public UserService(UserEntityRepository repository, UserDetailsManager manager, UserMapper userMapper, ImageService imageService, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.manager = manager;
        //this.manager = manager;
        this.userMapper = userMapper;
        this.imageService = imageService;
       // this.passwordEncoder = passwordEncoder;
        this.passwordEncoder = passwordEncoder;
    }

    public NewPassword setPassword(Principal principal, String newPassword){
        UserEntity userEntity = repository.findByEmail(principal.getName()).get();
        NewPassword newPasswordDTO = new NewPassword(userEntity.getPassword(), newPassword);
        manager.changePassword(userEntity.getPassword(), passwordEncoder.encode(newPassword));
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        repository.save(userEntity);
        return newPasswordDTO;
    }

    public User getUser(Principal principal) {
        return userMapper.toDTO(repository.findByEmail(principal.getName()).orElse(new UserEntity()));
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
    public void addInTable(Register register){
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(passwordEncoder.encode(register.getPassword()));
        userEntity.setEmail(register.getUsername());
        userEntity.setPhone(register.getPhone());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setRole(register.getRole());
        repository.save(userEntity);
    }
}