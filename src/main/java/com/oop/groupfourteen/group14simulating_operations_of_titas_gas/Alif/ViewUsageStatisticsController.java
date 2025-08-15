package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.Map;
import java.util.HashMap;

public class ViewUsageStatisticsController implements Initializable {

    @FXML private DatePicker fromDatePicker;
    @FXML private DatePicker toDatePicker;
    @FXML private Button fetchDataButton;
    @FXML private Button viewStatisticsButton;
    @FXML private Button viewChartsButton;
    @FXML private Button downloadReportButton;
    @FXML private TableView<UsageData> usageStatisticsTable;
    @FXML private TableColumn<UsageData, String> zoneColumn;
    @FXML private TableColumn<UsageData, String> dateColumn;
    @FXML private TableColumn<UsageData, Double> usageRateColumn;
    @FXML private TableColumn<UsageData, Double> totalUsageColumn;
    @FXML private Label averageUsageLabel;
    @FXML private Label peakUsageLabel;

    private ObservableList<UsageData> usageList;

    public static class UsageData {
        private String zone;
        private String date;
        private Double usageRate;
        private Double totalUsage;

        public UsageData(String zone, String date, Double usageRate, Double totalUsage) {
            this.zone = zone;
            this.date = date;
            this.usageRate = usageRate;
            this.totalUsage = totalUsage;
        }

        public String getZone() { return zone; }
        public String getDate() { return date; }
        public Double getUsageRate() { return usageRate; }
        public Double getTotalUsage() { return totalUsage; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.setValue(LocalDate.now().minusDays(30));
        toDatePicker.setValue(LocalDate.now());

        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("zone"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        usageRateColumn.setCellValueFactory(new PropertyValueFactory<>("usageRate"));
        totalUsageColumn.setCellValueFactory(new PropertyValueFactory<>("totalUsage"));

        usageList = FXCollections.observableArrayList();
        usageStatisticsTable.setItems(usageList);
    }

    @FXML
    private void handleFetchData() {
        usageList.clear();
        usageList.addAll(
                new UsageData("Zone A", "2024-01-15", 2500.0, 60000.0),
                new UsageData("Zone B", "2024-01-15", 1800.0, 43200.0),
                new UsageData("Zone C", "2024-01-15", 3200.0, 76800.0),
                new UsageData("Zone D", "2024-01-15", 4500.0, 108000.0)
        );
        updateStatistics();
        showAlert(Alert.AlertType.INFORMATION, "Data Fetched", "Usage statistics data has been loaded successfully.");
    }

    @FXML
    private void handleViewStatistics() {
        if (usageList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }
        updateStatistics();
    }

    @FXML
    private void handleViewCharts() {
        if (usageList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }

        try {
            VBox chartView = createChartView();
            Stage chartStage = new Stage();
            chartStage.setTitle("Usage Statistics Charts");
            chartStage.setScene(new Scene(chartView, 800, 600));
            chartStage.show();
        } catch (Exception e) {
            System.err.println("Error creating charts: " + e.getMessage());
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Chart Error", "Failed to create charts: " + e.getMessage());
        }
    }

    @FXML
    private void handleDownloadReport() {
        if (usageList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Please fetch data first.");
            return;
        }
        showAlert(Alert.AlertType.INFORMATION, "Download Complete", "Usage statistics report has been downloaded successfully.");
    }

    /**
     * Create comprehensive chart view using JavaFX built-in charting
     */
    private VBox createChartView() {
        VBox chartContainer = new VBox(20);
        chartContainer.setStyle("-fx-padding: 20; -fx-background-color: white;");

        // Title
        Label titleLabel = new Label("Usage Statistics Charts");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        chartContainer.getChildren().add(titleLabel);

        // Create pie chart for total usage distribution
        PieChart pieChart = createUsagePieChart();
        pieChart.setPrefSize(350, 250);

        // Create bar chart for usage rates
        BarChart<String, Number> barChart = createUsageBarChart();
        barChart.setPrefSize(400, 250);

        // Add charts side by side
        HBox chartsRow = new HBox(20);
        chartsRow.getChildren().addAll(pieChart, barChart);
        chartContainer.getChildren().add(chartsRow);

        return chartContainer;
    }

    /**
     * Create pie chart showing total usage distribution by zone
     */
    private PieChart createUsagePieChart() {
        Map<String, Double> zoneUsage = new HashMap<>();

        for (UsageData data : usageList) {
            String zone = data.getZone();
            double currentUsage = zoneUsage.getOrDefault(zone, 0.0);
            zoneUsage.put(zone, currentUsage + data.getTotalUsage());
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Map.Entry<String, Double> entry : zoneUsage.entrySet()) {
            pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
        }

        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setTitle("Total Usage by Zone");
        pieChart.setLabelsVisible(true);

        return pieChart;
    }

    /**
     * Create bar chart comparing usage rates across zones
     */
    private BarChart<String, Number> createUsageBarChart() {
        Map<String, Double> zoneUsageRate = new HashMap<>();

        for (UsageData data : usageList) {
            String zone = data.getZone();
            double currentRate = zoneUsageRate.getOrDefault(zone, 0.0);
            zoneUsageRate.put(zone, currentRate + data.getUsageRate());
        }

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Zone");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Usage Rate (m³/hr)");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Usage Rate by Zone");

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Usage Rate");

        for (Map.Entry<String, Double> entry : zoneUsageRate.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        barChart.getData().add(series);

        return barChart;
    }

    private void updateStatistics() {
        double avgUsage = usageList.stream().mapToDouble(UsageData::getUsageRate).average().orElse(0);
        double peakUsage = usageList.stream().mapToDouble(UsageData::getUsageRate).max().orElse(0);

        averageUsageLabel.setText(String.format("Average Usage: %.1f m³/hr", avgUsage));
        peakUsageLabel.setText(String.format("Peak Usage: %.1f m³/hr", peakUsage));
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}