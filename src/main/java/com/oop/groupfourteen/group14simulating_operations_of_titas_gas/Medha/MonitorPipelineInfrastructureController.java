package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;

public class MonitorPipelineInfrastructureController
{
    @javafx.fxml.FXML
    private Label maintenanceStatus;
    @javafx.fxml.FXML
    private Label statusOutput;
    @javafx.fxml.FXML
    private Label faultZoneOutput;

    @javafx.fxml.FXML
    public void initialize() {
        maintenanceStatus.setText("Status: All pipelines operational.\nLast maintenance: 2 weeks ago.");
    }

    @javafx.fxml.FXML
    public void archiveButton(ActionEvent actionEvent) {

        statusOutput.setText("Maintenance records archived successfully.");
    }

    @javafx.fxml.FXML
    public void zoomFaultButton(ActionEvent actionEvent) {

        faultZoneOutput.setText("Zoomed in on Zone 3 — Minor leakage detected.");
    }

    @javafx.fxml.FXML
    public void flagDamageButton(ActionEvent actionEvent) {

        faultZoneOutput.setText("Damage flagged in Zone 3. Data saved.");
    }

    @javafx.fxml.FXML
    public void validateSensorButton(ActionEvent actionEvent) {

        statusOutput.setText("Sensor reading: 102 PSI\nPhysical inspection: 101.5 PSI\nValidation: OK");
    }
}