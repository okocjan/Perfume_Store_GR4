package com.example.pzespolowe.Models.Projection;

public class ProdAndZdjModel {
    private int id;
    private String nazwa;
    private String rodzaj;
    private double cena;
    private int pojemnosc;
    private String imagePath;

    private String opis;

    public ProdAndZdjModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getPojemnosc() {
        return pojemnosc;
    }

    public void setPojemnosc(int pojemnosc) {
        this.pojemnosc = pojemnosc;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "\nid = " + id +
                ", nazwa = " + nazwa +
                ", rodzaj = " + rodzaj +
                ", cena = " + cena +
                ", pojemnosc = " + pojemnosc +
                ", imagePath = " + imagePath +
                ", opis = " + opis + "\n";
    }
}
