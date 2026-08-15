package main.java.com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Entretien;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EntretienDao {

    public void create(Entretien entretien) throws SQLException {
        String sql = "INSERT INTO entretien (numentr, numserv, numclient, immatriculation_voiture, montanttotal, dateentretien) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entretien.getNumEntr());
            stmt.setString(2, entretien.getNumServ());
            stmt.setString(3, entretien.getNumClient());
            stmt.setString(4, entretien.getImmatriculationVoiture());
            stmt.setInt(5, entretien.getMontantTotal());
            stmt.setDate(6, Date.valueOf(entretien.getDateEntretien()));
            stmt.executeUpdate();
        }
    }

    public Entretien findById(String numEntr) throws SQLException {
        String sql = "SELECT * FROM entretien WHERE numentr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numEntr);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Entretien> findAll() throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String sql = "SELECT * FROM entretien ORDER BY dateentretien DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                entretiens.add(mapRow(rs));
            }
        }
        return entretiens;
    }

    public List<Entretien> findByClient(String numClient) throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String sql = "SELECT * FROM entretien WHERE numclient = ? ORDER BY dateentretien DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numClient);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entretiens.add(mapRow(rs));
                }
            }
        }
        return entretiens;
    }

    public int getRecetteTotale() throws SQLException {
        String sql = "SELECT COALESCE(SUM(montanttotal), 0) AS total FROM entretien";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("total");
            }
        }
        return 0;
    }

    public Map<YearMonth, Integer> getRecettesParMois() throws SQLException {
        Map<YearMonth, Integer> recettes = new LinkedHashMap<>();
        String sql = "SELECT DATE_TRUNC('month', dateentretien) AS mois, SUM(montanttotal) AS total " +
                "FROM entretien GROUP BY DATE_TRUNC('month', dateentretien) ORDER BY mois";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                YearMonth mois = YearMonth.from(rs.getDate("mois").toLocalDate());
                recettes.put(mois, rs.getInt("total"));
            }
        }
        return recettes;
    }

    public void update(Entretien entretien) throws SQLException {
        String sql = "UPDATE entretien SET numserv = ?, numclient = ?, immatriculation_voiture = ?, montanttotal = ?, dateentretien = ? WHERE numentr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entretien.getNumServ());
            stmt.setString(2, entretien.getNumClient());
            stmt.setString(3, entretien.getImmatriculationVoiture());
            stmt.setInt(4, entretien.getMontantTotal());
            stmt.setDate(5, Date.valueOf(entretien.getDateEntretien()));
            stmt.setString(6, entretien.getNumEntr());
            stmt.executeUpdate();
        }
    }

    public void delete(String numEntr) throws SQLException {
        String sql = "DELETE FROM entretien WHERE numentr = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numEntr);
            stmt.executeUpdate();
        }
    }

    private Entretien mapRow(ResultSet rs) throws SQLException {
        Entretien entretien = new Entretien();
        entretien.setNumEntr(rs.getString("numentr"));
        entretien.setNumServ(rs.getString("numserv"));
        entretien.setNumClient(rs.getString("numclient"));
        entretien.setImmatriculationVoiture(rs.getString("immatriculation_voiture"));
        entretien.setMontantTotal(rs.getInt("montanttotal"));
        entretien.setDateEntretien(rs.getDate("dateentretien").toLocalDate());
        return entretien;
    }
}