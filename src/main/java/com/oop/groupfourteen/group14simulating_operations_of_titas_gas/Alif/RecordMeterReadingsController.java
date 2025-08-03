package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.time.LocalDate;

public class RecordMeterReadingsController implements Initializable {

    @FXML private Button logoutBtn;
    @FXML private TextField customerIdField;
    @FXML private Button selectCustomerBtn;
    @FXML private VBox meterReadingSection;
    @FXML private Label previousReadingLabel;
    @FXML private TextField meterReadingField;
    @FXML private DatePicker readingDatePicker;
    @FXML private TextArea notesArea;
    @FXML private Button submitReadingBtn;
    @FXML private VBox confirmationSection;
    @FXML private TextArea confirmationArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set default date to today
        readingDatePicker.setValue(LocalDate.now());

        // Set up event handlers
        selectCustomerBtn.setOnAction(this::handleSelectCustomer);
        submitReadingBtn.setOnAction(this::handleSubmitReading);
        logoutBtn.setOnAction(this::handleLogout);

        // Validate meter reading input
        meterReadingField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*\\.?\\d*")) {
                meterReadingField.setText(oldValue);
            }
        });
    }

    @FXML
    private void handleSelectCustomer(ActionEvent event) {
        String customerId = customerIdField.getText().trim();

        if (customerId.isEmpty()) {
            showAlert("Error", "Please enter a customer ID", Alert.AlertType.ERROR);
            return;
        }

        // Validate customer ID (VL event type)
        if (!validateCustomerId(customerId)) {
            showAlert("Error", "Invalid customer ID format", Alert.AlertType.ERROR);
            return;
        }

        // Simulate fetching previous reading data
        previousReadingLabel.setText("1250.5 kWh");
        meterReadingSection.setVisible(true);

        showAlert("Success", "Customer selected successfully", Alert.AlertType.INFORMATION);
    }

    @FXML
    private void handleSubmitReading(ActionEvent event) {
        String readingText = meterReadingField.getText().trim();
        LocalDate readingDate = readingDatePicker.getValue();
        String notes = notesArea.getText().trim();

        if (readingText.isEmpty()) {
            showAlert("Error", "Please enter a meter reading", Alert.AlertType.ERROR);
            return;
        }

        if (readingDate == null) {
            showAlert("Error", "Please select a reading date", Alert.AlertType.ERROR);
            return;
        }

        try {
            double reading = Double.parseDouble(readingText);
            double previousReading = 1250.5; // This would come from database

            if (reading < previousReading) {
                showAlert("Error", "New reading cannot be less than previous reading", Alert.AlertType.ERROR);
                return;
            }

            // Process the reading (DP event type)
            processReading(customerIdField.getText(), reading, readingDate, notes);

            // Show confirmation (OP event type)
            showConfirmation(reading, readingDate);

        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid reading format", Alert.AlertType.ERROR);
        }
    }

    private boolean validateCustomerId(String customerId) {
        // VL event type - validation check
        return customerId.matches("\\d{6,8}");
    }

    private void processReading(String customerId, double reading, LocalDate date, String notes) {
        // DP event type - data processing
        // This would typically save to database
        System.out.println("Processing reading for customer: " + customerId);
        System.out.println("Reading: " + reading + " on " + date);
        System.out.println("Notes: " + notes);
    }

    private void showConfirmation(double reading, LocalDate readingDate) {
        // OP event type - output display
        StringBuilder confirmation = new StringBuilder();
        confirmation.append("Meter reading recorded successfully!\n\n");
        confirmation.append("Customer ID: ").append(customerIdField.getText()).append("\n");
        confirmation.append("Previous Reading: 1250.5 kWh\n");
        confirmation.append("New Reading: ").append(reading).append(" kWh\n");
        confirmation.append("Consumption: ").append(reading - 1250.5).append(" kWh\n");
        confirmation.append("Reading Date: ").append(readingDate).append("\n");
        confirmation.append("Recorded on: ").append(LocalDate.now());

        confirmationArea.setText(confirmation.toString());
        confirmationSection.setVisible(true);
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        // Handle logout logic here
        System.out.println("Logging out...");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}