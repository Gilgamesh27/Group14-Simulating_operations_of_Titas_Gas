package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RespondToLeakAlertsController
{
    @FXML
    private Label responseOutput;
    @FXML
    private Label alertLabel;

    @FXML
    public void initialize() {

        alertLabel.setText("ALERT: Gas leak detected in Zone 2!");
        boolean alertReceived = true;
    }

    @FXML
    public void shutdownButton(ActionEvent actionEvent) {

        boolean alertReceived = false;
        if (alertReceived) {
            responseOutput.setText("Zone 2 shutdown remotely to prevent spread.");
            boolean isShutdown = true;
        } else {
            responseOutput.setText("No active alert to act on.");
        }
    }

    @FXML
    public void validateAlertButton(ActionEvent actionEvent) {

        boolean alertReceived = false;
        if (alertReceived) {
            responseOutput.setText("Validation: Sensor confirms leak.\nManual input: Leak visible.");
        } else {
            responseOutput.setText("No alert to validate.");
        }
    }

    @FXML
    public void recordActionButton(ActionEvent actionEvent) {

        boolean zoneShutdown = false;
        if (zoneShutdown) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            responseOutput.setText("Shutdown action recorded at: " + timestamp);
        } else {
            responseOutput.setText("Shutdown not initiated. Cannot record.");
        }
    }

    @FXML
    public void logIncidentButton(ActionEvent actionEvent) {

        boolean zoneShutdown = false;
        if (zoneShutdown) {
            responseOutput.setText("Incident logged and marked as resolved.");
            zoneShutdown = false;
        } else {
            responseOutput.setText("Cannot log incident. Shutdown not done.");
        }
    }
}