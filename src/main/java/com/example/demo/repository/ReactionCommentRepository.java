package com.example.demo.repository;

import com.example.demo.model.ReactionComment;
import com.example.demo.model.ReactionPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionCommentRepository extends JpaRepository<ReactionComment, Integer> {
}
