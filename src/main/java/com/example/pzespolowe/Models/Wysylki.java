package com.example.pzespolowe.Models;

import javax.persistence.*;

@Entity
@Table(name = "wysylki", indexes = {
        @Index(name = "ID_ZAM_FK", columnList = "ID_ZAM")
})
public class Wysylki {
    @Id
    @Column(name = "ID_ZAM")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID_ZAM")
    private Zamowienie zamowienie;

    @Column(name = "KURIER", nullable = false, length = 40)
    private String kurier;

    @Column(name = "CZY_POBR", nullable = false)
    private Integer czyPobr;

    @Column(name = "TRACK_NR", nullable = false, length = 128)
    private String trackNr;

    @Column(name = "CENA", nullable = false)
    private Double cena;

    public Wysylki() {
    }

    public Wysylki(Zamowienie zamowienie, String kurier, Integer czyPobr, String trackNr, Double cena) {
        this.zamowienie = zamowienie;
        this.kurier = kurier;
        this.czyPobr = czyPobr;
        this.trackNr = trackNr;
        this.cena = cena;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public String getTrackNr() {
        return trackNr;
    }

    public void setTrackNr(String trackNr) {
        this.trackNr = trackNr;
    }

    public Integer getCzyPobr() {
        return czyPobr;
    }

    public void setCzyPobr(Integer czyPobr) {
        this.czyPobr = czyPobr;
    }

    public String getKurier() {
        return kurier;
    }

    public void setKurier(String kurier) {
        this.kurier = kurier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }

    @Override
    public String toString() {
        return "\nWysylki{" +
                ", kurier='" + kurier + '\'' +
                ", czyPobr=" + czyPobr +
                ", trackNr='" + trackNr + '\'' +
                ", cena=" + cena +
                '}';
    }
}