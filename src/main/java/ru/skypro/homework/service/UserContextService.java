package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserEntityRepository;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserContextService {
    private final UserEntityRepository repository;

    public UserContextService(UserEntityRepository repository) {
        this.repository = repository;
    }

    public List<UserDetails> doAllUsersToContext(){
        return repository.findAll().stream().map(this::doUserInContext).collect(Collectors.toList());
    }

    public UserDetails doUserInContext(UserEntity user){
        return org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getEmail())
                .roles(user.getRole().name())
                .build();
    }
}
