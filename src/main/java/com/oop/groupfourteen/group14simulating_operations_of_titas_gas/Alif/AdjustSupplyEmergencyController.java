package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class AdjustSupplyEmergencyController implements Initializable {

    @FXML private ComboBox<Zone> affectedZoneComboBox;
    @FXML private TextField emergencyRateField;
    @FXML private ComboBox<String> emergencyTypeComboBox;
    @FXML private Button setRateButton;
    @FXML private Button submitButton;
    @FXML private Button resetButton;
    @FXML private TextArea updateArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Zone> zones = FXCollections.observableArrayList(
                new Zone("ZONE001", "Zone A - Residential North"),
                new Zone("ZONE002", "Zone B - Residential South"),
                new Zone("ZONE003", "Zone C - Commercial District"),
                new Zone("ZONE004", "Zone D - Industrial Area"),
                new Zone("ZONE005", "Zone E - Mixed Development"),
                new Zone("ZONE006", "Zone F - Government Buildings")
        );
        affectedZoneComboBox.setItems(zones);

        emergencyTypeComboBox.setItems(FXCollections.observableArrayList(
                "Gas Leak", "Pipeline Rupture", "Equipment Failure",
                "Natural Disaster", "Maintenance Emergency", "Safety Incident"
        ));

        // Allow only numbers
        emergencyRateField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) emergencyRateField.setText(oldVal);
        });
    }

    @FXML
    private void handleSetEmergencyRate() {
        Zone zone = affectedZoneComboBox.getValue();
        String type = emergencyTypeComboBox.getValue();
        String rateText = emergencyRateField.getText().trim();

        if (zone == null) { showAlert("Select a zone."); return; }
        if (type == null) { showAlert("Select an emergency type."); return; }
        if (rateText.isEmpty()) { showAlert("Enter emergency rate."); return; }

        double rate;
        try { rate = Double.parseDouble(rateText); }
        catch (NumberFormatException e) { showAlert("Enter valid number."); return; }
        if (rate <= 0) { showAlert("Rate must be > 0."); return; }

        updateArea.setText(String.format(
                "EMERGENCY RATE SET\n\nZone: %s\nType: %s\nRate: %.2f m³/hr\nTime: %s\nStatus: Ready for submission",
                zone.getName(), type, rate, java.time.LocalDateTime.now()
        ));
    }

    @FXML
    private void handleSubmitChanges() {
        if (updateArea.getText().isEmpty() || !updateArea.getText().contains("Ready for submission")) {
            showAlert("Set the emergency rate first."); return;
        }

        Zone zone = affectedZoneComboBox.getValue();
        String type = emergencyTypeComboBox.getValue();
        String rate = emergencyRateField.getText();

        updateArea.setText(String.format(
                "EMERGENCY SUBMITTED\n\nZone: %s\nType: %s\nRate: %s m³/hr\nSubmission Time: %s\nStatus: ACTIVE\nEmergency ID: EM-%d",
                zone.getName(), type, rate, java.time.LocalDateTime.now(), System.currentTimeMillis() % 10000
        ));
    }

    @FXML
    private void handleReset() {
        affectedZoneComboBox.setValue(null);
        emergencyTypeComboBox.setValue(null);
        emergencyRateField.clear();
        updateArea.clear();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
