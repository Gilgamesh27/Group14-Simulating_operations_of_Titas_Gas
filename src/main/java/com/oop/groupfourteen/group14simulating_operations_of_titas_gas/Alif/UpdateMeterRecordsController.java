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

public class UpdateMeterRecordsController implements Initializable {

    @FXML private ComboBox<String> meterIdComboBox;
    @FXML private TextField meterTypeField;
    @FXML private TextArea confirmationArea;
    @FXML private Button updateButton;
    @FXML private Button clearButton;

    private Map<String, Meter> meterDatabase;
    private Meter currentMeter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        meterDatabase = new HashMap<>();
        meterDatabase.put("MTR001", new Meter("MTR001", "Digital Smart Meter", LocalDate.of(2020,3,15), "Residential Block A"));
        meterDatabase.put("MTR002", new Meter("MTR002", "Analog Standard Meter", LocalDate.of(2018,7,22), "Commercial Plaza B"));
        meterDatabase.put("MTR003", new Meter("MTR003", "Digital Industrial Meter", LocalDate.of(2019,11,8), "Industrial Zone C"));


        ObservableList<String> meterIds = FXCollections.observableArrayList(meterDatabase.keySet());
        meterIdComboBox.setItems(meterIds);


        meterIdComboBox.setOnAction(e -> loadMeterInfo());
    }

    private void loadMeterInfo() {
        String selectedId = meterIdComboBox.getValue();
        if (selectedId != null) {
            currentMeter = meterDatabase.get(selectedId);
            meterTypeField.setText(currentMeter.getMeterType());

            String info = String.format(
                    "Meter ID: %s\nType: %s\nLocation: %s\nStatus: %s\nCurrent Reading: %.2f m³",
                    currentMeter.getMeterId(),
                    currentMeter.getMeterType(),
                    currentMeter.getLocation(),
                    currentMeter.getStatus(),
                    currentMeter.getCurrentReading()
            );
            confirmationArea.setText(info);
        }
    }

    @FXML
    private void handleUpdateRecord() {
        if (currentMeter != null) {
            String newType = meterTypeField.getText().trim();
            if (!newType.isEmpty()) {
                currentMeter.setMeterType(newType);
                confirmationArea.setText("Meter updated:\n" + currentMeter.toString());
            } else {
                showAlert(Alert.AlertType.ERROR, "Validation Error", "Meter type cannot be empty.");
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Meter Selected", "Please select a meter first.");
        }
    }

    @FXML
    private void handleClear() {
        meterIdComboBox.setValue(null);
        meterTypeField.clear();
        confirmationArea.clear();
        currentMeter = null;
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
