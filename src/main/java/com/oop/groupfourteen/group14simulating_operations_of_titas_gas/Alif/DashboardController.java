package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class DashboardController {

    @FXML
    private StackPane contentPane;

    public void loadScene(String fxmlFilePath) {
        try {
            Node content = FXMLLoader.load(getClass().getResource(fxmlFilePath));
            contentPane.getChildren().setAll(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onOption1Click() {
        loadScene("/com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/GasSupplyDashboard.fxml");
    }

    @FXML
    public void onOption2Click() {
        loadScene("/com/oop/groupfourteen/group14simulating_operations_of_titas_gas/Alif/BillingandMeteringOfficerDashboard.fxml");
    }
}