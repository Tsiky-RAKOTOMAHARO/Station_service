package com.station.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class EntretienController {

    @FXML
    private ComboBox<?> cbClient;

    @FXML
    private ComboBox<?> cbService;

    @FXML
    private TextField txtImmatriculation;

    @FXML
    private Button btnEnregistrer;

    @FXML
    private Button btnGenererRecu;

    @FXML
    private TableView<?> tableEntretiens;

    @FXML
    private TableColumn<?, ?> colNumEntr;

    @FXML
    private TableColumn<?, ?> colClient;

    @FXML
    private TableColumn<?, ?> colService;

    @FXML
    private TableColumn<?, ?> colImmat;

    @FXML
    private TableColumn<?, ?> colMontant;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    public void handleEnregistrerEntretien(ActionEvent event) {
    }

    @FXML
    public void handleGenererRecuPdf(ActionEvent event) {
    }
}