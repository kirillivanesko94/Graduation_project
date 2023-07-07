package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<NewPassword> setPassword(@RequestBody String currentPassword, String newPassword) {
        return ResponseEntity.ok(new NewPassword(currentPassword,newPassword));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody String firstName, String lastName, String phone) throws IOException {
        return ResponseEntity.ok(new UpdateUser(firstName,lastName,phone));
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateImage(@RequestBody String image){
        return ResponseEntity.ok(new User());
    }
}
