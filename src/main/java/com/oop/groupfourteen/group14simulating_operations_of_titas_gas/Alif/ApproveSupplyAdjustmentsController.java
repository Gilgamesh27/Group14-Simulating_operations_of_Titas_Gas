package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class ApproveSupplyAdjustmentsController implements Initializable {

    @FXML private TableView<AdjustmentRequest> adjustmentRequestsTable;
    @FXML private TableColumn<AdjustmentRequest, String> requestIdColumn;
    @FXML private TableColumn<AdjustmentRequest, String> zoneColumn;
    @FXML private TableColumn<AdjustmentRequest, Double> currentRateColumn;
    @FXML private TableColumn<AdjustmentRequest, Double> requestedRateColumn;
    @FXML private TableColumn<AdjustmentRequest, String> reasonColumn;
    @FXML private TableColumn<AdjustmentRequest, String> statusColumn;
    @FXML private Label selectedRequestLabel;
    @FXML private TextField commentsField;
    @FXML private TextArea confirmationArea;

    private ObservableList<AdjustmentRequest> requestsList;
    private AdjustmentRequest selectedRequest;

    public static class AdjustmentRequest {
        private String requestId, zone, reason, status;
        private Double currentRate, requestedRate;

        public AdjustmentRequest(String requestId, String zone, Double currentRate, Double requestedRate, String reason, String status) {
            this.requestId = requestId; this.zone = zone; this.currentRate = currentRate;
            this.requestedRate = requestedRate; this.reason = reason; this.status = status;
        }

        public String getRequestId() { return requestId; }
        public String getZone() { return zone; }
        public Double getCurrentRate() { return currentRate; }
        public Double getRequestedRate() { return requestedRate; }
        public String getReason() { return reason; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        zoneColumn.setCellValueFactory(new PropertyValueFactory<>("zone"));
        currentRateColumn.setCellValueFactory(new PropertyValueFactory<>("currentRate"));
        requestedRateColumn.setCellValueFactory(new PropertyValueFactory<>("requestedRate"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        requestsList = FXCollections.observableArrayList();
        adjustmentRequestsTable.setItems(requestsList);

        adjustmentRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            selectedRequest = newSel;
            selectedRequestLabel.setText(newSel != null ? newSel.getRequestId() + " - " + newSel.getZone() : "None selected");
        });
    }

    @FXML
    private void handleViewAdjustment() {
        requestsList.setAll(
                new AdjustmentRequest("ADJ001","Zone A",2500.0,2800.0,"Demand","Pending"),
                new AdjustmentRequest("ADJ002","Zone B",1800.0,1600.0,"Maintenance","Pending"),
                new AdjustmentRequest("ADJ003","Zone C",3200.0,3500.0,"Peak","Pending")
        );
    }

    @FXML
    private void handleApproveChanges() {
        if (selectedRequest == null || commentsField.getText().trim().isEmpty()) return;
        selectedRequest.setStatus("Approved");
        adjustmentRequestsTable.refresh();
        confirmationArea.setText("Approved: " + selectedRequest.getRequestId() + " - Comments: " + commentsField.getText());
    }

    @FXML
    private void handleSubmitApproval() {
        if (selectedRequest == null || !"Approved".equals(selectedRequest.getStatus())) return;
        selectedRequest.setStatus("Submitted");
        adjustmentRequestsTable.refresh();
        confirmationArea.setText("Submitted: " + selectedRequest.getRequestId() + " - New Rate: " + selectedRequest.getRequestedRate());
    }
}
