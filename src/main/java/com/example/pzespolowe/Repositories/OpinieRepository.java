package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Opinie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinieRepository extends JpaRepository<Opinie, Integer> {
}