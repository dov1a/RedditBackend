package com.example.demo.repository;

import com.example.demo.model.Banned;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannedRepository extends JpaRepository<Banned, Integer> {
}
