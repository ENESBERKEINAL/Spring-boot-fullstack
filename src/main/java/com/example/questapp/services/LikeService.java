package com.example.questapp.services;

import com.example.questapp.entities.Like;
import com.example.questapp.entities.Post;
import com.example.questapp.entities.User;
import com.example.questapp.repos.LikeRepository;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LikeService {

    public LikeService(LikeRepository likeRepository,UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
        this.postService = postService;
    }

    private LikeRepository likeRepository;
    private UserService userService;
    private PostService postService;

    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if(userId.isPresent() && postId.isPresent()){
            list= likeRepository.findByUserIdAndPostId(userId.get(),postId.get());
        }else if(userId.isPresent()){
            list =likeRepository.findByUserId(userId.get());
        }else if(postId.isPresent()){
            list =likeRepository.findByPostId(postId.get());
        }else
            list =likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
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
