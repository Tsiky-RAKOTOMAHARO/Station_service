package com.station.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientController {

    @FXML
    private TextField txtRecherche;

    @FXML
    private TableView<?> tableClients;

    @FXML
    private TableColumn<?, ?> colIdClient;

    @FXML
    private TableColumn<?, ?> colNomClient;

    @FXML
    private TableColumn<?, ?> colPrenomClient;

    @FXML
    private TableColumn<?, ?> colTelephoneClient;

    @FXML
    public void handleRechercherClient(ActionEvent event) {
    }
}