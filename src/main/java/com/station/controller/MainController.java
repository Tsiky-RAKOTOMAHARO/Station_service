package com.station.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnVentes;

    @FXML
    private Button btnStocks;

    @FXML
    private Button btnEntretiens;

    @FXML
    private Button btnClients;

    @FXML
    private StackPane contentArea;

    @FXML
    public void initialize() {
        // Charger le dashboard par défaut au démarrage
        loadView("/fxml/dashboard.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Parent view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleNavDashboard(ActionEvent event) {
        loadView("/fxml/dashboard.fxml");
    }

    @FXML
    public void handleNavVentes(ActionEvent event) {
        loadView("/fxml/ventes.fxml");
    }

    @FXML
    public void handleNavStocks(ActionEvent event) {
        loadView("/fxml/stocks.fxml");
    }

    @FXML
    public void handleNavEntretiens(ActionEvent event) {
        loadView("/fxml/entretiens.fxml");
    }

    @FXML
    public void handleNavClients(ActionEvent event) {
        loadView("/fxml/clients.fxml");
    }
}