package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Faktury;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakturyRepository extends JpaRepository<Faktury, Integer> {
}