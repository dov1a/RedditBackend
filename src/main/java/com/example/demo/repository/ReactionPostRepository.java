package com.example.demo.repository;

import com.example.demo.model.ReactionPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReactionPostRepository extends JpaRepository<ReactionPost, Integer> {
}
