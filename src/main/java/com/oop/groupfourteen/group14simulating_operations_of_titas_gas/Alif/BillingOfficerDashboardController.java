package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class BillingOfficerDashboardController {

    @FXML private Label userInfoLabel;
    @FXML private Button signoutButton;

    @FXML
    public void initialize() {
        userInfoLabel.setText("Welcome, Billing and Metering Officer");
    }

    @FXML
    public void handleSignOut() {
            redirectToLogin();
        }


    private void redirectToLogin() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "/com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Login.fxml"));
            Stage stage = (Stage) signoutButton.getScene().getWindow();
            stage.setScene(new Scene(root, 450, 400));
            stage.setResizable(false);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to return to login: " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }

    private void openFxml(String fxmlFile, String title) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(
                    "com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/" + fxmlFile));
            Stage stage = (Stage) signoutButton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Titas Gas - " + title);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load " + fxmlFile + ": " + e.getMessage(), ButtonType.OK).showAndWait();
        }
    }


    @FXML public void openRecordMeterReadings() { openFxml("RecordMeterReadings.fxml", "Record Meter Readings"); }
    @FXML public void openGenerateMonthlyBills() { openFxml("GenerateMonthlyBills.fxml", "Generate Monthly Bills"); }
    @FXML public void openHandleBillingErrors() { openFxml("HandleBillingErrors.fxml", "Handle Billing Errors"); }
    @FXML public void openUpdateMeterRecords() { openFxml("UpdateMeterRecords.fxml", "Update Meter Records"); }
    @FXML public void openGeneratePaymentSummary() { openFxml("GeneratePaymentSummary.fxml", "Generate Payment Summary"); }
    @FXML public void openProcessRefunds() { openFxml("ProcessRefunds.fxml", "Process Refunds"); }
    @FXML public void openApproveDiscountAdjustments() { openFxml("ApproveDiscountAdjustments.fxml", "Approve Discount Adjustments"); }
    @FXML public void openIssueFinalBill() { openFxml("IssueFinalBill.fxml", "Issue Final Bill"); }
}
