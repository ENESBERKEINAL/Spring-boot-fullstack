package com.example.questapp.services;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.LikeRepository;
import com.example.questapp.requests.LikeCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeService {

    public LikeService(LikeRepository likeRepository,UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    LikeRepository likeRepository;
    UserService userService;
    PostService postService;

    public List<Like> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        if(userId.isPresent() && postId.isPresent()){
            return likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            return likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            return likeRepository.findByPostId(postId.get());
        }else
            return likeRepository.findAll();
    }

    public Like getLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOneLike(LikeCreateRequest likeCreateRequest) {

        User user = userService.getOneUserById(likeCreateRequest.getUserId());
        Post post = postService.getPostById(likeCreateRequest.getPostId());

        if(user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            likeToSave.setId(likeCreateRequest.getId());
            return likeRepository.save(likeToSave);
        }else
            return null;
    }

    public void deleteOneLikeByLikeId(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
