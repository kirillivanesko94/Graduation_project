package ru.skypro.homework.service;

import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
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
    public UserService(UserEntityRepository repository, UserDetailsManager manager, UserMapper userMapper) {
        this.repository = repository;
        this.manager = manager;
        this.userMapper = userMapper;
    }

    public NewPassword setPassword(String currentPassword, String newPassword){
        manager.changePassword(currentPassword, newPassword);
        return new NewPassword(currentPassword, newPassword);
    }

    public User getUser(Principal principal) throws Exception {
        return userMapper.toDTO(repository.findByEmail(principal.getName()).orElseThrow(() -> new Exception("Пользователь не найден")));
        //return UserMapper.toDTO(new UserEntity());
    }
    public UpdateUser updateUser(Principal principal, String firstName, String lastName, String phone) throws Exception {
        Optional<UserEntity> userEntity = repository.findByEmail(principal.getName());
        if (userEntity.isPresent()){
            UserEntity newUserEntity = userEntity.get();
            newUserEntity.setFirstName(firstName);
            newUserEntity.setLastName(lastName);
            newUserEntity.setPhone(phone);
            repository.save(newUserEntity);
            return new UpdateUser(firstName, lastName, phone);
        }
        throw new Exception("Пользователь не найден");
    }

    public User updateImage(Principal principal, MultipartFile image) throws Exception {
        Optional<UserEntity> userEntity = repository.findByEmail(principal.getName());
        if (userEntity.isPresent()){
            UserEntity newUserEntity = userEntity.get();
            //newUserEntity.setImage(image);
            repository.save(newUserEntity);
            return userMapper.toDTO(newUserEntity);
        }
        throw new Exception("Пользователь не найден");
    }
}