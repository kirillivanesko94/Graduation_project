package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.CommentEntityRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private final CommentEntityRepository repository;
    private final CommentMapper commentMapper;

    public CommentsService(CommentEntityRepository repository, CommentMapper commentMapper) {
        this.repository = repository;
        this.commentMapper = commentMapper;
    }

    public Comment addComment(Comment comment) {
        repository.save(commentMapper.toEntity(comment));
        return comment;
    }

    public void removeComment(int id) {
        repository.deleteById(id);
    }

    public Comment updateComment(int id, Comment updateComment) throws Exception{
        Optional<CommentEntity> entity = repository.findById(id);
        if (entity.isPresent()) {
            CommentEntity newCommentEntity = entity.get();
            newCommentEntity.setText(commentMapper.toEntity(updateComment).getText());
            repository.save(newCommentEntity);
            return commentMapper.toDTO(newCommentEntity);
        }
        throw new Exception("Отзыв не найден");
    }

    public Comments getAllComments() {
        List<CommentEntity> list = repository.findAll();
        Comments comments = new Comments();
        List<Comment> result = commentMapper.commentsListToDTO(list);
        comments.setCount(result.size());
        comments.setResults(result);
        return comments;
    }


}
