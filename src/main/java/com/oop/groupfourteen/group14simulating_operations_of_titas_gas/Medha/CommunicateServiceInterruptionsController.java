package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CommunicateServiceInterruptionsController
{
    @javafx.fxml.FXML
    private ComboBox affectedZonesCombo;
    @javafx.fxml.FXML
    private TextField maintenanceInfoField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {

        affectedZonesCombo.getItems().addAll("Zone A", "Zone B", "Zone C");
    }

    @javafx.fxml.FXML
    public void notifyCustomersButton(ActionEvent actionEvent) {

        statusLabel.setText("Customers in " + affectedZonesCombo.getValue() + " notified.");
    }

    @javafx.fxml.FXML
    public void logNotificationsButton(ActionEvent actionEvent) {

        statusLabel.setText("Notifications logged.");
    }

    @javafx.fxml.FXML
    public void generateReportButton(ActionEvent actionEvent) {

        statusLabel.setText("Service disruption report generated.");
    }
}