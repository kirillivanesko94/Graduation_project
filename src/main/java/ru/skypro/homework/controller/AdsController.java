package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.service.impl.AdService;

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

    @PostMapping()
    public ResponseEntity<Ad> addAds(@RequestBody Ad ad) {
        adService.saveAd(ad);
        return ResponseEntity.ok(ad);

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
        adService.updateAD(id, createOrUpdateAd.getTitle(), createOrUpdateAd.getPrice(), createOrUpdateAd.getDescription());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Ads> getAllAdsByUser(Principal principal) throws AdNotFoundException{
        return ResponseEntity.ok(adService.getAllAdsByUser(principal));
    }

    @PatchMapping(value = "/{id}/image")
    public ResponseEntity<String> updateImageAd(@PathVariable int id, @RequestBody Ad ad) throws AdNotFoundException {
        return ResponseEntity.ok(adService.updateImage(id, ad.getImage()));
    }
}
