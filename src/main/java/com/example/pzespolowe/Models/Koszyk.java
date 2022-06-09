package com.example.pzespolowe.Models;

import javax.persistence.*;

@Entity
@Table(name = "koszyk")
public class Koszyk {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KL", nullable = false)
    private Klient idKl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PR", nullable = false)
    private Produkt idPr;

    @Column(name = "FINAL_PRICE", nullable = false)
    private Integer finalPrice;

    public Koszyk() {
    }

    public Koszyk(Klient idKl, Produkt idPr, Integer finalPrice) {
        this.idKl = idKl;
        this.idPr = idPr;
        this.finalPrice = finalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klient getIdKl() {
        return idKl;
    }

    public void setIdKl(Klient idKl) {
        this.idKl = idKl;
    }

    public Produkt getIdPr() {
        return idPr;
    }

    public void setIdPr(Produkt idPr) {
        this.idPr = idPr;
    }

    public Integer getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(Integer finalPrice) {
        this.finalPrice = finalPrice;
    }

}