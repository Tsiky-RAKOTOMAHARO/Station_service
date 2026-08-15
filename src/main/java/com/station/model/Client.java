package com.station.model;

import java.util.Objects;

public class Client {

    private String numClient;
    private String nomClient;
    private String telephone;

    public Client() {
    }

    public Client(String numClient, String nomClient, String telephone) {
        this.numClient = numClient;
        this.nomClient = nomClient;
        this.telephone = telephone;
    }

    public String getNumClient() {
        return numClient;
    }

    public void setNumClient(String numClient) {
        this.numClient = numClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(numClient, client.numClient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numClient);
    }

    @Override
    public String toString() {
        return "Client{" +
                "numClient='" + numClient + '\'' +
                ", nomClient='" + nomClient + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
};