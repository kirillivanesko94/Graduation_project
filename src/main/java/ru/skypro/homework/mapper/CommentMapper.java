package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(source = "author", target = "user.id")
    CommentEntity toEntity(Comment comment);

    @Mapping(source = "user.id", target = "author")
    Comment toDTO(CommentEntity commentEntity);

    List<Comment> commentsListToDTO(List<CommentEntity> commentEntityList);
}