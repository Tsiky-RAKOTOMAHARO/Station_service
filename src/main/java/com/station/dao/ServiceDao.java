package main.java.com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceDao {

    public void create(Service service) throws SQLException {
        String sql = "INSERT INTO service (numserv, designation, prixactuel) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, service.getNumServ());
            stmt.setString(2, service.getDesignation());
            stmt.setInt(3, service.getPrixActuel());
            stmt.executeUpdate();
        }
    }

    public Service findById(String numServ) throws SQLException {
        String sql = "SELECT * FROM service WHERE numserv = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numServ);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Service> findAll() throws SQLException {
        List<Service> services = new ArrayList<>();
        String sql = "SELECT * FROM service ORDER BY designation";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                services.add(mapRow(rs));
            }
        }
        return services;
    }

    public void update(Service service) throws SQLException {
        String sql = "UPDATE service SET designation = ?, prixactuel = ? WHERE numserv = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, service.getDesignation());
            stmt.setInt(2, service.getPrixActuel());
            stmt.setString(3, service.getNumServ());
            stmt.executeUpdate();
        }
    }

    public void delete(String numServ) throws SQLException {
        String sql = "DELETE FROM service WHERE numserv = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numServ);
            stmt.executeUpdate();
        }
    }

    private Service mapRow(ResultSet rs) throws SQLException {
        Service service = new Service();
        service.setNumServ(rs.getString("numserv"));
        service.setDesignation(rs.getString("designation"));
        service.setPrixActuel(rs.getInt("prixactuel"));
        return service;
    }
}