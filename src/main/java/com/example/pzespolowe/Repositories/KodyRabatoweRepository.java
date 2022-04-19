package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.KodyRabatowe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KodyRabatoweRepository extends JpaRepository<KodyRabatowe, Integer> {
}