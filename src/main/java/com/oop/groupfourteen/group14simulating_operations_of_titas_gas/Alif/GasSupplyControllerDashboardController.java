package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;

public class GasSupplyControllerDashboardController {
    @FXML
    private Label userInfoLabel;
    @FXML
    private Button signoutButton;

    @FXML
    public void initialize() {
        userInfoLabel.setText("Welcome, Gas Supply Controller");
    }

    public void setUserInfo(String userRole) {
        userInfoLabel.setText("Welcome, " + userRole);
    }

    @FXML
    public void handleSignOut() {

            redirectToLogin();
        }


    private void redirectToLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Login.fxml"));
            Parent loginRoot = loader.load();
            Stage currentStage = (Stage) signoutButton.getScene().getWindow();
            Stage loginStage = new Stage();
            loginStage.setTitle("Titas Gas Login");
            loginStage.setScene(new Scene(loginRoot, 450, 400));
            loginStage.setResizable(false);
            currentStage.close();
            loginStage.show();
        } catch (IOException e) {
            showErrorDialog("Error", "Failed to return to login: " + e.getMessage());
        }
    }


    @FXML public void openScheduleGasSupply() { openFxmlFile("ScheduleGasSupply.fxml", "Schedule Gas Supply"); }
    @FXML public void openManagePeakDemand() { openFxmlFile("ManagePeakDemand.fxml", "Manage Peak Demand"); }
    @FXML public void openAdjustSupplyEmergency() { openFxmlFile("AdjustSupplyEmergency.fxml", "Adjust Supply Emergency"); }
    @FXML public void openViewUsageStatistics() { openFxmlFile("ViewUsageStatistics.fxml", "View Usage Statistics"); }
    @FXML public void openManageGasPressureLevels() { openFxmlFile("ManageGasPressureLevels.fxml", "Manage Gas Pressure Levels"); }
    @FXML public void openApproveSupplyAdjustments() { openFxmlFile("ApproveSupplyAdjustments.fxml", "Approve Supply Adjustments"); }
    @FXML public void openSuspendGasSupply() { openFxmlFile("SuspendGasSupply.fxml", "Suspend Gas Supply"); }
    @FXML public void openGenerateGasSupplyReports() { openFxmlFile("GenerateGasSupplyReports.fxml", "Generate Gas Supply Reports"); }

    private void openFxmlFile(String fxmlFileName, String title) {
        try {
            String resourcePath = "/com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resourcePath));
            loader.setController(null);
            Parent root = loader.load();
            Stage currentStage = (Stage) signoutButton.getScene().getWindow();
            Stage operationStage = new Stage();
            operationStage.setTitle("Titas Gas - " + title);
            operationStage.setScene(new Scene(root, 800, 600));
            operationStage.initModality(Modality.WINDOW_MODAL);
            operationStage.initOwner(currentStage);
            operationStage.show();
        } catch (Exception e) {
            showErrorDialog("Error", "Failed to load FXML: " + e.getMessage());
        }
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
