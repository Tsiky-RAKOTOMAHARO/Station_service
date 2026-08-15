package com.station.model;

import java.util.Objects;

public class Service {

    private String numServ;
    private String designation;
    private int prixActuel;

    public Service() {
    }

    public Service(String numServ, String designation, int prixActuel) {
        this.numServ = numServ;
        this.designation = designation;
        this.prixActuel = prixActuel;
    }

    public String getNumServ() {
        return numServ;
    }

    public void setNumServ(String numServ) {
        this.numServ = numServ;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getPrixActuel() {
        return prixActuel;
    }

    public void setPrixActuel(int prixActuel) {
        this.prixActuel = prixActuel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Service)) return false;
        Service service = (Service) o;
        return Objects.equals(numServ, service.numServ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numServ);
    }

    @Override
    public String toString() {
        return "Service{" +
                "numServ='" + numServ + '\'' +
                ", designation='" + designation + '\'' +
                ", prixActuel=" + prixActuel +
                '}';
    }
};