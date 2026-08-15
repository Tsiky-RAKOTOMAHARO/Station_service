package com.station.model;

import java.time.LocalDate;
import java.util.Objects;

public class Entretien {

    private String numEntr;
    private String numServ;
    private String numClient;
    private String immatriculationVoiture;
    private int montantTotal;
    private LocalDate dateEntretien;

    public Entretien() {
    }

    public Entretien(String numEntr, String numServ, String numClient, String immatriculationVoiture, int montantTotal, LocalDate dateEntretien) {
        this.numEntr = numEntr;
        this.numServ = numServ;
        this.numClient = numClient;
        this.immatriculationVoiture = immatriculationVoiture;
        this.montantTotal = montantTotal;
        this.dateEntretien = dateEntretien;
    }

    public String getNumEntr() {
        return numEntr;
    }

    public void setNumEntr(String numEntr) {
        this.numEntr = numEntr;
    }

    public String getNumServ() {
        return numServ;
    }

    public void setNumServ(String numServ) {
        this.numServ = numServ;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getImmatriculationVoiture() {
        return immatriculationVoiture;
    }

    public void setImmatriculationVoiture(String immatriculationVoiture) {
        this.immatriculationVoiture = immatriculationVoiture;
    }

    public int getMontantTotal() {
        return montantTotal;
    }

    public void setMontantTotal(int montantTotal) {
        this.montantTotal = montantTotal;
    }

    public LocalDate getDateEntretien() {
        return dateEntretien;
    }

    public void setDateEntretien(LocalDate dateEntretien) {
        this.dateEntretien = dateEntretien;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entretien)) return false;
        Entretien entretien = (Entretien) o;
        return Objects.equals(numEntr, entretien.numEntr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numEntr);
    }

    @Override
    public String toString() {
        return "Entretien{" +
                "numEntr='" + numEntr + '\'' +
                ", numServ='" + numServ + '\'' +
                ", numClient='" + numClient + '\'' +
                ", immatriculationVoiture='" + immatriculationVoiture + '\'' +
                ", montantTotal=" + montantTotal +
                ", dateEntretien=" + dateEntretien +
                '}';
    }
};