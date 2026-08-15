package com.station.model;

import java.time.LocalDate;
import java.util.Objects;

public class Achat {

    private String numAchat;
    private String numProd;
    private String numClient;
    private int nbrLitre;
    private int montantTotal;
    private LocalDate dateAchat;

    public Achat() {
    }

    public Achat(String numAchat, String numProd, String numClient, int nbrLitre, int montantTotal, LocalDate dateAchat) {
        this.numAchat = numAchat;
        this.numProd = numProd;
        this.numClient = numClient;
        this.nbrLitre = nbrLitre;
        this.montantTotal = montantTotal;
        this.dateAchat = dateAchat;
    }

    public String getNumAchat() {
        return numAchat;
    }

    public void setNumAchat(String numAchat) {
        this.numAchat = numAchat;
    }

    public String getNumProd() {
        return numProd;
    }

    public void setNumProd(String numProd) {
        this.numProd = numProd;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public int getNbrLitre() {
        return nbrLitre;
    }

    public void setNbrLitre(int nbrLitre) {
        this.nbrLitre = nbrLitre;
    }

    public int getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(int montantTotal) {
        this.montantTotal = montantTotal;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        this.dateAchat = dateAchat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Achat)) return false;
        Achat achat = (Achat) o;
        return Objects.equals(numAchat, achat.numAchat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numAchat);
    }

    @Override
    public String toString() {
        return "Achat{" +
                "numAchat='" + numAchat + '\'' +
                ", numProd='" + numProd + '\'' +
                ", numClient='" + numClient + '\'' +
                ", nbrLitre=" + nbrLitre +
                ", montantTotal=" + montantTotal +
                ", dateAchat=" + dateAchat +
                '}';
    }
};