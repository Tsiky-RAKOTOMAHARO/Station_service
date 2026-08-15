package com.station.service;

import com.station.dao.ServiceDao;
import com.station.model.Service;

import java.sql.SQLException;
import java.util.List;

public class PrestationService {

    private final ServiceDao serviceDao;

    public PrestationService() {
        this.serviceDao = new ServiceDao();
    }

    public PrestationService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public List<Service> getAllPrestations() throws SQLException {
        return serviceDao.findAll();
    }

    public Service getPrestation(String numServ) throws SQLException {
        return serviceDao.findById(numServ);
    }

    public void ajouterPrestation(Service service) throws SQLException {
        serviceDao.create(service);
    }

    public void modifierPrestation(Service service) throws SQLException {
        serviceDao.update(service);
    }

    public void supprimerPrestation(String numServ) throws SQLException {
        serviceDao.delete(numServ);
    }
}