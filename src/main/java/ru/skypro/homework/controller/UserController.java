package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.UserServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody String currentPassword, String newPassword) {
        return ResponseEntity.ok(service.setPassword(currentPassword, newPassword));
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        try{
            return ResponseEntity.ok(service.getUser());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody String firstName, String lastName, String phone) {
        try{
            return ResponseEntity.ok(service.updateUser(firstName, lastName, phone));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateImage(@RequestBody String image){
        try{
            return ResponseEntity.ok(service.updateImage(image));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
