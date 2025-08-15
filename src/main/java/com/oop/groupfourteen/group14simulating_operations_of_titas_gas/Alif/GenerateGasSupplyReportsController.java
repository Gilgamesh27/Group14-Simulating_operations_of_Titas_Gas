package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.List;
import java.io.Serializable;

public class GenerateGasSupplyReportsController implements Initializable {

    @FXML private ComboBox<String> reportTypeComboBox;
    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private Button fetchDataButton;
    @FXML private Button saveToBinButton;
    @FXML private Button loadFromBinButton;
    @FXML private Button viewChartsButton;
    @FXML private Button generatePDFButton;
    @FXML private TableView<SupplyReportData> supplyReportTable;
    @FXML private TableColumn<SupplyReportData, String> dateColumn;
    @FXML private TableColumn<SupplyReportData, String> zoneColumn;
    @FXML private TableColumn<SupplyReportData, Double> supplyRateColumn;
    @FXML private TableColumn<SupplyReportData, Double> totalSupplyColumn;
    @FXML private TableColumn<SupplyReportData, Double> pressureColumn;
    @FXML private Label totalSupplyLabel;
    @FXML private Label averagePressureLabel;

    private ObservableList<SupplyReportData> reportList;
    private Stage currentStage;

    public static class SupplyReportData implements Serializable {

        private String date;
        private String zone;
        private Double supplyRate;
        private Double totalSupply;
        private Double pressure;

        public SupplyReportData(String date, String zone, Double supplyRate, Double totalSupply, Double pressure) {
            this.date = date;
            this.zone = zone;
            this.supplyRate = supplyRate;
            this.totalSupply = totalSupply;
            this.pressure = pressure;
        }

        public String getDate() { return date; }
        public String getZone() { return zone; }
        public Double getSupplyRate() { return supplyRate; }
        public Double getTotalSupply() { return totalSupply; }
        public Double getPressure() { return pressure; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            ObservableList<String> reportTypes = FXCollections.observableArrayList(
                    "Daily Supply Report", "Weekly Supply Summary",
                    "Monthly Supply Analysis", "Zone-wise Supply Report"
            );
            reportTypeComboBox.setItems(reportTypes);

            fromDatePicker.setValue(LocalDate.now().minusDays(30));
            toDatePicker.setValue(LocalDate.now());

            dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
            zoneColumn.setCellValueFactory(new PropertyValueFactory<>("zone"));
            supplyRateColumn.setCellValueFactory(new PropertyValueFactory<>("supplyRate"));
            totalSupplyColumn.setCellValueFactory(new PropertyValueFactory<>("totalSupply"));
            pressureColumn.setCellValueFactory(new PropertyValueFactory<>("pressure"));

            reportList = FXCollections.observableArrayList();
            supplyReportTable.setItems(reportList);

            // Initialize currentStage when the scene is available
            if (fetchDataButton != null && fetchDataButton.getScene() != null) {
                currentStage = (Stage) fetchDataButton.getScene().getWindow();
            }
        } catch (Exception e) {
            System.err.println("Error initializing controller: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleFetchData() {
        if (validateInputs()) {
            reportList.clear();
            reportList.addAll(
                    new SupplyReportData("2024-01-15", "Zone A", 2500.0, 60000.0, 45.2),
                    new SupplyReportData("2024-01-15", "Zone B", 1800.0, 43200.0, 38.7),
                    new SupplyReportData("2024-01-15", "Zone C", 3200.0, 76800.0, 52.1),
                    new SupplyReportData("2024-01-15", "Zone D", 4500.0, 108000.0, 48.9)
            );

            updateSummaryLabels();
            showAlert(Alert.AlertType.INFORMATION, "Data Fetched", "Gas supply data loaded successfully.");
        }
    }

    private Stage getCurrentStage() {
        if (currentStage == null) {
            // Try to get stage from any available button
            if (fetchDataButton != null && fetchDataButton.getScene() != null) {
                currentStage = (Stage) fetchDataButton.getScene().getWindow();
            } else if (saveToBinButton != null && saveToBinButton.getScene() != null) {
                currentStage = (Stage) saveToBinButton.getScene().getWindow();
            } else if (loadFromBinButton != null && loadFromBinButton.getScene() != null) {
                currentStage = (Stage) loadFromBinButton.getScene().getWindow();
            }
        }
        return currentStage;
    }

    @FXML
    private void handleSaveToBin() {
        if (reportList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }

        Stage stage = getCurrentStage();
        if (stage == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot access application window.");
            return;
        }

        String fileName = "gas_supply_data_" + System.currentTimeMillis();

        if (FileHandler.saveToBin(stage, reportList, fileName)) {
            showAlert(Alert.AlertType.INFORMATION, "Saved", "Data saved to binary file successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to save data.");
        }
    }

    @FXML
    private void handleLoadFromBin() {
        Stage stage = getCurrentStage();
        if (stage == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot access application window.");
            return;
        }

        List<?> loadedData = FileHandler.loadFromBin(stage);
        if (loadedData != null && !loadedData.isEmpty()) {
            reportList.clear();
            for (Object obj : loadedData) {
                if (obj instanceof SupplyReportData) {
                    reportList.add((SupplyReportData) obj);
                }
            }
            updateSummaryLabels();
            showAlert(Alert.AlertType.INFORMATION, "Loaded", "Data loaded from binary file successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load data from binary file.");
        }
    }

    @FXML
    private void handleViewCharts() {
        if (reportList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }

        // Redirect to View Usage Statistics for comprehensive chart visualization
        showAlert(Alert.AlertType.INFORMATION, "View Charts",
                "Please use the 'View Usage Statistics' section from the main dashboard for comprehensive chart visualization.");
    }

    @FXML
    private void handleGeneratePDF() {
        if (reportList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }

        Stage stage = getCurrentStage();
        if (stage == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot access application window.");
            return;
        }

        String reportType = reportTypeComboBox.getValue();
        String fromDate = fromDatePicker.getValue().toString();
        String toDate = toDatePicker.getValue().toString();

        if (PDFGenerator.generateGasSupplyReport(stage, reportList, reportType, fromDate, toDate)) {
            showAlert(Alert.AlertType.INFORMATION, "Report Generated", "Report has been generated successfully.");
        } else {
            showAlert(Alert.AlertType.ERROR, "Generation Failed", "Failed to generate report.");
        }
    }

    private void updateSummaryLabels() {
        double totalSupply = reportList.stream().mapToDouble(SupplyReportData::getTotalSupply).sum();
        double avgPressure = reportList.stream().mapToDouble(SupplyReportData::getPressure).average().orElse(0);

        totalSupplyLabel.setText(String.format("Total Supply: %.1f m³", totalSupply));
        averagePressureLabel.setText(String.format("Average Pressure: %.1f PSI", avgPressure));
    }

    private boolean validateInputs() {
        if (reportTypeComboBox.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select a report type.");
            return false;
        }

        if (fromDatePicker.getValue() == null || toDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please select date range.");
            return false;
        }

        if (fromDatePicker.getValue().isAfter(toDatePicker.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Error", "From date cannot be after to date.");
            return false;
        }

        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}