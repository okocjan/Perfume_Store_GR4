package com.example.pzespolowe.Models;

import javax.persistence.*;

@Entity
@Table(name = "opinie", indexes = {
        @Index(name = "ID_KL_FK", columnList = "ID_KL"),
        @Index(name = "ID_PR_FK", columnList = "ID_PR")
})
public class Opinie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PR", nullable = false)
    private Produkt idPr;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KL", nullable = false)
    private Klient idKl;

    @Column(name = "TRESC", nullable = false, length = 1024)
    private String tresc;

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}