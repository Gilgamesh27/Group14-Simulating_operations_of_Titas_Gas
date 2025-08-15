package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Random;

public class ManagePeakDemandController implements Initializable {

    @javafx.fxml.FXML
    private Label currentDemandLabel;
    @javafx.fxml.FXML
    private TextField newSupplyRateField;
    @javafx.fxml.FXML
    private Button monitorButton;
    @javafx.fxml.FXML
    private Button adjustButton;
    @javafx.fxml.FXML
    private Button submitButton;
    @javafx.fxml.FXML
    private TextArea demandMonitorArea;
    @javafx.fxml.FXML
    private TextArea flowUpdateArea;

    private double currentDemand;
    private Random random = new Random();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        currentDemand = 2500 + random.nextDouble() * 1000;
        currentDemandLabel.setText(String.format("%.2f m³/hr", currentDemand));
    }

    @javafx.fxml.FXML
    private void handleMonitorDemand() {
        currentDemand = 2000 + random.nextDouble() * 2000;
        currentDemandLabel.setText(String.format("%.2f m³/hr", currentDemand));
        demandMonitorArea.setText("Demand monitoring completed.");
    }

    @javafx.fxml.FXML
    private void handleAdjustSupplyRate() {
        if (!newSupplyRateField.getText().trim().isEmpty()) {
            flowUpdateArea.setText("Supply rate adjustment ready.");
        }
    }

    @javafx.fxml.FXML
    private void handleSubmitAdjustment() {
        if (!flowUpdateArea.getText().isEmpty()) {
            flowUpdateArea.setText("Supply rate adjustment submitted.");
        }
    }
}
