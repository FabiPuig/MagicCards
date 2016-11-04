package com.example.a20464654j.magiccards;

/**
 * Created by Fabian on 20/10/2016.
 */

public class Carta {
    private String nom;
    private String tipo;
    private String color;
    private String raresa;
    private String imatgeURL;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColor() { return color; }

    public void setColor(String color) { this.color = color; }

    public String getRaresa() {
        return raresa;
    }

    public void setRaresa(String raresa) {
        this.raresa = raresa;
    }

    public String getImatgeURL() {
        return imatgeURL;
    }

    public void setImatgeURL(String imatgeURL) {
        this.imatgeURL = imatgeURL;
    }

    //per ara el toString() retornara nomes el nom
    @Override
    public String toString() {
        return nom;
    }
}
