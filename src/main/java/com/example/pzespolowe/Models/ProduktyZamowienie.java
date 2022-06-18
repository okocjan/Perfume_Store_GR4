package com.example.pzespolowe.Models;


import javax.persistence.*;

@Entity
@Table(name = "produkty_zamowienie")
public class ProduktyZamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ZAM")
    private Zamowienie zamowienie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PR", nullable = false)
    private Produkt idPr;

    public ProduktyZamowienie() {
    }

    public ProduktyZamowienie(Zamowienie zamowienie, Produkt idPr) {
        this.zamowienie = zamowienie;
        this.idPr = idPr;
    }

    public Integer getId() {
        return id;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public Produkt getIdPr() {
        return idPr;
    }

    @Override
    public String toString() {
        return "ProduktyZamowienie{" +
                "id=" + id +
                ", idPr=" + idPr.getId() +
                '}';
    }
}