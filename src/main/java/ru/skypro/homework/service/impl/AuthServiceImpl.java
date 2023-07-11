package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    //Расширение UserDetailsService, которое предоставляет возможность создавать новых пользователей и обновлять существующих.
    private final UserDetailsManager manager;
    //Сервисный интерфейс для кодирования паролей. Предпочтительной реализацией является BCryptPasswordEncoder
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder,
                           UserEntityRepository repository) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }
    //userExists(String username): Проверьте, существует ли в системе пользователь с указанным именем входа.

    //Методы, унаследованные от interface org.springframework.security.core.userdetails.UserDetailsService - loadUserByUsername
    //loadUserByUsername(String username): Определяет местонахождение пользователя на основе имени пользователя.

    //encode(CharSequence rawPassword): Закодируйте необработанный пароль.
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    //createUser(UserDetails user)
    //Создайте нового пользователя с предоставленными данными.
    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        return true;
    }

}
