package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.service.AdService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("ads")
public class AdsController {
    private final AdService adService;

    public AdsController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping()
    public ResponseEntity<Ads> getAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @PostMapping()
    public ResponseEntity<Ad> addAds(@RequestBody AdEntity adEntity) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(adService.saveAd(adEntity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Ad> getInformationAboutAd(@PathVariable int id) {
        return ResponseEntity.ok(new Ad());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Ad> deleteAd(@PathVariable int id) {
        return ResponseEntity.ok(new Ad());
    }

    @PatchMapping("{id}")
    public ResponseEntity<CreateOrUpdateAd> updatetAd(@PathVariable int id, @RequestBody String title, int price, String description) {
        return ResponseEntity.ok(new CreateOrUpdateAd(title, price, description));
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAllAdsByUser() {
        return ResponseEntity.ok(new Ads());
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<User> updateImageAd(@PathVariable int id, @RequestBody String image) {
        return ResponseEntity.ok(new User());
    }
}
