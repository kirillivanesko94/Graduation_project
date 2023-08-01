package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.AvatarEntity;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "image", source = "avatarEntity.user", qualifiedByName = "avatarToString")
    User toDTO(UserEntity userEntity);

    @Named("avatarToString")
    default String avatarToString(UserEntity userEntity) {
        AvatarEntity avatarEntity = userEntity.getAvatarEntity();
        if (avatarEntity == null) {
            return null;
        }
        return "/avatar/" + avatarEntity.getId().toString();
    }
}