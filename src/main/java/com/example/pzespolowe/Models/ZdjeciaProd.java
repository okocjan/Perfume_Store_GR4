package com.example.pzespolowe.Models;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "zdjecia_prod", indexes = {
        @Index(name = "ID_PR_FK", columnList = "ID_PR")
})
public class ZdjeciaProd{
    @Id
    @Column(name = "ID_PR")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "ID_PR")
    private Produkt produkt;

    @Column(name = "SRC", nullable = false, length = 512)
    private String src;

    public ZdjeciaProd() {
    }

    public ZdjeciaProd(Integer id, String src) {
        this.id = id;
        this.src = src;
    }

    public ZdjeciaProd(Produkt produkt, String src) {
        this.produkt = produkt;
        this.src = src;
    }

    public ZdjeciaProd(String src) {
        this.src = src;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}