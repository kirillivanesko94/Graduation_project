package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor

public class RegistrationController {
    @PostMapping("/register")
    public Register registrationNewUser(@RequestBody String userName, String password,
                                        String firstName, String lastName, String phone, Role role) {
        return new Register();
    }
}
