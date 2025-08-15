package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScheduleTechnicianVisitsController
{
    @javafx.fxml.FXML
    private TextField technicianIdField;
    @javafx.fxml.FXML
    private TextField visitTimeField;
    @javafx.fxml.FXML
    private DatePicker visitDatePicker;
    @javafx.fxml.FXML
    private TextField customerIdField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void assignTechnicianButton(ActionEvent actionEvent) {

        if (technicianIdField.getText().isEmpty()) {
            statusLabel.setText("Enter technician ID to assign.");
        } else {
            statusLabel.setText("Technician " + technicianIdField.getText() + " assigned.");
        }
    }

    @javafx.fxml.FXML
    public void confirmCompletionButton(ActionEvent actionEvent) {

        statusLabel.setText("Visit completed. Ticket closed.");
    }

    @javafx.fxml.FXML
    public void logVisitButton(ActionEvent actionEvent) {

        statusLabel.setText("Technician visit logged.");
    }

    @javafx.fxml.FXML
    public void receiveRequestButton(ActionEvent actionEvent) {

        if (customerIdField.getText().isEmpty()) {
            statusLabel.setText("Enter customer ID first.");
        } else {
            statusLabel.setText("Service request received for customer: " + customerIdField.getText());
        }
    }

    @javafx.fxml.FXML
    public void scheduleVisitButton(ActionEvent actionEvent) {

        if (visitDatePicker.getValue() == null || visitTimeField.getText().isEmpty()) {
            statusLabel.setText("Select date and time for visit.");
        } else {
            statusLabel.setText("Visit scheduled for " + visitDatePicker.getValue() + " at " + visitTimeField.getText());
        }
    }
}