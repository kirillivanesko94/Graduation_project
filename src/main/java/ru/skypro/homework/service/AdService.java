package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdService {
    private final AdEntityRepository adRepository;

    public AdService(AdEntityRepository adRepository) {
        this.adRepository = adRepository;
    }

    public Ad saveAd(AdEntity adEntity) {
        return AdMapper.mapToDto(adRepository.save(adEntity));
    }

    public Ads getAllAds() {
        List<AdEntity> list = adRepository.findAll();
        Ad[] result = new Ad[list.size()];
        for (int i = 0; i < list.size(); i++) {
            AdEntity adEntity = list.get(i);
            Ad ad = AdMapper.mapToDto(adEntity);
            result[i] = ad;
        }
        Ads ads = new Ads();
        ads.setCount(result.length);
        ads.setResults(result);
        return ads;
    }

    public ExtendedAd getInformationAboutAd(int id) {
        Optional<AdEntity> adEntity = adRepository.findById(id);
        if (adEntity.isPresent()) {
            AdEntity entity = adEntity.get();
            Ad ad = AdMapper.mapToDto(entity);
            ExtendedAd extendedAd = new ExtendedAd();
            extendedAd.setPk(ad.getPk());
            extendedAd.setImage(ad.getImage());

        }
        return null;
    }

}

