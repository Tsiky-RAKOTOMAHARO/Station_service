package com.station.service;

import com.station.dao.AchatDao;
import com.station.dao.EntretienDao;
import com.station.dao.ProduitDao;
import com.station.model.Achat;
import com.station.model.Produit;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class VenteService {

    private final AchatDao achatDao;
    private final EntretienDao entretienDao;
    private final ProduitDao produitDao;

    public VenteService() {
        this.achatDao = new AchatDao();
        this.entretienDao = new EntretienDao();
        this.produitDao = new ProduitDao();
    }

    public VenteService(AchatDao achatDao, EntretienDao entretienDao, ProduitDao produitDao) {
        this.achatDao = achatDao;
        this.entretienDao = entretienDao;
        this.produitDao = produitDao;
    }

    public Achat enregistrerAchat(String numAchat, String numProd, String numClient, int nbrLitre) throws SQLException {
        Produit produit = produitDao.findById(numProd);
        if (produit == null) {
            throw new IllegalArgumentException("Produit introuvable : " + numProd);
        }
        if (produit.getStockActuel() < nbrLitre) {
            throw new IllegalStateException(
                    "Stock insuffisant pour " + produit.getDesignation() +
                            " (disponible : " + produit.getStockActuel() + " L, demandé : " + nbrLitre + " L)");
        }

        int montantTotal = nbrLitre * produit.getPrixUnitaireActuel();

        Achat achat = new Achat(numAchat, numProd, numClient, nbrLitre, montantTotal, LocalDate.now());
        achatDao.create(achat);

        produitDao.updateStock(numProd, produit.getStockActuel() - nbrLitre);

        return achat;
    }

    public List<Achat> getAchatsByClient(String numClient) throws SQLException {
        return achatDao.findByClient(numClient);
    }

    public int getRecetteTotale() throws SQLException {
        return achatDao.getRecetteTotale() + entretienDao.getRecetteTotale();
    }

    public Map<YearMonth, Integer> getRecettesParMois() throws SQLException {
        Map<YearMonth, Integer> recettes = new TreeMap<>();

        achatDao.getRecettesParMois().forEach((mois, montant) ->
                recettes.merge(mois, montant, Integer::sum));

        entretienDao.getRecettesParMois().forEach((mois, montant) ->
                recettes.merge(mois, montant, Integer::sum));

        return recettes;
    }
}