package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
    @PostMapping("set_password")//ok
    public ResponseEntity<?> setPassword(Principal principal, @RequestBody NewPassword newPassword) {
        service.setPassword(principal, newPassword.getNewPassword());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser(Principal principal){
        try{
            return ResponseEntity.ok(service.getUser(principal));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(Principal principal, @RequestBody UpdateUser updateUser){
            return ResponseEntity.ok(service.updateUser(principal, updateUser.getFirstName(), updateUser.getLastName(), updateUser.getPhone()));

    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(Principal principal, @RequestParam MultipartFile image){
        service.updateImage(principal, image);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
