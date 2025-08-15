package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MonitorValveOperationsController
{
    @javafx.fxml.FXML
    private Label valveStatusLabel;
    @javafx.fxml.FXML
    private TextField overrideValveField;
    @javafx.fxml.FXML
    private Label valveOutputLabel;
    @javafx.fxml.FXML
    private PieChart severityChart;

    @javafx.fxml.FXML
    public void initialize() {
        valveStatusLabel.setText("Valves OK\nValve 1: Open\nValve 2: Closed\nValve 3: Open");
    }

    @javafx.fxml.FXML
    public void validateValveButton(ActionEvent actionEvent) {

        valveOutputLabel.setText("Auto-feedback matches current valve positions.\nValidation successful.");
    }

    @javafx.fxml.FXML
    public void recordInterventionButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void overrideButton(ActionEvent actionEvent) {

        String overriddenValve = overrideValveField.getText();
        if (overriddenValve.isEmpty()) {
            valveOutputLabel.setText("Enter a valve ID to override.");
        } else {
            valveOutputLabel.setText("Manual override applied to " + overriddenValve);
        }
    }

    @javafx.fxml.FXML
    public void archiveHistoryButton(ActionEvent actionEvent) {

        valveOutputLabel.setText("Valve operation history archived successfully.");
        String overriddenValve = "";
    }
    }
