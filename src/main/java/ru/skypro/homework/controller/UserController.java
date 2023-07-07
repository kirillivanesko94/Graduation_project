package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody String currentPassword, String newPassword) {
        return new NewPassword(currentPassword, newPassword);
    }

    @GetMapping("/me")
    public User getUser() {
        return new User();
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody String firstName, String lastName, String phone) throws IOException {
        return new UpdateUser(firstName, lastName, phone);
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User updateImage(@RequestBody String image){
        User user = new User();
        user.setImage(image);
        return user;
    }
}
