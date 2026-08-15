package com.station.service;

import com.station.dao.EntretienDao;
import com.station.dao.ServiceDao;
import com.station.model.Entretien;
import com.station.model.Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EntretienService {

    private final EntretienDao entretienDao;
    private final ServiceDao serviceDao;

    public EntretienService() {
        this.entretienDao = new EntretienDao();
        this.serviceDao = new ServiceDao();
    }

    public EntretienService(EntretienDao entretienDao, ServiceDao serviceDao) {
        this.entretienDao = entretienDao;
        this.serviceDao = serviceDao;
    }

    public List<Entretien> getAllEntretiens() throws SQLException {
        return entretienDao.findAll();
    }

    public List<Entretien> getEntretiensByClient(String numClient) throws SQLException {
        return entretienDao.findByClient(numClient);
    }

    public Entretien enregistrerEntretien(String numEntr, String numServ, String numClient, String immatriculation) throws SQLException {
        Service service = serviceDao.findById(numServ);
        if (service == null) {
            throw new IllegalArgumentException("Service introuvable : " + numServ);
        }

        Entretien entretien = new Entretien(
                numEntr, numServ, numClient, immatriculation, service.getPrixActuel(), LocalDate.now());

        entretienDao.create(entretien);

        return entretien;
    }

    public void supprimerEntretien(String numEntr) throws SQLException {
        entretienDao.delete(numEntr);
    }
}