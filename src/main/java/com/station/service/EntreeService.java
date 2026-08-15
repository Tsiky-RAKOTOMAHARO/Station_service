package com.station.service;

import com.station.dao.EntreeDao;
import com.station.dao.ProduitDao;
import com.station.model.Entree;
import com.station.model.Produit;

import java.sql.SQLException;
import java.util.List;

public class EntreeService {

    private final EntreeDao entreeDao;
    private final ProduitDao produitDao;

    public EntreeService() {
        this.entreeDao = new EntreeDao();
        this.produitDao = new ProduitDao();
    }

    public EntreeService(EntreeDao entreeDao, ProduitDao produitDao) {
        this.entreeDao = entreeDao;
        this.produitDao = produitDao;
    }

    public List<Entree> getAllEntrees() throws SQLException {
        return entreeDao.findAll();
    }

    public List<Entree> getEntreesByProduit(String numProd) throws SQLException {
        return entreeDao.findByProduit(numProd);
    }

    public Entree enregistrerEntree(Entree entree) throws SQLException {
        Produit produit = produitDao.findById(entree.getNumProd());
        if (produit == null) {
            throw new IllegalArgumentException("Produit introuvable : " + entree.getNumProd());
        }

        entreeDao.create(entree);

        int nouveauStock = produit.getStockActuel() + entree.getQuantiteAjoutee();
        produitDao.updateStock(produit.getNumProd(), nouveauStock);

        return entree;
    }

    public void supprimerEntree(String numEntree) throws SQLException {
        entreeDao.delete(numEntree);
    }
}