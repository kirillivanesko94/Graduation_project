package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

@Mapper
public interface UserMapper {
    static User toDTO(UserEntity userEntity){
        User user = new User();
        user.setId(user.getId());
        user.setFirstName(userEntity.getFirstName());
        user.setLastName(userEntity.getLastName());
        user.setEmail(userEntity.getEmail());
        user.setPhone(userEntity.getPhone());
        user.setImage(userEntity.getImage());
        return user;
    }
    //UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //User toDTO(UserEntity user);
    //UserEntity toEntity(User userDto);
}