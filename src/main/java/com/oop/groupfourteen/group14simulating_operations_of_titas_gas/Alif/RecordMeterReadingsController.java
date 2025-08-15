package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class RecordMeterReadingsController implements Initializable {

    @FXML private ComboBox<String> customerComboBox;
    @FXML private TextField meterReadingField;
    @FXML private DatePicker readingDatePicker;
    @FXML private TextArea confirmationArea;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> customerList = FXCollections.observableArrayList(
                "CUST001 - John Doe",
                "CUST002 - Jane Smith",
                "CUST003 - Mike Johnson",
                "CUST004 - Sarah Wilson"
        );
        customerComboBox.setItems(customerList);
        readingDatePicker.setValue(LocalDate.now());
    }

    @FXML
    private void handleSubmitReading() {
        if (customerComboBox.getValue() == null || meterReadingField.getText().isEmpty()) return;

        confirmationArea.setText(
                "Reading Recorded:\nCustomer: " + customerComboBox.getValue() +
                        "\nMeter Reading: " + meterReadingField.getText() +
                        "\nDate: " + readingDatePicker.getValue()
        );
    }

    @FXML
    private void handleClear() {
        customerComboBox.setValue(null);
        meterReadingField.clear();
        readingDatePicker.setValue(LocalDate.now());
        confirmationArea.clear();
    }
}
