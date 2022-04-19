package com.example.pzespolowe.Models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "newsletter", indexes = {
        @Index(name = "ID_KL_FK", columnList = "ID_KL")
})
public class Newsletter {
    @Id
    @Column(name = "ID_KL")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID_KL")
    private Klient klient;

    @Column(name = "DATA_ZAP", nullable = false)
    private LocalDate dataZap;

    @Column(name = "`OPT-IN`", nullable = false)
    private Integer optIn;

    public Newsletter() {
    }

    public Integer getOptIn() {
        return optIn;
    }

    public void setOptIn(Integer optIn) {
        this.optIn = optIn;
    }

    public LocalDate getDataZap() {
        return dataZap;
    }

    public void setDataZap(LocalDate dataZap) {
        this.dataZap = dataZap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient klient) {
        this.klient = klient;
    }
}