package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class IssueFinalBillController implements Initializable {

    @FXML private ComboBox<String> customerComboBox;
    @FXML private DatePicker disconnectionDatePicker;
    @FXML private TextField finalReadingField;
    @FXML private Button generateButton;
    @FXML private Button viewBillButton;
    @FXML private Button submitButton;
    @FXML private TextArea billDetailsArea;
    @FXML private TextArea confirmationArea;

    private Map<String, DisconnectedCustomer> disconnectedCustomers;
    private DisconnectedCustomer selectedCustomer;
    private FinalBill generatedBill;

    private static class DisconnectedCustomer {
        String id, name, address;
        double lastReading, outstandingBalance;
        LocalDate lastBillDate;

        DisconnectedCustomer(String id, String name, String address, double lastReading, double outstandingBalance, LocalDate lastBillDate) {
            this.id = id; this.name = name; this.address = address;
            this.lastReading = lastReading; this.outstandingBalance = outstandingBalance; this.lastBillDate = lastBillDate;
        }
    }

    private static class FinalBill {
        String billId;
        double consumptionCharge, outstandingBalance, disconnectionFee, totalAmount;

        FinalBill(String billId, double consumptionCharge, double outstandingBalance, double disconnectionFee) {
            this.billId = billId;
            this.consumptionCharge = consumptionCharge;
            this.outstandingBalance = outstandingBalance;
            this.disconnectionFee = disconnectionFee;
            this.totalAmount = consumptionCharge + outstandingBalance + disconnectionFee;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        disconnectedCustomers = new HashMap<>();
        disconnectedCustomers.put("DISC001 - John Doe", new DisconnectedCustomer("DISC001", "John Doe", "123 Main St", 1250.5, 89.50, LocalDate.of(2024, 1, 15)));
        disconnectedCustomers.put("DISC002 - Jane Smith", new DisconnectedCustomer("DISC002", "Jane Smith", "456 Oak Ave", 2340.8, 156.75, LocalDate.of(2024, 1, 10)));
        ObservableList<String> customerList = FXCollections.observableArrayList(disconnectedCustomers.keySet());
        customerComboBox.setItems(customerList);
        customerComboBox.setOnAction(e -> loadCustomerInfo());
        disconnectionDatePicker.setValue(LocalDate.now());

        finalReadingField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) finalReadingField.setText(oldVal);
        });
    }

    private void loadCustomerInfo() {
        String key = customerComboBox.getValue();
        if (key != null) {
            selectedCustomer = disconnectedCustomers.get(key);
            finalReadingField.setText(String.valueOf(selectedCustomer.lastReading + 50));
            billDetailsArea.setText("Customer: " + selectedCustomer.name + "\nLast Reading: " + selectedCustomer.lastReading);
            confirmationArea.clear();
            generatedBill = null;
        }
    }

    @FXML
    private void handleGenerateFinalBill() {
        if (!validateInputs()) return;
        double finalReading = Double.parseDouble(finalReadingField.getText());
        double consumption = finalReading - selectedCustomer.lastReading;
        double consumptionCharge = consumption * 0.85;
        double disconnectionFee = 50.0;
        generatedBill = new FinalBill("FB" + System.currentTimeMillis()%100000, consumptionCharge, selectedCustomer.outstandingBalance, disconnectionFee);

        billDetailsArea.setText(
                "Bill ID: " + generatedBill.billId +
                        "\nCustomer: " + selectedCustomer.name +
                        "\nConsumption: " + consumption +
                        "\nTotal: $" + generatedBill.totalAmount
        );
    }

    @FXML
    private void handleViewBill() {
        if (generatedBill == null) { showAlert(Alert.AlertType.WARNING, "No Bill", "Generate bill first."); return; }
        showAlert(Alert.AlertType.INFORMATION, "Bill", "Bill ID: " + generatedBill.billId + "\nTotal: $" + generatedBill.totalAmount);
    }

    @FXML
    private void handleSubmitFinalBilling() {
        if (generatedBill == null) { showAlert(Alert.AlertType.WARNING, "No Bill", "Generate bill first."); return; }
        confirmationArea.setText("Bill ID: " + generatedBill.billId + " submitted.\nTotal: $" + generatedBill.totalAmount);
        showAlert(Alert.AlertType.INFORMATION, "Submitted", "Final bill submitted.");
    }

    private boolean validateInputs() {
        if (customerComboBox.getValue() == null) { showAlert(Alert.AlertType.ERROR, "Error", "Select customer"); return false; }
        if (disconnectionDatePicker.getValue() == null) { showAlert(Alert.AlertType.ERROR, "Error", "Select date"); return false; }
        if (finalReadingField.getText().isEmpty()) { showAlert(Alert.AlertType.ERROR, "Error", "Enter final reading"); return false; }
        try {
            double val = Double.parseDouble(finalReadingField.getText());
            if (val < selectedCustomer.lastReading) { showAlert(Alert.AlertType.ERROR, "Error", "Final reading too low"); return false; }
        } catch (NumberFormatException e) { showAlert(Alert.AlertType.ERROR, "Error", "Invalid number"); return false; }
        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
