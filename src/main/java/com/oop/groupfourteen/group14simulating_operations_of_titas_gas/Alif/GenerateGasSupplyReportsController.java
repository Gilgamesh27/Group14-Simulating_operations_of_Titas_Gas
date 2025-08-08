package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class GenerateGasSupplyReportsController {
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private TableColumn zoneColumn;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private TableColumn supplyRateColumn;
    @javafx.fxml.FXML
    private TableColumn pressureColumn;
    @javafx.fxml.FXML
    private TableColumn totalSupplyColumn;
    @javafx.fxml.FXML
    private Button fetchDataButton;
    @javafx.fxml.FXML
    private Label totalSupplyLabel;
    @javafx.fxml.FXML
    private Button downloadReportButton;
    @javafx.fxml.FXML
    private Label averagePressureLabel;
    @javafx.fxml.FXML
    private Button viewReportButton;
    @javafx.fxml.FXML
    private ComboBox reportTypeComboBox;
    @javafx.fxml.FXML
    private TableView supplyReportTable;
    @javafx.fxml.FXML
    private TableColumn dateColumn;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleFetchData(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleViewReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleDownloadReport(ActionEvent actionEvent) {
    }
}