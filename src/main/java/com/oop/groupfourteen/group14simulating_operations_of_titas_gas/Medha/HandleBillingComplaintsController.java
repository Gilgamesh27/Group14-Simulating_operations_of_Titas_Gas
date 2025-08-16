package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HandleBillingComplaintsController
{
    @javafx.fxml.FXML
    private TextArea complaintDetailsArea;
    @javafx.fxml.FXML
    private TextField customerIdField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void logComplaintButton(ActionEvent actionEvent) {

        if (customerIdField.getText().isEmpty() || complaintDetailsArea.getText().isEmpty()) {
            statusLabel.setText("Please enter customer ID and complaint details.");
        } else {
            statusLabel.setText("Complaint logged successfully.");
        }
    }

    @javafx.fxml.FXML
    public void validateErrorButton(ActionEvent actionEvent) {

        statusLabel.setText("Error validation complete.");
    }

    @javafx.fxml.FXML
    public void correctErrorButton(ActionEvent actionEvent) {

        statusLabel.setText("Billing corrected or justified.");
    }

    @javafx.fxml.FXML
    public void retrieveHistoryButton(ActionEvent actionEvent) {

        if (customerIdField.getText().isEmpty()) {
            statusLabel.setText("Enter customer ID to retrieve billing history.");
        } else {
            statusLabel.setText("Billing history retrieved for customer: " + customerIdField.getText());
        }
    }

    @javafx.fxml.FXML
    public void archiveReportButton(ActionEvent actionEvent) {

        statusLabel.setText("Complaint resolution report archived.");
    }
}