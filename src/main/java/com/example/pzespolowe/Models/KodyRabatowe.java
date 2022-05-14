package com.example.pzespolowe.Models;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "kody_rabatowe")
public class KodyRabatowe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "KOD", nullable = false, length = 20)
    private String kod;

    @Column(name = "RABAT", nullable = false)
    private Integer rabat;

    @OneToMany(mappedBy = "idRabat")
    private Set<Zamowienie> zamowienies = new LinkedHashSet<>();

    public Set<Zamowienie> getZamowienies() {
        return zamowienies;
    }

    public void setZamowienies(Set<Zamowienie> zamowienies) {
        this.zamowienies = zamowienies;
    }

    public Integer getRabat() {
        return rabat;
    }

    public void setRabat(Integer rabat) {
        this.rabat = rabat;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}