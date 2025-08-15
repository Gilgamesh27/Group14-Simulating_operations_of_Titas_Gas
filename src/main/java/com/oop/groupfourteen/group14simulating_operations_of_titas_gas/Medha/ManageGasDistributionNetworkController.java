package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ManageGasDistributionNetworkController {

    @FXML
    private Label pressureText;

    @FXML
    private TextField valveInputField;

    @FXML
    private TextField manualChangeField;

    @FXML
    private Label outputLabel;

    // Simulated initial pressure
    private final double currentPressure = 75.5;

    @FXML
    public void initialize() {
        pressureText.setText("Pressure: " + currentPressure + " psi\nFlow: 1200 m³/h");
    }

    @FXML
    public void adjustValve() {
        String input = valveInputField.getText();
        if (input.isEmpty()) {
            outputLabel.setText("Please enter a valve adjustment value.");
            return;
        }
        outputLabel.setText("Valve adjusted to: " + input);
    }

    @FXML
    public void detectAnomaly() {
        if (currentPressure < 50 || currentPressure > 100) {
            outputLabel.setText("Anomaly Detected! Pressure out of range.");
        } else {
            outputLabel.setText("No anomalies detected.");
        }
    }

    @FXML
    public void recordManualChange() {
        String data = manualChangeField.getText();
        if (data.isEmpty()) {
            outputLabel.setText("Please enter manual change details.");
        } else {
            outputLabel.setText("Manual change recorded: " + data);
        }
    }

    @FXML
    public void generateReport() {
        String report = "Performance Report:\nPressure: " + currentPressure + " psi\nFlow: 1200 m³/h\nValve Status: OK";
        outputLabel.setText(report);
    }
}