package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduktyZamowienieRepository extends JpaRepository<ProduktyZamowienie, Produkt> {
}