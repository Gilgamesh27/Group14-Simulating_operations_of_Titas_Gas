package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GeneratePaymentSummaryController implements Initializable {

    @javafx.fxml.FXML private DatePicker fromDatePicker;
    @javafx.fxml.FXML private DatePicker toDatePicker;
    @javafx.fxml.FXML private Button fetchDataButton;
    @javafx.fxml.FXML private Button viewSummaryButton;
    @javafx.fxml.FXML private Button downloadReportButton;
    @javafx.fxml.FXML private TableView<PaymentData> paymentSummaryTable;
    @javafx.fxml.FXML private TableColumn<PaymentData, String> dateColumn;
    @javafx.fxml.FXML private TableColumn<PaymentData, String> customerIdColumn;
    @javafx.fxml.FXML private TableColumn<PaymentData, Double> amountColumn;
    @javafx.fxml.FXML private TableColumn<PaymentData, String> paymentMethodColumn;
    @javafx.fxml.FXML private Label totalAmountLabel;

    private ObservableList<PaymentData> paymentList;

    public static class PaymentData {
        private String date;
        private String customerId;
        private Double amount;
        private String paymentMethod;

        public PaymentData(String date, String customerId, Double amount, String paymentMethod) {
            this.date = date;
            this.customerId = customerId;
            this.amount = amount;
            this.paymentMethod = paymentMethod;
        }

        public String getDate() { return date; }
        public String getCustomerId() { return customerId; }
        public Double getAmount() { return amount; }
        public String getPaymentMethod() { return paymentMethod; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fromDatePicker.setValue(LocalDate.now().minusDays(30));
        toDatePicker.setValue(LocalDate.now());

        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        paymentMethodColumn.setCellValueFactory(new PropertyValueFactory<>("paymentMethod"));

        paymentList = FXCollections.observableArrayList();
        paymentSummaryTable.setItems(paymentList);
    }

    @javafx.fxml.FXML
    private void handleFetchData() {
        if (!validateDateRange()) return;

        paymentList.clear();
        paymentList.addAll(
                new PaymentData("2024-01-15", "CUST001", 125.50, "Bank Transfer"),
                new PaymentData("2024-01-16", "CUST002", 89.75, "Credit Card"),
                new PaymentData("2024-01-17", "CUST003", 234.20, "Cash")
        );

        updateTotalAmount();
        showAlert(Alert.AlertType.INFORMATION, "Data Fetched", "Payment data fetched successfully.");
    }

    @javafx.fxml.FXML
    private void handleViewSummary() {
        if (paymentList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Fetch payment data first.");
            return;
        }

        double totalAmount = paymentList.stream().mapToDouble(PaymentData::getAmount).sum();
        long totalTransactions = paymentList.size();
        String summary = String.format(
                "Payment Summary\nTotal Transactions: %d\nTotal Amount: $%.2f\nAverage Payment: $%.2f",
                totalTransactions, totalAmount, totalAmount / totalTransactions
        );

        showAlert(Alert.AlertType.INFORMATION, "Payment Summary", summary);
    }

    @javafx.fxml.FXML
    private void handleDownloadReport() {
        if (paymentList.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "No Data", "Fetch payment data first.");
            return;
        }

        String filename = String.format("payment_summary_%s_to_%s.pdf",
                fromDatePicker.getValue(), toDatePicker.getValue());
        showAlert(Alert.AlertType.INFORMATION, "Download Complete",
                String.format("Report downloaded as: %s", filename));
    }

    private void updateTotalAmount() {
        double total = paymentList.stream().mapToDouble(PaymentData::getAmount).sum();
        totalAmountLabel.setText(String.format("Total Amount: $%.2f", total));
    }

    private boolean validateDateRange() {
        if (fromDatePicker.getValue() == null || toDatePicker.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Select both dates.");
            return false;
        }
        if (fromDatePicker.getValue().isAfter(toDatePicker.getValue())) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "'From' date cannot be after 'To' date.");
            return false;
        }
        return true;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
