package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl {
    private final CommentEntityRepository repository;

    public CommentsServiceImpl(CommentEntityRepository repository) {
        this.repository = repository;
    }

    public Comment addComment(Comment comment) {
        repository.save(CommentMapper.INSTANCE.toEntity(comment));
        return comment;
    }

    public void removeComment(int id) {
        repository.deleteById(id);
    }

    public Comment updateComment(int id, Comment updateComment) throws Exception{
        Optional<CommentEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            CommentEntity newCommentEntity = entity.get();
            newCommentEntity.setText(CommentMapper.INSTANCE.toEntity(updateComment).getText());
            repository.save(newCommentEntity);
            return CommentMapper.INSTANCE.toDTO(newCommentEntity);
        }
        throw new Exception("Отзыв не найден");
    }

    public CommentEntity[] getAllComments() {
        List<CommentEntity> list = repository.findAll();
        CommentEntity[] arr = new CommentEntity[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }


}
