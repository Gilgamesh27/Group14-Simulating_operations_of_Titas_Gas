package com.oop.groupfourteen.group14simulating_operations_of_titas_gas;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoginController
{
    @javafx.fxml.FXML
    private Label infoLabel;
    @javafx.fxml.FXML
    private TextField userIdField;
    @javafx.fxml.FXML
    private Button loginButton;
    @javafx.fxml.FXML
    private PasswordField passwordField;
    @javafx.fxml.FXML
    public void initialize() {
        clearMessage();
    }

    private final List<User> systemUsers = new ArrayList<>();

    public LoginController() {
        systemUsers.add(new User("123456", "Billing@123", "Billing and Metering Officer", "com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/BillingOfficerDashboard.fxml", "Titas Gas - Billing and Metering Officer Dashboard"));
        systemUsers.add(new User("789012", "GasSupply@123", "Gas Supply Controller", "com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/GasSupplyControllerDashboard.fxml", "Titas Gas - Gas Supply Controller Dashboard"));
    }


    private void clearMessage() {
        infoLabel.setText("");
        infoLabel.setStyle("");
    }

    @javafx.fxml.FXML
    public void handleLogin(ActionEvent actionEvent) {String userId = userIdField.getText().trim();
        String password = passwordField.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            displayError("Error: User ID and Password cannot be empty");
            return;
        }
        User user = chker(userId, password);
        if (user != null) {
            openUserDashboard(user);
        } else {
            displayError("Error: Invalid User ID or Password");
        }
    }

    @javafx.fxml.FXML
    public void handleCreateAccount(ActionEvent actionEvent) {
        infoLabel.setText("Please contact your HR department to create a new account.");

    }
    @javafx.fxml.FXML
    public void forgotPasswordButton(ActionEvent actionEvent) {
        infoLabel.setText("Please contact your system administrator to reset your password.");

    }
    private void displayError(String msg) {

        infoLabel.setStyle("-fx-text-fill: red;");
    }
    private void openUserDashboard(User user) {
        try {
            URL resource = getClass().getResource("/" + user.getDashboardFile());
            if (resource == null) {
                displayError("FXML file not found: " + user.getDashboardFile());
                return;
            }
            Parent root = FXMLLoader.load(resource);
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            Stage dashboard = new Stage();
            dashboard.setTitle(user.getWindowTitle());
            dashboard.setScene(new Scene(root));

            currentStage.close();
            dashboard.show();

        } catch (IOException e) {
            e.printStackTrace();
            displayError("Could not load dashboard: " + e.getMessage());
        }
    }
    private User chker(String userId, String password) {
        for (User user : systemUsers) {
            if (user.getUserId().equals(userId) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

}