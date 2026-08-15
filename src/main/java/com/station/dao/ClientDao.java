package com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    public void create(Client client) throws SQLException {
        String sql = "INSERT INTO client (numclient, nomclient, telephone) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNumClient());
            stmt.setString(2, client.getNomClient());
            stmt.setString(3, client.getTelephone());
            stmt.executeUpdate();
        }
    }

    public Client findById(String numClient) throws SQLException {
        String sql = "SELECT * FROM client WHERE numclient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numClient);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Client> findAll() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client ORDER BY nomclient";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clients.add(mapRow(rs));
            }
        }
        return clients;
    }

    public List<Client> rechercherParNom(String motCle) throws SQLException {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client WHERE nomclient ILIKE ? ORDER BY nomclient";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + motCle + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clients.add(mapRow(rs));
                }
            }
        }
        return clients;
    }

    public void update(Client client) throws SQLException {
        String sql = "UPDATE client SET nomclient = ?, telephone = ? WHERE numclient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.getNomClient());
            stmt.setString(2, client.getTelephone());
            stmt.setString(3, client.getNumClient());
            stmt.executeUpdate();
        }
    }

    public void delete(String numClient) throws SQLException {
        String sql = "DELETE FROM client WHERE numclient = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numClient);
            stmt.executeUpdate();
        }
    }

    private Client mapRow(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setNumClient(rs.getString("numclient"));
        client.setNomClient(rs.getString("nomclient"));
        client.setTelephone(rs.getString("telephone"));
        return client;
    }
}