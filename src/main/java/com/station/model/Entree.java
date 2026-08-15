package com.station.model;

import java.time.LocalDate;
import java.util.Objects;

public class Entree {

    private String numEntree;
    private String numProd;
    private int quantiteAjoutee;
    private LocalDate dateEntree;

    public Entree() {
    }

    public Entree(String numEntree, String numProd, int quantiteAjoutee, LocalDate dateEntree) {
        this.numEntree = numEntree;
        this.numProd = numProd;
        this.quantiteAjoutee = quantiteAjoutee;
        this.dateEntree = dateEntree;
    }

    public String getNumEntree() {
        return numEntree;
    }

    public void setNumEntree(String numEntree) {
        this.numEntree = numEntree;
    }

    public String getNumProd() {
        return numProd;
    }

    public void setNumProd(String numProd) {
        this.numProd = numProd;
    }

    public int getQuantiteAjoutee() {
        return quantiteAjoutee;
    }

    public void setQuantiteAjoutee(int quantiteAjoutee) {
        this.quantiteAjoutee = quantiteAjoutee;
    }

    public LocalDate getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(LocalDate dateEntree) {
        this.dateEntree = dateEntree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entree)) return false;
        Entree entree = (Entree) o;
        return Objects.equals(numEntree, entree.numEntree);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numEntree);
    }

    @Override
    public String toString() {
        return "Entree{" +
                "numEntree='" + numEntree + '\'' +
                ", numProd='" + numProd + '\'' +
                ", quantiteAjoutee=" + quantiteAjoutee +
                ", dateEntree=" + dateEntree +
                '}';
    }
};