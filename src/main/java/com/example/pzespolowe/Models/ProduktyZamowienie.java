package com.example.pzespolowe.Models;


import javax.persistence.*;

@Entity
@Table(name = "produkty_zamowienie")
public class ProduktyZamowienie {
    @Id
    @Column(name = "ID_ZAM")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ZAM")
    @MapsId
    private Zamowienie zamowienie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PR", nullable = false)
    private Produkt idPr;

    public Integer getId() {
        return id;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public Produkt getIdPr() {
        return idPr;
    }
}