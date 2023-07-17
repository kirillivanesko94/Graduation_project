package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "image", expression="java(userEntity.getImage().toString())")
    User toDTO(UserEntity userEntity);

}