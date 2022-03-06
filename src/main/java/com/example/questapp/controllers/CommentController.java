package com.example.questapp.controllers;

import com.example.questapp.entities.Comment;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;
import com.example.questapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest){
        return commentService.createOneComment(newCommentRequest);
    }


    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/{commentId}")
    public Comment updateCommentById(@PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest){
        return commentService.updateCommentById(commentId,commentUpdateRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }



}
