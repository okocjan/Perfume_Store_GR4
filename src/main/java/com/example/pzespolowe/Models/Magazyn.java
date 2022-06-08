package com.example.pzespolowe.Models;

import org.hibernate.annotations.CollectionId;

import javax.persistence.*;

@Entity
@Table(name = "magazyn")
public class Magazyn {

    @Id
    @Column(name = "ID_PR")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID_PR")
    private Produkt produkt;

    @Column(name = "ILOSC", nullable = false)
    private Integer ilosc;

    public Magazyn() {
    }

    public Magazyn(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }


}