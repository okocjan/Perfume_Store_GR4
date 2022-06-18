package com.example.pzespolowe.Repositories;

import com.example.pzespolowe.Models.Koszyk;
import com.example.pzespolowe.Models.Projection.BasketProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface KoszykRepository extends JpaRepository<Koszyk, Integer> {

    @Query(nativeQuery = true, value = "SELECT k.id as id, p.ID as prid, p.nazwa_prod as name, p.cena as price, " +
            "zp.src as src FROM " +
            "zdjecia_prod as zp " +
            "join produkt as p " +
            "on p.ID = zp.ID_PR " +
            "join koszyk as k " +
            "on k.ID_PR = p.ID ")
    List<BasketProjection> getBasket();
}
