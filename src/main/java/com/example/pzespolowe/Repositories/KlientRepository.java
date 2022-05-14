package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Klient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlientRepository extends JpaRepository<Klient, Integer> {
}