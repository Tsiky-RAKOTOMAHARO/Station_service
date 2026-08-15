package com.station.controller;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DashboardController {

    @FXML
    private Label lblRecetteTotale;

    @FXML
    private Label lblAlertesStock;

    @FXML
    private BarChart<String, Number> chartRecettesMois;

    @FXML
    private CategoryAxis xAxisMois;

    @FXML
    private NumberAxis yAxisMontant;

    @FXML
    private TableView<?> tableTopClients;

    @FXML
    private TableColumn<?, ?> colNomClient;

    @FXML
    private TableColumn<?, ?> colTotalDepense;

}