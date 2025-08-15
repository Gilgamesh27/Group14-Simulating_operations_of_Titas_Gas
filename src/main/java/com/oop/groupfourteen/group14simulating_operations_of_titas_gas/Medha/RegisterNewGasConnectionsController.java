package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.UUID;

public class RegisterNewGasConnectionsController
{
    @javafx.fxml.FXML
    private TextField documentField;
    @javafx.fxml.FXML
    private DatePicker connectionDatePicker;
    @javafx.fxml.FXML
    private TextField customerNameField;
    @javafx.fxml.FXML
    private TextField customerAddressField;
    @javafx.fxml.FXML
    private TextField connectionIdField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void generateIdButton(ActionEvent actionEvent) {

        String id = "CON-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        connectionIdField.setText(id);
        statusLabel.setText("Connection ID generated and saved.");
    }

    @javafx.fxml.FXML
    public void validateButton(ActionEvent actionEvent) {

        if (customerNameField.getText().isEmpty() || customerAddressField.getText().isEmpty() || documentField.getText().isEmpty()) {
            statusLabel.setText("Please fill all customer details and documents.");
        } else {
            statusLabel.setText("Eligibility & documents validated.");
        }
    }

    @javafx.fxml.FXML
    public void scheduleButton(ActionEvent actionEvent) {

        if (connectionDatePicker.getValue() == null) {
            statusLabel.setText("Please select a connection date.");
        } else {
            statusLabel.setText("Connection scheduled for " + connectionDatePicker.getValue() + ".");
        }
    }

    @javafx.fxml.FXML
    public void confirmButton(ActionEvent actionEvent) {

        statusLabel.setText("Confirmation sent to customer.");
    }
}