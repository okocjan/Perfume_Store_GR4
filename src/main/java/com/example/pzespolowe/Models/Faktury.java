package com.example.pzespolowe.Models;

import org.apache.tomcat.jni.Address;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "faktury", indexes = {
        @Index(name = "ID_KL_FK", columnList = "ID_KL")
})
public class Faktury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NAZWA_FIR", nullable = false, length = 128)
    private String nazwaFir;

    @Column(name = "NIP", nullable = false)
    private Integer nip;

    @Column(name = "DATA_WYST", nullable = false)
    private LocalDate dataWyst;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_KL", nullable = false)
    private Klient idKl;

    @OneToOne(mappedBy = "faktury")
    private Zamowienie zamowienie;

    public LocalDate getDataWyst() {
        return dataWyst;
    }

    public void setDataWyst(LocalDate dataWyst) {
        this.dataWyst = dataWyst;
    }

    public Integer getNip() {
        return nip;
    }

    public void setNip(Integer nip) {
        this.nip = nip;
    }

    public String getNazwaFir() {
        return nazwaFir;
    }

    public void setNazwaFir(String nazwaFir) {
        this.nazwaFir = nazwaFir;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}