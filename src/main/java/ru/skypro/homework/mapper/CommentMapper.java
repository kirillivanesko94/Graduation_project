package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "authorImage", expression = "java(comment.getUser().getImage().toString())")
    @Mapping(source = "authorFirstName", target = "user.firstName")
    CommentEntity toEntity(Comment comment);

    @Mapping(target = "authorImage", expression = "java(new Image())")
    @Mapping(target = "authorFirstName", source = "user.firstName")
    Comment toDTO(CommentEntity commentEntity);

    Comments commentsListToDTO(int count, CommentEntity[] results);
}