package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;

public class InspectScadaSystemLogsController
{
    @FXML
    private TableColumn colSeverity;
    @FXML
    private TableColumn colMessage;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView logTable;
    @FXML
    private TableColumn colDate;
    @FXML
    private ChoiceBox severityChoice;
    @FXML
    private Label statusLabel;
    @FXML
    private PieChart severityChart;

    @FXML
    public void initialize() {

        severityChoice.setItems(FXCollections.observableArrayList("Low", "Medium", "High", "Critical"));

        colDate.setCellValueFactory(cellData -> cellData.getClass().getProtectionDomain());
        

        ObservableList logs = null;
        logTable.setItems(logs);

        logs.addAll(
               
        );

        updateChart();
    }

    private void updateChart() {

        severityChart.setData(FXCollections.observableArrayList(
                new PieChart.Data("High", 5),
                new PieChart.Data("Low", 3)
        ));

    }

    @FXML
    public void annotateButton(ActionEvent actionEvent) {

        statusLabel.setText("Annotation added to selected log.");
    }

    @FXML
    public void storeButton(ActionEvent actionEvent) {

        statusLabel.setText("Annotated report stored successfully.");
    }

    @FXML
    public void validateButton(ActionEvent actionEvent) {

        statusLabel.setText("Selected log validated successfully.");
    }

    @FXML
    public void filterButton(ActionEvent actionEvent) {

        statusLabel.setText("Filter applied for date " + datePicker.getValue() + " and severity " + severityChoice.getValue());
    }
}