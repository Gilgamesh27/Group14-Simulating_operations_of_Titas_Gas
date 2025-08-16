package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

public class HandleBillingErrorsController implements Initializable {

    @FXML private TextField searchAccountField;
    @FXML private TextField adjustedAmountField;
    @FXML private Button searchButton;
    @FXML private Button adjustButton;
    @FXML private Button submitButton;
    @FXML private TextArea complaintArea;
    @FXML private TextArea confirmationArea;

    private List<AccountInfo> accountList;
    private AccountInfo currentAccount;

    private static class AccountInfo {
        String accountNumber, customerName, complaint;
        double currentBill;

        AccountInfo(String accountNumber, String customerName, double currentBill, String complaint) {
            this.accountNumber = accountNumber;
            this.customerName = customerName;
            this.currentBill = currentBill;
            this.complaint = complaint;
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        accountList = new ArrayList<>();
        accountList.add(new AccountInfo("ACC001","John Doe",125.50,"Meter reading appears incorrect."));
        accountList.add(new AccountInfo("ACC002","Jane Smith",89.75,"Double billing issue."));
        accountList.add(new AccountInfo("ACC003","Mike Johnson",234.20,"High bill dispute."));
        accountList.add(new AccountInfo("ACC004","Sarah Wilson",156.80,"Billing address error."));
        accountList.add(new AccountInfo("ACC005","David Brown",198.45,"Incorrect rate applied."));

        adjustedAmountField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) adjustedAmountField.setText(oldVal);
        });
    }

    @FXML
    private void handleSearchAccounts() {
        String accNum = searchAccountField.getText().trim().toUpperCase();
        if (accNum.isEmpty()) { showAlert(Alert.AlertType.WARNING,"Input Required","Enter account number"); return; }

        currentAccount = null;
        for (AccountInfo acc : accountList) {
            if (acc.accountNumber.equalsIgnoreCase(accNum)) {
                currentAccount = acc;
                break;
            }
        }

        if (currentAccount != null) {
            complaintArea.setText(
                    "Account: " + currentAccount.accountNumber +
                            "\nCustomer: " + currentAccount.customerName +
                            "\nBill: $" + currentAccount.currentBill +
                            "\nComplaint: " + currentAccount.complaint
            );
            adjustedAmountField.setText(String.valueOf(currentAccount.currentBill));
        } else {
            complaintArea.setText("Account not found.");
            adjustedAmountField.clear();
        }
    }

    @FXML
    private void handleAdjustBill() {
        if (currentAccount == null) { showAlert(Alert.AlertType.WARNING,"No Account","Search account first"); return; }
        String text = adjustedAmountField.getText().trim();
        if (text.isEmpty()) { showAlert(Alert.AlertType.WARNING,"Input Required","Enter adjusted amount"); return; }

        try {
            double adjusted = Double.parseDouble(text);
            if (adjusted < 0) { showAlert(Alert.AlertType.ERROR,"Invalid Amount","Cannot be negative"); return; }
            double diff = adjusted - currentAccount.currentBill;
            confirmationArea.setText(
                    "Account: " + currentAccount.accountNumber +
                            "\nOriginal: $" + currentAccount.currentBill +
                            "\nAdjusted: $" + adjusted +
                            "\nDifference: " + (diff >=0 ? "+" : "") + diff +
                            "\nAdjustment ready"
            );
        } catch (NumberFormatException e) { showAlert(Alert.AlertType.ERROR,"Invalid Input","Enter valid number"); }
    }

    @FXML
    private void handleSubmitChanges() {
        if (currentAccount == null) { showAlert(Alert.AlertType.WARNING,"No Account","Search account first"); return; }
        if (confirmationArea.getText().isEmpty() || !confirmationArea.getText().contains("Adjustment ready")) {
            showAlert(Alert.AlertType.WARNING,"No Adjustment","Adjust bill first"); return;
        }

        try {
            double adjusted = Double.parseDouble(adjustedAmountField.getText().trim());
            confirmationArea.setText(
                    "Account: " + currentAccount.accountNumber +
                            "\nCustomer: " + currentAccount.customerName +
                            "\nNew Bill: $" + adjusted +
                            "\nSubmitted at: " + java.time.LocalDateTime.now() +
                            "\nStatus: Changes Applied"
            );
            showAlert(Alert.AlertType.INFORMATION,"Submitted","Changes submitted successfully");
        } catch (NumberFormatException e) { showAlert(Alert.AlertType.ERROR,"Invalid Input","Enter valid number"); }
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
