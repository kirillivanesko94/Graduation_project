package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper
public interface AdMapper {
//    AdMapper INSTANCE = Mappers.getMapper(AdMapper.class);
//    AdEntity toEntity(Ad ad);
    static Ad mapToDto(AdEntity adEntity){
        Ad ad = new Ad();
        ad.setAuthor(adEntity.getAuthor());
        ad.setImage(adEntity.getImage());
        ad.setPk(adEntity.getPk());
        ad.setPrice(adEntity.getPrice());
        ad.setTitle(adEntity.getTitle());
        return ad;
    }
    static AdEntity mapToEntity(Ad ad) {
        AdEntity adEntity = new AdEntity();
        adEntity.setAuthor(ad.getAuthor());
        adEntity.setImage(ad.getImage());
        adEntity.setPk(ad.getPk());
        adEntity.setPrice(ad.getPrice());
        adEntity.setTitle(ad.getTitle());
        return adEntity;
    }

}
