package main.java.com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Entree;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntreeDao {

    public void create(Entree entree) throws SQLException {
        String sql = "INSERT INTO entree (numentree, numprod, quantiteajoutee, dateentree) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entree.getNumEntree());
            stmt.setString(2, entree.getNumProd());
            stmt.setInt(3, entree.getQuantiteAjoutee());
            stmt.setDate(4, Date.valueOf(entree.getDateEntree()));
            stmt.executeUpdate();
        }
    }

    public Entree findById(String numEntree) throws SQLException {
        String sql = "SELECT * FROM entree WHERE numentree = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numEntree);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Entree> findAll() throws SQLException {
        List<Entree> entrees = new ArrayList<>();
        String sql = "SELECT * FROM entree ORDER BY dateentree DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entrees.add(mapRow(rs));
            }
        }
        return entrees;
    }

    public List<Entree> findByProduit(String numProd) throws SQLException {
        List<Entree> entrees = new ArrayList<>();
        String sql = "SELECT * FROM entree WHERE numprod = ? ORDER BY dateentree DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numProd);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entrees.add(mapRow(rs));
                }
            }
        }
        return entrees;
    }

    public void update(Entree entree) throws SQLException {
        String sql = "UPDATE entree SET numprod = ?, quantiteajoutee = ?, dateentree = ? WHERE numentree = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entree.getNumProd());
            stmt.setInt(2, entree.getQuantiteAjoutee());
            stmt.setDate(3, Date.valueOf(entree.getDateEntree()));
            stmt.setString(4, entree.getNumEntree());
            stmt.executeUpdate();
        }
    }

    public void delete(String numEntree) throws SQLException {
        String sql = "DELETE FROM entree WHERE numentree = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numEntree);
            stmt.executeUpdate();
        }
    }

    private Entree mapRow(ResultSet rs) throws SQLException {
        Entree entree = new Entree();
        entree.setNumEntree(rs.getString("numentree"));
        entree.setNumProd(rs.getString("numprod"));
        entree.setQuantiteAjoutee(rs.getInt("quantiteajoutee"));
        entree.setDateEntree(rs.getDate("dateentree").toLocalDate());
        return entree;
    }
}