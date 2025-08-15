package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TrackServiceHistoryController
{
    @javafx.fxml.FXML
    private TextField customerIdField;
    @javafx.fxml.FXML
    private TextField issueAnnotationField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void searchCustomerButton(ActionEvent actionEvent) {

        if (customerIdField.getText().isEmpty()) {
            statusLabel.setText("Please enter a customer ID.");
        } else {
            statusLabel.setText("Customer found: " + customerIdField.getText());
        }
    }

    @javafx.fxml.FXML
    public void saveUpdatesButton(ActionEvent actionEvent) {

        if (issueAnnotationField.getText().isEmpty()) {
            statusLabel.setText("No updates to save.");
        } else {
            statusLabel.setText("Updates saved: " + issueAnnotationField.getText());
        }
    }

    @javafx.fxml.FXML
    public void viewRecordsButton(ActionEvent actionEvent) {

        statusLabel.setText("Displaying past service records for customer.");
    }

    @javafx.fxml.FXML
    public void validateRecordsButton(ActionEvent actionEvent) {

        statusLabel.setText("Records validated successfully.");
    }
}