package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class ApproveDiscountAdjustmentsController implements Initializable {

    @FXML private TableView<DiscountRequest> discountRequestsTable;
    @FXML private TableColumn<DiscountRequest, String> requestIdColumn;
    @FXML private TableColumn<DiscountRequest, String> customerIdColumn;
    @FXML private TableColumn<DiscountRequest, String> discountTypeColumn;
    @FXML private TableColumn<DiscountRequest, Double> discountAmountColumn;
    @FXML private TableColumn<DiscountRequest, String> statusColumn;
    @FXML private Label selectedRequestLabel;
    @FXML private TextField commentsField;
    @FXML private TextArea confirmationArea;

    private ObservableList<DiscountRequest> discountRequestsList;
    private DiscountRequest selectedRequest;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        requestIdColumn.setCellValueFactory(new PropertyValueFactory<>("requestId"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        discountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("discountType"));
        discountAmountColumn.setCellValueFactory(new PropertyValueFactory<>("discountAmount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        discountRequestsList = FXCollections.observableArrayList();
        discountRequestsTable.setItems(discountRequestsList);

        discountRequestsTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            selectedRequest = newSel;
            selectedRequestLabel.setText(newSel != null ?
                    String.format("%s - %s - $%.2f", newSel.getRequestId(), newSel.getCustomerId(), newSel.getDiscountAmount()) :
                    "None selected");
        });
    }

    @FXML
    private void handleViewDiscountRequests() {
        discountRequestsList.setAll(
                new DiscountRequest("DR001", "CUST001", "Senior Citizen", 15.00, "Pending", "Customer is over 65"),
                new DiscountRequest("DR002", "CUST002", "Low Income", 25.50, "Pending", "Low-income assistance"),
                new DiscountRequest("DR003", "CUST003", "Loyalty Discount", 10.00, "Pending", "10+ years with us")
        );
    }

    @FXML
    private void handleApproveAdjustment() {
        if (selectedRequest == null || commentsField.getText().trim().isEmpty()) return;
        selectedRequest.setStatus("Approved");
        discountRequestsTable.refresh();
        confirmationArea.setText("Approved: " + selectedRequest.getRequestId() + " - Comments: " + commentsField.getText());
    }

    @FXML
    private void handleSubmitApproval() {
        if (selectedRequest == null || !"Approved".equals(selectedRequest.getStatus())) return;
        selectedRequest.setStatus("Submitted");
        discountRequestsTable.refresh();
        confirmationArea.setText("Submitted: " + selectedRequest.getRequestId() + " - Discount: $" + selectedRequest.getDiscountAmount());
        commentsField.clear();
        selectedRequest = null;
        selectedRequestLabel.setText("None selected");
        discountRequestsTable.getSelectionModel().clearSelection();
    }
}
