package com.station.service;

import com.station.dao.AchatDao;
import com.station.dao.ClientDao;
import com.station.dao.EntretienDao;
import com.station.model.Achat;
import com.station.model.Client;
import com.station.model.Entretien;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientService {

    private final ClientDao clientDao;
    private final AchatDao achatDao;
    private final EntretienDao entretienDao;

    public ClientService() {
        this.clientDao = new ClientDao();
        this.achatDao = new AchatDao();
        this.entretienDao = new EntretienDao();
    }

    public ClientService(ClientDao clientDao, AchatDao achatDao, EntretienDao entretienDao) {
        this.clientDao = clientDao;
        this.achatDao = achatDao;
        this.entretienDao = entretienDao;
    }

    public List<Client> getAllClients() throws SQLException {
        return clientDao.findAll();
    }

    public Client getClient(String numClient) throws SQLException {
        return clientDao.findById(numClient);
    }

    public List<Client> rechercherParNom(String motCle) throws SQLException {
        if (motCle == null || motCle.isBlank()) {
            return clientDao.findAll();
        }
        return clientDao.rechercherParNom(motCle.trim());
    }

    public void ajouterClient(Client client) throws SQLException {
        clientDao.create(client);
    }

    public void modifierClient(Client client) throws SQLException {
        clientDao.update(client);
    }

    public void supprimerClient(String numClient) throws SQLException {
        clientDao.delete(numClient);
    }

    public List<Map.Entry<Client, Integer>> getTop5Clients() throws SQLException {
        Map<String, Integer> totalParClient = new HashMap<>();

        for (Achat achat : achatDao.findAll()) {
            totalParClient.merge(achat.getNumClient(), achat.getMontantTotal(), Integer::sum);
        }
        for (Entretien entretien : entretienDao.findAll()) {
            totalParClient.merge(entretien.getNumClient(), entretien.getMontantTotal(), Integer::sum);
        }

        List<Map.Entry<Client, Integer>> classement = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : totalParClient.entrySet()) {
            Client client = clientDao.findById(entry.getKey());
            if (client != null) {
                classement.add(Map.entry(client, entry.getValue()));
            }
        }

        classement.sort(Comparator.comparingInt((Map.Entry<Client, Integer> e) -> e.getValue()).reversed());

        return classement.size() > 5 ? classement.subList(0, 5) : classement;
    }
}