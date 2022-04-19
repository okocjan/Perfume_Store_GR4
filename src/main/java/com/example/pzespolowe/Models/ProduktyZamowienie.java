package com.example.pzespolowe.Models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "produkty_zamowienie", indexes = {
        @Index(name = "ID_ZAM_FK", columnList = "ID_ZAM"),
        @Index(name = "ID_PR_FK", columnList = "ID_PR")
})
public class ProduktyZamowienie {
    @Id
    @Column(name = "ID_ZAM")
    private Integer id;


    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID_ZAM")
    private Zamowienie zamowienie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PR", nullable = false)
    private Produkt idPr;

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

    public Zamowienie getZamowienie() {
        return zamowienie;
    }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
}