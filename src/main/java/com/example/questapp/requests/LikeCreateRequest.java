package com.example.questapp.requests;
import lombok.Data;

@Data
public class LikeCreateRequest {

    long userId;
    long postId;
    long id;
}
