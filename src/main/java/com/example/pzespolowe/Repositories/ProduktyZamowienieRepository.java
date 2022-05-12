package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduktyZamowienieRepository extends JpaRepository<ProduktyZamowienie, Produkt> {

    @Query(nativeQuery = true, value = "SELECT * FROM produkty_zamowienie WHERE ID_ZAM = :id")
    List<ProduktyZamowienie> findProduktyZamowienieByZamowienieId(int id);
}