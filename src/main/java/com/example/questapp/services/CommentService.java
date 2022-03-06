package com.example.questapp.services;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.CommentRepository;
import com.example.questapp.requests.CommentCreateRequest;
import com.example.questapp.requests.CommentUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    public CommentService(CommentRepository commentRepository,UserService userService, PostService postService) {
        this.commentRepository = commentRepository;
        this.postService = postService;
        this.userService = userService;
    }

    private CommentRepository commentRepository;
    private UserService userService;
    private PostService postService;

    public List<Comment> getAllCommentsWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() & postId.isPresent()){
            return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            return commentRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return commentRepository.findByPostId(postId.get());
        }else
            return commentRepository.findAll();
    }

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public Comment createOneComment(CommentCreateRequest newCommentRequest) {
        User user = userService.getOneUserById(newCommentRequest.getUserId());
        Post post = postService.getPostById(newCommentRequest.getPostId());

        if (user != null && post != null ){
            Comment commentToSave = new Comment();
            commentToSave.setId(newCommentRequest.getId());
            commentToSave.setText(newCommentRequest.getText());
            commentToSave.setUser(user);
            commentToSave.setPost(post);
            return commentRepository.save(commentToSave);
        }else
            return null;


    }

    public Comment updateCommentById(Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Optional<Comment> comment = commentRepository.findById(commentId);
        if(comment.isPresent()){
            Comment commentToUpdate = comment.get();
            commentToUpdate.setText(commentUpdateRequest.getText());
            return commentRepository.save(commentToUpdate);
        }else
            return null;
    }

    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
