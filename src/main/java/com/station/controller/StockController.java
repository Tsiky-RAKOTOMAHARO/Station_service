package com.station.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockController {

    @FXML
    private ComboBox<?> cbProduitEntree;

    @FXML
    private TextField txtQuantiteAjoutee;

    @FXML
    private Button btnAjouterStock;

    @FXML
    private TableView<?> tableStocks;

    @FXML
    private TableColumn<?, ?> colNumProd;

    @FXML
    private TableColumn<?, ?> colDesignation;

    @FXML
    private TableColumn<?, ?> colPrixUnitaire;

    @FXML
    private TableColumn<?, ?> colStockActuel;

    @FXML
    public void handleAjouterStock(ActionEvent event) {
    }
}