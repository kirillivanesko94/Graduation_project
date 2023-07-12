package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.repository.AdEntityRepository;

@Service
public class AdService {
    private final AdEntityRepository adRepository;

    public AdService(AdEntityRepository adRepository) {
        this.adRepository = adRepository;
    }

    public AdEntity saveAd(Ad ad) {
        return adRepository.save(AdMapper.INSTANCE.toEntity(ad));
    }
}

