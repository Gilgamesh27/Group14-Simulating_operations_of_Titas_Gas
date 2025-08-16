package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EmergencyShutdownHandlingController
{
    @FXML
    private Label emergencyOutputLabel;

    @FXML
    public void initialize() {
    }

    @FXML
    public void triggerShutdownButton(ActionEvent actionEvent) {

        boolean emergencyDetected = false;

        if (emergencyDetected) {
            boolean shutdownTriggered = true;
            emergencyOutputLabel.setText("Emergency shutdown protocol activated.");
        } else {
            emergencyOutputLabel.setText("No emergency detected. Cannot shut down.");
        }
    }

    @FXML
    public void notifyControlButton(ActionEvent actionEvent) {

        boolean shutdownTriggered = false;
        if (shutdownTriggered) {
            emergencyOutputLabel.setText("Central control and response teams notified.");
        } else {
            emergencyOutputLabel.setText("Shutdown not triggered yet.");
        }
    }

    @FXML
    public void detectEmergencyButton(ActionEvent actionEvent) {

        boolean emergencyDetected = true;
        emergencyOutputLabel.setText("Emergency detected: Pressure burst in Zone 1!");
    }

    @FXML
    public void documentStepsButton(ActionEvent actionEvent) {

        boolean shutdownTriggered = false;
        if (shutdownTriggered) {
            boolean stepsDocumented = true;
            emergencyOutputLabel.setText("Shutdown steps documented successfully.");
        } else {
            emergencyOutputLabel.setText("Cannot document. Shutdown not triggered.");
        }
    }

    @FXML
    public void archiveReportButton(ActionEvent actionEvent) {

        boolean stepsDocumented = false;
        if (stepsDocumented) {
            emergencyOutputLabel.setText("Emergency report archived. Process complete.");
            boolean emergencyDetected = false;
            boolean shutdownTriggered = false;
            stepsDocumented = false;
        } else {
            emergencyOutputLabel.setText("Please document steps before archiving.");
        }
    }
}