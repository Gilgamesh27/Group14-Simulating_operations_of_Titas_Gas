package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ScheduleGasSupplyController implements Initializable {

    @FXML private ComboBox<String> zoneComboBox;
    @FXML private TextField supplyTimeField;
    @FXML private DatePicker supplyDatePicker;
    @FXML private TextField supplyRateField;
    @FXML private TextArea confirmationArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zoneComboBox.setItems(FXCollections.observableArrayList(
                "Zone A", "Zone B", "Zone C", "Zone D"
        ));
        supplyDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleInputSchedule() {
        if (zoneComboBox.getValue() == null || supplyTimeField.getText().isEmpty() || supplyRateField.getText().isEmpty()) return;
        confirmationArea.setText(
                "Schedule Preview:\nZone: " + zoneComboBox.getValue() +
                        "\nDate: " + supplyDatePicker.getValue() +
                        "\nTime: " + supplyTimeField.getText() +
                        "\nRate: " + supplyRateField.getText() + " m³/hr"
        );
    }

    @FXML
    private void handleSubmitSchedule() {
        if (confirmationArea.getText().isEmpty()) return;
        confirmationArea.setText("Schedule Submitted for Zone: " + zoneComboBox.getValue());
    }

    @FXML
    private void handleClear() {
        zoneComboBox.setValue(null);
        supplyTimeField.clear();
        supplyDatePicker.setValue(LocalDate.now());
        supplyRateField.clear();
        confirmationArea.clear();
    }
}
