package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GenerateMonthlyBillsController implements Initializable {

    @javafx.fxml.FXML private ComboBox<String> billingCycleComboBox;
    @javafx.fxml.FXML private DatePicker monthYearPicker;
    @javafx.fxml.FXML private Button calculateButton;
    @javafx.fxml.FXML private Button reviewButton;
    @javafx.fxml.FXML private Button confirmButton;
    @javafx.fxml.FXML private TableView<Bill> billsTableView;
    @javafx.fxml.FXML private TableColumn<Bill, String> customerColumn;
    @javafx.fxml.FXML private TableColumn<Bill, Double> amountColumn;
    @javafx.fxml.FXML private TableColumn<Bill, String> statusColumn;

    private ObservableList<Bill> billsList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        billingCycleComboBox.setItems(FXCollections.observableArrayList(
                "Monthly - Residential",
                "Monthly - Commercial",
                "Bi-Monthly - Industrial",
                "Quarterly - Government"
        ));
        monthYearPicker.setValue(LocalDate.now());

        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        billsList = FXCollections.observableArrayList();
        billsTableView.setItems(billsList);
    }

    @javafx.fxml.FXML
    private void handleCalculateBills() {
        if (billingCycleComboBox.getValue() == null || monthYearPicker.getValue() == null) return;

        billsList.clear();
        String[] customerIds = {"CUST001", "CUST002", "CUST003", "CUST004", "CUST005"};
        double[] consumptions = {50.2, 35.9, 93.7, 62.7, 79.4};
        double rate = 2.50;

        LocalDate selectedMonth = monthYearPicker.getValue();
        String billingPeriod = selectedMonth.getMonth().name() + " " + selectedMonth.getYear();
        String dueDate = selectedMonth.plusMonths(1).withDayOfMonth(15).toString(); // Example: 15th of next month

        for (int i = 0; i < customerIds.length; i++) {
            double previousReading = 0.0; // Placeholder
            double currentReading = consumptions[i]; // Assuming consumption is the current reading for simplicity
            double totalAmount = consumptions[i] * rate;
            billsList.add(new Bill(customerIds[i], previousReading, currentReading, consumptions[i], rate, totalAmount, billingPeriod, dueDate));
        }
        billsTableView.refresh();
    }

    @javafx.fxml.FXML
    private void handleReviewBills() {
        for (Bill bill : billsList) {
            if ("Pending".equals(bill.getStatus())) bill.setStatus("Reviewed");
        }
        billsTableView.refresh();
    }

    @javafx.fxml.FXML
    private void handleConfirmGeneration() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        for (Bill bill : billsList) {
            if ("Reviewed".equals(bill.getStatus())) {
                bill.setStatus("Generated");
                PDFGenerator.generateMonthlyBillPdf(stage, bill);
            }
        }
        billsTableView.refresh();
    }
}
