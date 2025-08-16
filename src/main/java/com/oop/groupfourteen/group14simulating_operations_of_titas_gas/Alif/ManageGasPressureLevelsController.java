package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageGasPressureLevelsController implements Initializable {

    @FXML private ComboBox<String> pipelineComboBox;
    @FXML private Label currentPressureLabel;
    @FXML private TextField pressureLevelField;
    @FXML private Button setPressureButton;
    @FXML private Button submitButton;
    @FXML private Button resetButton;
    @FXML private TableView<PipelineData> pipelineStatusTable;
    @FXML private TableColumn<PipelineData, String> pipelineIdColumn;
    @FXML private TableColumn<PipelineData, Double> currentPressureColumn;
    @FXML private TableColumn<PipelineData, Double> targetPressureColumn;
    @FXML private TableColumn<PipelineData, String> statusColumn;
    @FXML private TextArea confirmationArea;

    private ObservableList<PipelineData> pipelineList;

    public static class PipelineData {
        private String pipelineId;
        private Double currentPressure;
        private Double targetPressure;
        private String status;

        public PipelineData(String pipelineId, Double currentPressure, Double targetPressure, String status) {
            this.pipelineId = pipelineId;
            this.currentPressure = currentPressure;
            this.targetPressure = targetPressure;
            this.status = status;
        }

        public String getPipelineId() { return pipelineId; }
        public Double getCurrentPressure() { return currentPressure; }
        public Double getTargetPressure() { return targetPressure; }
        public String getStatus() { return status; }
        public void setTargetPressure(Double targetPressure) { this.targetPressure = targetPressure; }
        public void setStatus(String status) { this.status = status; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Pipelines for ComboBox
        ObservableList<String> pipelines = FXCollections.observableArrayList(
                "Pipeline A", "Pipeline B", "Pipeline C", "Pipeline D"
        );
        pipelineComboBox.setItems(pipelines);
        pipelineComboBox.setOnAction(e -> updateCurrentPressure());

        // Table setup
        pipelineIdColumn.setCellValueFactory(new PropertyValueFactory<>("pipelineId"));
        currentPressureColumn.setCellValueFactory(new PropertyValueFactory<>("currentPressure"));
        targetPressureColumn.setCellValueFactory(new PropertyValueFactory<>("targetPressure"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // Minimal pipeline data
        pipelineList = FXCollections.observableArrayList(
                new PipelineData("Pipeline A", 45.2, 45.0, "Normal"),
                new PipelineData("Pipeline B", 38.7, 40.0, "Adjusting"),
                new PipelineData("Pipeline C", 52.1, 50.0, "Normal"),
                new PipelineData("Pipeline D", 35.5, 35.0, "Normal")
        );
        pipelineStatusTable.setItems(pipelineList);

        // Allow only numbers in pressure field
        pressureLevelField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*\\.?\\d*")) pressureLevelField.setText(oldVal);
        });
    }

    private void updateCurrentPressure() {
        String selected = pipelineComboBox.getValue();
        if (selected != null) {
            double pressure = 35 + Math.random() * 20;
            currentPressureLabel.setText(String.format("%.1f PSI", pressure));
        }
    }

    @FXML
    private void handleSetPressureLevel() {
        if (pipelineComboBox.getValue() == null || pressureLevelField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Error", "Select a pipeline and enter pressure.");
            return;
        }
        confirmationArea.setText(
                "Pipeline: " + pipelineComboBox.getValue() +
                        "\nNew Pressure: " + pressureLevelField.getText() +
                        " PSI\nStatus: Ready for submission"
        );
    }

    @FXML
    private void handleSubmit() {
        if (confirmationArea.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Warning", "Set a pressure level first.");
            return;
        }
        confirmationArea.appendText("\n\nPressure update CONFIRMED!");
        pipelineStatusTable.refresh(); // keep table visible and up-to-date
        showAlert(Alert.AlertType.INFORMATION, "Submitted", "Pressure updated successfully.");
    }

    @FXML
    private void handleReset() {
        pipelineComboBox.setValue(null);
        currentPressureLabel.setText("");
        pressureLevelField.clear();
        confirmationArea.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String msg) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
