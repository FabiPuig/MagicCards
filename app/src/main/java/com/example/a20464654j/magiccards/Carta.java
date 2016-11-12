package com.example.a20464654j.magiccards;

import java.io.Serializable;

/**
 * Created by Fabian on 20/10/2016.
 */

public class Carta implements Serializable{
    private String nom;
    private String tipo;
    private String color;
    private String raresa;
    private String imatgeURL;
    private String text;

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

    public String getText() { return text; }

    public void setText(String text) { this.text = text; }

    //per ara el toString() retornara nomes el nom
    @Override
    public String toString() {
        return nom;
    }
}
