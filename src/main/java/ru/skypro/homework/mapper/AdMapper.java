package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "image", source = "image.ad", qualifiedByName = "imagesToString")
    Ad mapToDto(AdEntity adEntity);
    AdEntity mapCreateOrUpdateDtoTo (CreateOrUpdateAd createOrUpdateAd);
    @Mapping(source = "author", target = "user.id")
    @Mapping(ignore = true, target = "image")
    AdEntity mapToEntity(Ad ad);

    List<Ad> mapToListDto(List<AdEntity> adEntities);
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(target = "image", source = "image.ad", qualifiedByName = "imagesToString")
    ExtendedAd mapToExtAdDto(AdEntity adEntity);
    @Named(value = "imagesToString")
    default String imagesToString(AdEntity adEntity){
        ImageEntity imageEntity = adEntity.getImage();
        if(imageEntity == null){
            return null;
        }
        return "/images/" + imageEntity.getAd().getPk().toString();

    }

}
