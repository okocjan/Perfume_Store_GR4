package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Wysylki;
import com.example.pzespolowe.Models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WysylkiRepository extends JpaRepository<Wysylki, Zamowienie> {
}