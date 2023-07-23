package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.repository.AdEntityRepository;
import ru.skypro.homework.repository.CommentEntityRepository;
import ru.skypro.homework.repository.UserEntityRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    private final CommentEntityRepository repository;
    private final CommentMapper commentMapper;
    private final UserEntityRepository userEntityRepository;
    private final AdEntityRepository adEntityRepository;

    public CommentsService(CommentEntityRepository repository, CommentMapper commentMapper, UserEntityRepository userEntityRepository, AdEntityRepository adEntityRepository) {
        this.repository = repository;
        this.commentMapper = commentMapper;
        this.userEntityRepository = userEntityRepository;
        this.adEntityRepository = adEntityRepository;
    }

    public Comment addComment(Comment comment, Principal principal, Integer id) {
        String email  = principal.getName();
        Optional<UserEntity> optionalUserEntity = userEntityRepository.findByEmail(email);
        UserEntity userEntity = optionalUserEntity.get();
        Optional<AdEntity> optionalAdEntity = adEntityRepository.findById(id);
        AdEntity ad = optionalAdEntity.get();
        Comment comment1 = new Comment();
        comment1.setAuthor(userEntity.getId());
        CommentEntity commentEntity = commentMapper.toEntity(comment);
        commentEntity.setAdEntity(ad);
        commentEntity.setUser(userEntity);
        repository.save(commentEntity);
        return comment;
    }

    public void removeComment( Integer idComment) {
        repository.deleteById(idComment);
    }

    public Comment updateComment(Integer id, Comment updateComment) throws Exception{
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
