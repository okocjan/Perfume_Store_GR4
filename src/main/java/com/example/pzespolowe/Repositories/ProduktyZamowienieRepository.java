package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.Projection.ProdZamProjection;
import com.example.pzespolowe.Models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProduktyZamowienieRepository extends JpaRepository<ProduktyZamowienie, Produkt> {

    @Query(nativeQuery = true, value = "SELECT * FROM produkty_zamowienie WHERE ID_ZAM = :id")
    List<ProduktyZamowienie> findProduktyZamowienieByZamowienieId(int id);

    @Query(nativeQuery = true, value = "SELECT p.ID_PR, p.ID_ZAM FROM produkty_zamowienie as p " +
            "JOIN zamowienie as z ON p.ID_ZAM = z.ID WHERE z.STATUS = 'IN_PROGRESS'")
    List<ProduktyZamowienie> findProduktyZamowienieByStatus();

    @Query(nativeQuery = true, value = "SELECT pz.ID_PR as id, p.NAZWA_PROD as name, COUNT(pz.ID_PR) as quantity from produkt as p " +
            "join produkty_zamowienie as pz on p.id = pz.ID_PR " +
            "join zamowienie as z on z.ID = pz.ID_ZAM " +
            "where z.STATUS = 'PREPARING' " +
            "group by pz.ID_PR, p.NAZWA_PROD " +
            "order by id")
    List<ProdZamProjection> testQ();
}