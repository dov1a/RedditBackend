package com.example.demo.repository;

import com.example.demo.model.Flair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlairRepository extends JpaRepository<Flair, Integer> {
}
