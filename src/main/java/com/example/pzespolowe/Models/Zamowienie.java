package com.example.pzespolowe.Models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "zamowienie")
public class Zamowienie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "DATA_ZAM", nullable = false)
    private LocalDate dataZam;

    @Column(name = "ADRES_ZAMIESZ", nullable = false, length = 128)
    private String adresZamiesz;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_FAK", referencedColumnName = "id")
    private Faktury faktury;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KL", nullable = false)
    private Klient idKl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_RABAT", nullable = false)
    private KodyRabatowe idRabat;

    @ManyToMany(mappedBy = "zamowienies")
    private Set<Produkt> produkts = new LinkedHashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "zamowienie")
    @PrimaryKeyJoinColumn
    private Wysylki wysylki;

    public Set<Produkt> getProdukts() {
        return produkts;
    }

    public void setProdukts(Set<Produkt> produkts) {
        this.produkts = produkts;
    }

    public KodyRabatowe getIdRabat() {
        return idRabat;
    }

    public void setIdRabat(KodyRabatowe idRabat) {
        this.idRabat = idRabat;
    }

    public Klient getIdKl() {
        return idKl;
    }

    public void setIdKl(Klient idKl) {
        this.idKl = idKl;
    }

    public String getAdresZamiesz() {
        return adresZamiesz;
    }

    public void setAdresZamiesz(String adresZamiesz) {
        this.adresZamiesz = adresZamiesz;
    }

    public LocalDate getDataZam() {
        return dataZam;
    }

    public void setDataZam(LocalDate dataZam) {
        this.dataZam = dataZam;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}