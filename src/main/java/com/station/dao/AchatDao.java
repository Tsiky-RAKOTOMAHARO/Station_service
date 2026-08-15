package main.java.com.station.dao;

import com.station.database.DatabaseConnection;
import com.station.model.Achat;

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

public class AchatDao {

    public void create(Achat achat) throws SQLException {
        String sql = "INSERT INTO achat (numachat, numprod, numclient, nbrlitre, montanttotal, dateachat) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, achat.getNumAchat());
            stmt.setString(2, achat.getNumProd());
            stmt.setString(3, achat.getNumClient());
            stmt.setInt(4, achat.getNbrLitre());
            stmt.setInt(5, achat.getMontantTotal());
            stmt.setDate(6, Date.valueOf(achat.getDateAchat()));
            stmt.executeUpdate();
        }
    }

    public Achat findById(String numAchat) throws SQLException {
        String sql = "SELECT * FROM achat WHERE numachat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numAchat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    public List<Achat> findAll() throws SQLException {
        List<Achat> achats = new ArrayList<>();
        String sql = "SELECT * FROM achat ORDER BY dateachat DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                achats.add(mapRow(rs));
            }
        }
        return achats;
    }

    public List<Achat> findByClient(String numClient) throws SQLException {
        List<Achat> achats = new ArrayList<>();
        String sql = "SELECT * FROM achat WHERE numclient = ? ORDER BY dateachat DESC";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numClient);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    achats.add(mapRow(rs));
                }
            }
        }
        return achats;
    }

    public int getRecetteTotale() throws SQLException {
        String sql = "SELECT COALESCE(SUM(montanttotal), 0) AS total FROM achat";
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
        String sql = "SELECT DATE_TRUNC('month', dateachat) AS mois, SUM(montanttotal) AS total " +
                "FROM achat GROUP BY DATE_TRUNC('month', dateachat) ORDER BY mois";
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

    public void update(Achat achat) throws SQLException {
        String sql = "UPDATE achat SET numprod = ?, numclient = ?, nbrlitre = ?, montanttotal = ?, dateachat = ? WHERE numachat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, achat.getNumProd());
            stmt.setString(2, achat.getNumClient());
            stmt.setInt(3, achat.getNbrLitre());
            stmt.setInt(4, achat.getMontantTotal());
            stmt.setDate(5, Date.valueOf(achat.getDateAchat()));
            stmt.setString(6, achat.getNumAchat());
            stmt.executeUpdate();
        }
    }

    public void delete(String numAchat) throws SQLException {
        String sql = "DELETE FROM achat WHERE numachat = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, numAchat);
            stmt.executeUpdate();
        }
    }

    private Achat mapRow(ResultSet rs) throws SQLException {
        Achat achat = new Achat();
        achat.setNumAchat(rs.getString("numachat"));
        achat.setNumProd(rs.getString("numprod"));
        achat.setNumClient(rs.getString("numclient"));
        achat.setNbrLitre(rs.getInt("nbrlitre"));
        achat.setMontantTotal(rs.getInt("montanttotal"));
        achat.setDateAchat(rs.getDate("dateachat").toLocalDate());
        return achat;
    }
}