package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.AdNotFoundException;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class AdService {
    private final AdEntityRepository adRepository;
    private final UserEntityRepository userEntityRepository;
    private final AdMapper adMapper;


    public AdService(AdEntityRepository adRepository, UserEntityRepository userEntityRepository, AdMapper adMapper) {
        this.adRepository = adRepository;
        this.userEntityRepository = userEntityRepository;
        this.adMapper = adMapper;
    }

    public Ad saveAd(Ad ad, MultipartFile image) {
        AdEntity adEntity = adMapper.mapToEntity(ad);
        adRepository.save(adEntity);
        return adMapper.mapToDto(adEntity);
    }

    public Ads getAllAds() {
        List<AdEntity> list = adRepository.findAll();
        Ad[] result = new Ad[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AdEntity adEntity = list.get(i);
            Ad ad = adMapper.mapToDto(adEntity);
            result[i] = ad;
        }
        Ads ads = new Ads();
        ads.setCount(result.length);
        ads.setResults(result);
        return ads;
    }

    public ExtendedAd getInformationAboutAd(int idAd) throws AdNotFoundException {
        Optional<AdEntity> optionalAdlEntity = adRepository.findById(idAd);
        if (optionalAdlEntity.isPresent()) {
            AdEntity adEntity = optionalAdlEntity.get();
            Ad ad = adMapper.mapToDto(adEntity);

            UserEntity userEntity = optionalAdlEntity.get().getUser();

            ExtendedAd extendedAd = new ExtendedAd();
            extendedAd.setPk(ad.getPk());
            extendedAd.setAuthorFirstName(userEntity.getFirstName());
            extendedAd.setAuthorLastName(userEntity.getLastName());
            extendedAd.setEmail(userEntity.getEmail());
            extendedAd.setImage(ad.getImage());
            extendedAd.setPhone(userEntity.getPhone());
            extendedAd.setPrice(ad.getPrice());
            extendedAd.setTitle(ad.getTitle());
            extendedAd.setDescription(adEntity.getDescription());

            return extendedAd;
        }
        throw new AdNotFoundException();
    }

    public void deleteAd(int idAd) throws AdNotFoundException {
        Optional<AdEntity> optionalAdEntity = adRepository.findById(idAd);
        if (optionalAdEntity.isPresent()) {
            AdEntity adEntity = optionalAdEntity.get();
            adRepository.deleteById(adEntity.getPk());
        } else {
            throw new AdNotFoundException();
        }
    }

    public Ad updateAD(int adId, String title, int price, String description) throws AdNotFoundException {
        Optional<AdEntity> optionalAdlEntity = adRepository.findById(adId);
        if (optionalAdlEntity.isPresent()) {
            AdEntity adEntity = optionalAdlEntity.get();

            CreateOrUpdateAd createOrUpdateAd = new CreateOrUpdateAd(title, price, description);
            adEntity.setTitle(createOrUpdateAd.getTitle());
            adEntity.setPrice(createOrUpdateAd.getPrice());
            adEntity.setDescription(createOrUpdateAd.getDescription());
            adRepository.save(adEntity);
            return adMapper.mapToDto(adEntity);
        } else {
            throw new AdNotFoundException();
        }
    }

    public Ads getAllAdsByUser(Principal principal) throws AdNotFoundException {
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findByEmail(principal.getName());
        System.out.println(principal.getName());
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            System.out.println(userEntity.getEmail());
            List<AdEntity> adEntity = adRepository.findAllByUserId(userEntity.getId());
            System.out.println(adEntity.size());
            Ad[] result = new Ad[adEntity.size()];
            for (int i = 0; i < adEntity.size(); i++) {
                AdEntity adEntity1 = adEntity.get(i);
                Ad ad = adMapper.mapToDto(adEntity1);
                result[i] = ad;
            }
            Ads ads = new Ads();
            ads.setCount(result.length);
            ads.setResults(result);
            return ads;
        } else {
            throw new AdNotFoundException();
        }
    }
    public String updateImage(int adId, MultipartFile file) throws AdNotFoundException {
//        Optional<AdEntity> optionalAdlEntity = adRepository.findById(adId);
//        if (optionalAdlEntity.isPresent()){
//            AdEntity adEntity = optionalAdlEntity.get();
//            adEntity.setImage(file.toString());
//            adRepository.save(adEntity);
//            return adEntity.getImage();
//        } else {
//            throw new AdNotFoundException();
//        }
        return file.toString();
    }

}

