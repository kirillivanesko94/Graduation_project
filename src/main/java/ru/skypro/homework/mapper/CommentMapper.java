package ru.skypro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;


@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "pk", target = "id")
    CommentEntity toEntity(Comment comment);

    @Mapping(source = "id", target = "pk")
    Comment toDTO(CommentEntity commentEntity);

    Comments commentsListToDTO(int count, CommentEntity[] results);
}
