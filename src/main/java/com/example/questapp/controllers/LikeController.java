package com.example.questapp.controllers;

import com.example.questapp.entities.Like;
import com.example.questapp.requests.LikeCreateRequest;
import com.example.questapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    private LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<Like> getAllLikesWithParam(@RequestParam Optional<Long> userId, @RequestParam Optional<Long> postId ){
        return likeService.getAllLikesWithParam(userId,postId);
    }

    @GetMapping("/{likeId}")
    public Like getLikeById(@PathVariable Long likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public Like createOneLike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createOneLike(likeCreateRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteOneLikeByLikeId(@PathVariable Long likeId){
        likeService.deleteOneLikeByLikeId(likeId);
    }
}
