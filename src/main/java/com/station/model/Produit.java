package com.station.model;

import java.util.Objects;

public class Produit {

    private String numProd;
    private String designation;
    private int prixUnitaireActuel;
    private int stockActuel;

    public Produit() {
    }

    public Produit(String numProd, String designation, int prixUnitaireActuel, int stockActuel) {
        this.numProd = numProd;
        this.designation = designation;
        this.prixUnitaireActuel = prixUnitaireActuel;
        this.stockActuel = stockActuel;
    }

    public String getNumProd() {
        return numProd;
    }

    public void setNumProd(String numProd) {
        this.numProd = numProd;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrixUnitaireActuel() {
        return prixUnitaireActuel;
    }

    public void setPrixUnitaireActuel(int prixUnitaireActuel) {
        this.prixUnitaireActuel = prixUnitaireActuel;
    }

    public int getStockActuel() {
        return stockActuel;
    }

    public void setStockActuel(int stockActuel) {
        this.stockActuel = stockActuel;
    }

    public boolean isStockBas() {
        return stockActuel < 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produit)) return false;
        Produit produit = (Produit) o;
        return Objects.equals(numProd, produit.numProd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numProd);
    }

    @Override
    public String toString() {
        return "Produit{" +
                "numProd='" + numProd + '\'' +
                ", designation='" + designation + '\'' +
                ", prixUnitaireActuel=" + prixUnitaireActuel +
                ", stockActuel=" + stockActuel +
                '}';
    }
};