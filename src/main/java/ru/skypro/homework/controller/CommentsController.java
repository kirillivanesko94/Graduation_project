package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Comment;
import ru.skypro.homework.dto.Comments;
import ru.skypro.homework.service.CommentsService;

import java.security.Principal;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/ads")
public class CommentsController {
    private final CommentsService service;


    public CommentsController(CommentsService service) {
        this.service = service;
    }

    /**
     * This method accesses the datebase to get a comment by id
     *
     * @param id ads
     * @return Received comment
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<Comments> getComments(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getAllComments(id));
    }

    /**
     * This method adds a new comment to the ad and saves it in the database
     *
     * @param id ad
     * @param comment created comment
     * @param principal
     * @return Saved comment in the database
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Integer id, @RequestBody Comment comment, Principal principal) {
        return ResponseEntity.ok(service.addComment(comment,principal,id));
    }

    /**
     * This method removes the announcement comment from the database
     *
     * @param adId id ads
     * @param commentId id comment
     * @return Successful deletion of the comment from the database
     */
    @DeleteMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Integer adId, @PathVariable Integer commentId){
        service.removeComment(commentId);
        return ResponseEntity.ok().build();
    }

    /**
     *This method saves the comment changes to the database
     *
     * @param adId id ads
     * @param commentId id comment
     * @param updateComment updated comment data
     * @return Updated comment data stored in the database
     * @throws Exception
     */
    @PatchMapping("/{adId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer adId, @PathVariable Integer commentId,
                                                 @RequestBody Comment updateComment) throws Exception {
        return ResponseEntity.ok(service.updateComment(commentId, updateComment));
    }

}
