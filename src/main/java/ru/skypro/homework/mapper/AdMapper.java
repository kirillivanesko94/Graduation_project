package ru.skypro.homework.mapper;

import org.mapstruct.*;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.dto.ExtendedAd;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

import java.util.List;

/**
 * This class implements the functionality of ad mapping
 */
@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AdMapper {
    /**
     * Method for mapping an entity in DTO ad
     * @param adEntity {@link AdEntity}
     * @return DTO ad
     */
    @Mapping(source = "user.id", target = "author")
    @Mapping(target = "image", source = "image.ad", qualifiedByName = "imagesToString")
    Ad mapToDto(AdEntity adEntity);

    /**
     * Method for mapping DTO createOrUpdateAd to ad entity
     * @param createOrUpdateAd {@link CreateOrUpdateAd}
     * @return Ad entity
     */
    AdEntity mapCreateOrUpdateDtoTo (CreateOrUpdateAd createOrUpdateAd);

    /**
     * Method for mapping DTO ad to  entity
     * @param ad {@link Ad}
     * @return Ad entity
     */
    @Mapping(source = "author", target = "user.id")
    @Mapping(ignore = true, target = "image")
    AdEntity mapToEntity(Ad ad);

    /**
     * Method for mapping the Ad entity list to the Ad DTO list
     * @param adEntities list to {@link AdEntity}
     * @return list DTO ad
     */
    List<Ad> mapToListDto(List<AdEntity> adEntities);

    /**
     * Method for mapping AD entity to extended AD (DTO)
     * @param adEntity {@link AdEntity}
     * @return full information about Ad (DTO)
     */
    @Mapping(source = "user.firstName", target = "authorFirstName")
    @Mapping(source = "user.lastName", target = "authorLastName")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(target = "image", source = "image.ad", qualifiedByName = "imagesToString")
    ExtendedAd mapToExtAdDto(AdEntity adEntity);

    /**
     * Method for mapping an image to a String value
     * @param adEntity {@link AdEntity}
     * @return The link for displaying the image is passed to the controller {@link ru.skypro.homework.controller.ImageController}
     */
    @Named(value = "imagesToString")
    default String imagesToString(AdEntity adEntity){
        ImageEntity imageEntity = adEntity.getImage();
        if(imageEntity == null){
            return null;
        }
        return "/images/" + imageEntity.getAd().getPk().toString();

    }

}
