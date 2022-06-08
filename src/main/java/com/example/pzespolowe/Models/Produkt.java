package com.example.pzespolowe.Models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "produkt")
public class Produkt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAZWA_PROD", nullable = false, length = 512)
    private String nazwaProd;

    @Column(name = "POJEMNOSC", nullable = false)
    private Integer pojemnosc;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    @Column(name = "RODZAJ", nullable = false, length = 1)
    private String rodzaj;

    @OneToMany(mappedBy = "idPr")
    private Set<Opinie> opinies = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "produkt")
    @PrimaryKeyJoinColumn
    private ZdjeciaProd zdjeciaProd;

    @ManyToMany
    @JoinTable(name = "produkty_zamowienie",
            joinColumns = @JoinColumn(name = "ID_PR"),
            inverseJoinColumns = @JoinColumn(name = "ID_ZAM"))
    private Set<Zamowienie> zamowienies = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "produkt")
    @PrimaryKeyJoinColumn
    private Magazyn magazyn;

    public Produkt() {
    }

    public Produkt(String nazwaProd, Integer pojemnosc, Double cena, String rodzaj, Magazyn magazyn) {
        this.nazwaProd = nazwaProd;
        this.pojemnosc = pojemnosc;
        this.cena = cena;
        this.rodzaj = rodzaj;
        this.magazyn = magazyn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNazwaProd() {
        return nazwaProd;
    }

    public void setNazwaProd(String nazwaProd) {
        this.nazwaProd = nazwaProd;
    }

    public Integer getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(Integer pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public ZdjeciaProd getZdjeciaProd() {
        return zdjeciaProd;
    }

    public void setZdjeciaProd(ZdjeciaProd zdjeciaProd) {
        this.zdjeciaProd = zdjeciaProd;
    }

    @Override
    public String toString() {
        return "\nId: " + id +
                "\nNazwa produktu: " + nazwaProd;
    }
}