package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.HashMap;
import java.util.Map;

public class ProcessRefundsController implements Initializable {

    @javafx.fxml.FXML
    private ComboBox<String> refundRequestComboBox;
    @javafx.fxml.FXML
    private TextField refundAmountField;
    @javafx.fxml.FXML
    private TextField reasonField;
    @javafx.fxml.FXML
    private Button validateButton;
    @javafx.fxml.FXML
    private Button processButton;
    @javafx.fxml.FXML
    private Button confirmButton;
    @javafx.fxml.FXML
    private TextArea validationArea;
    @javafx.fxml.FXML
    private TextArea confirmationArea;

    private Map<String, RefundRequest> refundDatabase;
    private RefundRequest currentRequest;

    private static class RefundRequest {
        String requestId, customerId, customerName, reason, status;
        double requestedAmount;
        RefundRequest(String requestId, String customerId, String customerName, double requestedAmount, String reason, String status) {
            this.requestId = requestId;
            this.customerId = customerId;
            this.customerName = customerName;
            this.requestedAmount = requestedAmount;
            this.reason = reason;
            this.status = status;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refundDatabase = new HashMap<>();
        refundDatabase.put("REF001 - John Doe - $45.50", new RefundRequest("REF001", "CUST001", "John Doe", 45.50, "Overpayment", "Pending"));
        refundDatabase.put("REF002 - Jane Smith - $89.75", new RefundRequest("REF002", "CUST002", "Jane Smith", 89.75, "Double payment", "Pending"));

        ObservableList<String> requestIds = FXCollections.observableArrayList(refundDatabase.keySet());
        refundRequestComboBox.setItems(requestIds);
        refundRequestComboBox.setOnAction(e -> loadRefundRequest());
    }

    private void loadRefundRequest() {
        String selected = refundRequestComboBox.getValue();
        if (selected != null) {
            currentRequest = refundDatabase.get(selected);
            if (currentRequest != null) {
                refundAmountField.setText(String.valueOf(currentRequest.requestedAmount));
                reasonField.setText(currentRequest.reason);
                validationArea.clear();
                confirmationArea.clear();
            }
        }
    }

    @javafx.fxml.FXML
    private void handleValidateRequest() {
        if (currentRequest != null) {
            validationArea.setText("Validated for processing.");
        }
    }

    @javafx.fxml.FXML
    private void handleProcessRefund() {
        if (currentRequest != null) {
            confirmationArea.setText("Processing initiated...");
        }
    }

    @javafx.fxml.FXML
    private void handleConfirmRefund() {
        if (currentRequest != null) {
            confirmationArea.setText("Refund confirmed successfully!");
            currentRequest.status = "Completed";
        }
    }
}
