package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class GenerateDistributionAnalyticsController
{
    @FXML
    private TableView analyticsTable;
    @FXML
    private TableColumn colMonth;
    @FXML
    private TableColumn colUsage;
    @FXML
    private TextArea findingsArea;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {

        colMonth.setCellValueFactory(cellData -> cellData.getClass());
        colUsage.setCellValueFactory(cellData -> cellData.getClass().isArray());

        ObservableList analyticsData = null;
        analyticsTable.setItems(analyticsData);

        

        updatePressureTrendChart();
    }

    private void updatePressureTrendChart() {

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Pressure Trends");
        series.getData().add(new XYChart.Data<>("January", 75));
        series.getData().add(new XYChart.Data<>("February", 80));
        series.getData().add(new XYChart.Data<>("March", 78));
    }

    @FXML
    public void archiveButton(ActionEvent actionEvent) {

        statusLabel.setText("Analytics report archived successfully.");
    }

    @FXML
    public void interpretTrendsButton(ActionEvent actionEvent) {

        findingsArea.setText("Trend analysis: No significant anomalies detected.");
        statusLabel.setText("Trends interpreted successfully.");
    }

    @FXML
    public void fetchDataButton(ActionEvent actionEvent) {
        statusLabel.setText("Monthly gas usage data fetched successfully.");
    }
}