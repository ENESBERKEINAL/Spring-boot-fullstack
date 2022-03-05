package com.example.questapp.controllers;

import com.example.questapp.entities.Post;
import com.example.questapp.requests.PostCreateRequest;
import com.example.questapp.requests.PostUpdateRequest;
import com.example.questapp.services.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;

    @GetMapping
    public List<Post> getAllPosts(@RequestParam Optional<Long> userId){
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createOnePost(@RequestBody PostCreateRequest newPostRequest){
        return postService.createOnePost(newPostRequest);
    }

    @PutMapping("/{postId}")
    public Post updatePostById(@PathVariable Long postId, @RequestBody PostUpdateRequest updateRequest) {
        return postService.updatePostById(postId, updateRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostbyId(@PathVariable Long postId){
        postService.deletePostById(postId);
    }
}
