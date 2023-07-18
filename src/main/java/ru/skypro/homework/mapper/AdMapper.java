package ru.skypro.homework.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "image", expression = "java(adEntity.getImage().getFileName())")
    Ad mapToDto(AdEntity adEntity);
    @Mapping(source = "author", target = "user.id")
    @Mapping(ignore = true, target = "image")
    AdEntity mapToEntity(Ad ad);

    List<Ad> mapToListDto(List<AdEntity> adEntities);
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(target = "image", expression = "java(adEntity.getImage().getFileName())")
    ExtendedAd mapToExtAdDto(AdEntity adEntity);

}
