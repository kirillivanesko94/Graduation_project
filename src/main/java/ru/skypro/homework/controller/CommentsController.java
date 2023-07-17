package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.entity.CommentEntity;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.service.CommentsService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final CommentsService service;
    private final CommentMapper commentMapper;

    public CommentsController(CommentsService service, CommentMapper commentMapper) {
        this.service = service;
        this.commentMapper = commentMapper;
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable int id) {
        CommentEntity[] allComments = service.getAllComments();
        return ResponseEntity.ok(commentMapper.commentsListToDTO(allComments.length, allComments));
    }
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int id, @RequestBody Comment comment) {
        return ResponseEntity.ok(service.addComment(comment));
    }
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable int adId, int commentId){
        service.removeComment(commentId);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int adId, int commentId,
                                                 @RequestBody Comment updateComment) throws Exception {
        return ResponseEntity.ok(service.updateComment(commentId, updateComment));
    }

}
