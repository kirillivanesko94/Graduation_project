package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.service.AdService;

import java.io.IOException;
import java.security.Principal;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Ad> addAds(@RequestPart("properties") CreateOrUpdateAd createOrUpdateAd,
                                     @RequestPart("image") MultipartFile image, Principal principal) throws IOException {
        Ad ad = adService.saveAd(createOrUpdateAd, image, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(ad);

    }

    @GetMapping("{id}")
    public ResponseEntity<ExtendedAd> getInformationAboutAd(@PathVariable int id) throws AdNotFoundException {
        return ResponseEntity.ok(adService.getInformationAboutAd(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteAd(@PathVariable int id) throws AdNotFoundException {
        adService.deleteAd(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<Ad> updateAd(@PathVariable int id, @RequestBody CreateOrUpdateAd createOrUpdateAd)
            throws AdNotFoundException {
        adService.updateAD(id, createOrUpdateAd);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAllAdsByUser(Principal principal) throws AdNotFoundException {
        return ResponseEntity.ok(adService.getAllAdsByUser(principal));
    }

    @PatchMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestParam("image") MultipartFile image) throws AdNotFoundException {
        return ResponseEntity.ok(adService.updateImage(id, image));
    }
}
