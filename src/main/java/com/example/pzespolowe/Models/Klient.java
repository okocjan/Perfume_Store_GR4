package com.example.pzespolowe.Models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "klient")
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "IMIE", nullable = false, length = 50)
    private String imie;

    @Column(name = "NAZWISKO", nullable = false, length = 50)
    private String nazwisko;

    @Column(name = "ADRES", nullable = false, length = 256)
    private String adres;

    @Column(name = "EMAIL", nullable = false, length = 128)
    private String email;

    @OneToMany(mappedBy = "idKl")
    private Set<Zamowienie> zamowienies = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "klient")
    @PrimaryKeyJoinColumn
    private Newsletter newsletter;

    @OneToMany(mappedBy = "idKl")
    private Set<Opinie> opinies = new LinkedHashSet<>();

    @OneToMany(mappedBy = "idKl")
    private Set<Faktury> fakturies = new LinkedHashSet<>();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}