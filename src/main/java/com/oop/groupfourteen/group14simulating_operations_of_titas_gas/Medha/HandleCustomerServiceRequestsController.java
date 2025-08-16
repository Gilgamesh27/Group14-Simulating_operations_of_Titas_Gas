package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HandleCustomerServiceRequestsController
{
    @javafx.fxml.FXML
    private TextField requestField;
    @javafx.fxml.FXML
    private TextArea logArea;
    @javafx.fxml.FXML
    private TextField customerIdField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }
    @javafx.fxml.FXML
    public void escalateButton(ActionEvent actionEvent) {

        logArea.appendText("Request escalated to higher authority.\n");
        statusLabel.setText("Request escalated.");
    }

    @javafx.fxml.FXML
    public void verifyCustomerButton(ActionEvent actionEvent) {

        String customerId = customerIdField.getText();
        if (customerId.isEmpty()) {
            statusLabel.setText("Enter Customer ID.");
        } else {
            statusLabel.setText("Customer " + customerId + " verified.");
        }
    }

    @javafx.fxml.FXML
    public void approveButton(ActionEvent actionEvent) {

        logArea.appendText("Request approved.\n");
        statusLabel.setText("Request approved.");
    }

    @javafx.fxml.FXML
    public void receiveRequestButton(ActionEvent actionEvent) {

        String request = requestField.getText();
        if (request.isEmpty()) {
            statusLabel.setText("Please enter a request.");
        } else {
            logArea.appendText("Request received: " + request + "\n");
            statusLabel.setText("Request received successfully.");
        }
    }

    @javafx.fxml.FXML
    public void closeRequestButton(ActionEvent actionEvent) {

        logArea.appendText("Request closed after resolution.\n");
        statusLabel.setText("Request closed.");
    }
}