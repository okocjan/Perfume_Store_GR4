package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Magazyn;
import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.Projection.MagazynProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MagazynRepository extends JpaRepository<Magazyn, Produkt> {

    @Query(nativeQuery = true, value = "SELECT p.ID as id, p.nazwa_prod as 'name', m.ilosc as quantity from produkt as p " +
            "join magazyn as m " +
            "on p.ID = m.ID_PR")
    List<MagazynProjection> getMagazynQuantity();

    @Query(nativeQuery = true, value = "select m.ID_PR, m.ILOSC " +
            "from magazyn as m " +
            "where ID_PR = :id")
    Magazyn findByProduktId(Integer id);
}