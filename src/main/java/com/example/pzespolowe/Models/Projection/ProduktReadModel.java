package com.example.pzespolowe.Models.Projection;

// TODO: implement projections for Produkt, Zamowienie, Opinie

import com.example.pzespolowe.Models.Produkt;
import com.example.pzespolowe.Models.ProduktyZamowienie;
import com.example.pzespolowe.Models.ZdjeciaProd;

import javax.persistence.criteria.CriteriaBuilder;

public class ProduktReadModel {
    private String nazwa;
    private Integer pojemnosc;
    private Double cena;
    private String rodzaj;
    private String zdjecie;

    public ProduktReadModel(Produkt produkt, ZdjeciaProd zdjecie) {
        this.nazwa = produkt.getNazwaProd();
        this.pojemnosc = produkt.getPojemnosc();
        this.cena = produkt.getCena();
        this.rodzaj = produkt.getRodzaj();
        this.zdjecie = produkt.getZdjeciaProd().getSrc();
    }

    public String getNazwa() {
        return nazwa;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public Double getCena() {
        return cena;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public String getZdjecie() {
        return zdjecie;
    }
}
