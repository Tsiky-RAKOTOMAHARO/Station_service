package com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitDao {

    public void create(Produit produit) throws SQLException {
        String sql = "INSERT INTO produit (numprod, designation, prixunitaireactuel, stockactuel) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produit.getNumProd());
            stmt.setString(2, produit.getDesignation());
            stmt.setInt(3, produit.getPrixUnitaireActuel());
            stmt.setInt(4, produit.getStockActuel());
            stmt.executeUpdate();
        }
    }

    public Produit findById(String numProd) throws SQLException {
        String sql = "SELECT * FROM produit WHERE numprod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numProd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Produit> findAll() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit ORDER BY designation";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produits.add(mapRow(rs));
            }
        }
        return produits;
    }

    public List<Produit> findProduitsStockBas() throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String sql = "SELECT * FROM produit WHERE stockactuel < 10 ORDER BY stockactuel";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                produits.add(mapRow(rs));
            }
        }
        return produits;
    }

    public void update(Produit produit) throws SQLException {
        String sql = "UPDATE produit SET designation = ?, prixunitaireactuel = ?, stockactuel = ? WHERE numprod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produit.getDesignation());
            stmt.setInt(2, produit.getPrixUnitaireActuel());
            stmt.setInt(3, produit.getStockActuel());
            stmt.setString(4, produit.getNumProd());
            stmt.executeUpdate();
        }
    }

    public void updateStock(String numProd, int nouveauStock) throws SQLException {
        String sql = "UPDATE produit SET stockactuel = ? WHERE numprod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, nouveauStock);
            stmt.setString(2, numProd);
            stmt.executeUpdate();
        }
    }

    public void delete(String numProd) throws SQLException {
        String sql = "DELETE FROM produit WHERE numprod = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numProd);
            stmt.executeUpdate();
        }
    }

    private Produit mapRow(ResultSet rs) throws SQLException {
        Produit produit = new Produit();
        produit.setNumProd(rs.getString("numprod"));
        produit.setDesignation(rs.getString("designation"));
        produit.setPrixUnitaireActuel(rs.getInt("prixunitaireactuel"));
        produit.setStockActuel(rs.getInt("stockactuel"));
        return produit;
    }
}