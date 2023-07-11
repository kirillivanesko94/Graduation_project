package ru.skypro.homework.service.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.repository.UserEntityRepository;

import java.util.Optional;

@Service
public class UserServiceImpl {
    private final UserEntityRepository repository;
    //Расширение UserDetailsService, которое предоставляет возможность создавать новых пользователей и обновлять существующих.
    private final UserDetailsManager manager;
    //Сервисный интерфейс для кодирования паролей. Предпочтительной реализацией является BCryptPasswordEncoder
    private final PasswordEncoder encoder;
    private final int id;
    private final String name;

    public UserServiceImpl(UserEntityRepository repository, UserDetailsManager manager, PasswordEncoder encoder) {
        this.repository = repository;
        this.manager = manager;
        this.encoder = encoder;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();
        this.id = userEntity.getId();
        this.name = authentication.getName();
    }

    public NewPassword setPassword(String currentPassword, String newPassword){
        manager.changePassword(currentPassword, newPassword);
        return new NewPassword(currentPassword, newPassword);
    }

    public User getUser() throws Exception {
        return UserMapper.INSTANCE.toDTO(repository.findById(id).orElseThrow(() -> new Exception("Пользователь не найден")));
    }
    public UpdateUser updateUser(String firstName, String lastName, String phone) throws Exception {
        Optional<UserEntity> userEntity = repository.findById(id);
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

    public User updateImage(String image) throws Exception {
        Optional<UserEntity> userEntity = repository.findById(id);
        if (userEntity.isPresent()){
            UserEntity newUserEntity = userEntity.get();
            newUserEntity.setImage(image);
            repository.save(newUserEntity);
            return UserMapper.INSTANCE.toDTO(newUserEntity);
        }
        throw new Exception("Пользователь не найден");
    }
    ////@RequestMapping("/handle")
    //    //public ResponseEntity<String> handle(HttpServletRequest httpRequest) {
    //    //  String userId= httpRequest.getHeader("user_id");
    //    //  HttpHeaders responseHeaders = new HttpHeaders();
    //    //  responseHeaders.set("user_id", userId);
    //    //  return new ResponseEntity<String>("Hello World", responseHeaders, HttpStatus.CREATED);
    //    //}
}
