package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.entity.AdEntity;

@Mapper(componentModel = "spring")
public interface AdMapper {
    @Mapping(target = "image", expression="java(adEntity.getImage().toString())")
    Ad mapToDto(AdEntity adEntity);
    @Mapping(target = "image", expression="java(adEntity.getImage())")
    AdEntity mapToEntity(Ad ad);


}
