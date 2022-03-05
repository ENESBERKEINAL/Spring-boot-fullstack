package com.example.questapp.repos;

import com.example.questapp.entities.Comment;
import com.example.questapp.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {


}
