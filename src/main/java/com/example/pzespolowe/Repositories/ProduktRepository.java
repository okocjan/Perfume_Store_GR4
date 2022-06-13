package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Produkt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduktRepository extends JpaRepository<Produkt, Integer> {
}