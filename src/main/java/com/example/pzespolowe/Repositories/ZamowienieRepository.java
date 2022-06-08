package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Zamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZamowienieRepository extends JpaRepository<Zamowienie, Integer> {
    @Query(nativeQuery = true, value = "SELECT * from zamowienie where STATUS = :status")
    List<Zamowienie> findZamowienieByStatus(String status);
}