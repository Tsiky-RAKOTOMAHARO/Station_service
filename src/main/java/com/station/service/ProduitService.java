package com.station.service;

import com.station.dao.ProduitDao;
import com.station.model.Produit;

import java.sql.SQLException;
import java.util.List;

public class ProduitService {

    private static final int SEUIL_ALERTE_STOCK = 10;

    private final ProduitDao produitDao;

    public ProduitService() {
        this.produitDao = new ProduitDao();
    }

    public ProduitService(ProduitDao produitDao) {
        this.produitDao = produitDao;
    }

    public List<Produit> getAllProduits() throws SQLException {
        return produitDao.findAll();
    }

    public Produit getProduit(String numProd) throws SQLException {
        return produitDao.findById(numProd);
    }

    public List<Produit> getProduitsEnAlerte() throws SQLException {
        return produitDao.findProduitsStockBas();
    }

    public boolean estStockBas(Produit produit) {
        return produit.getStockActuel() < SEUIL_ALERTE_STOCK;
    }

    public void ajouterProduit(Produit produit) throws SQLException {
        produitDao.create(produit);
    }

    public void modifierProduit(Produit produit) throws SQLException {
        produitDao.update(produit);
    }

    public void supprimerProduit(String numProd) throws SQLException {
        produitDao.delete(numProd);
    }
}