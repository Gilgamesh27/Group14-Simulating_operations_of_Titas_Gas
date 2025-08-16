package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import com.sun.javafx.tk.quantum.PaintRenderJob;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ManageComplaintFollowUpController
{
    @FXML
    private TextField remarksField;
    @FXML
    private ListView unresolvedListView;
    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {

        ObservableList<Object> complaints = FXCollections.observableArrayList(
                new Complaint("C001", "John Doe", "Unresolved"),
                new Complaint("C002", "Jane Smith", "Unresolved"),
                new Complaint("C003", "Mike Ross", "Unresolved")
        );
        unresolvedListView.setItems(complaints);
    }

    @FXML
    public void contactCustomerButton(ActionEvent actionEvent) {

        ObservableList<Object> complaints = FXCollections.observableArrayList(
                new Complaint("C001", "John Doe", "Unresolved"),
                new Complaint("C002", "Jane Smith", "Unresolved"),
                new Complaint("C003", "Mike Ross", "Unresolved")
        );
        unresolvedListView.setItems(complaints);
    }

    @FXML
    public <Complaint> void updateTicketButton(ActionEvent actionEvent) {

        Complaint selected = (Complaint) unresolvedListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.getClass();
            saveToFile(selected, "complaints_updates.txt");
            statusLabel.setText("Remarks updated and saved to file.");
        } else {
            statusLabel.setText("Select a complaint to update.");
        }
    }

    private <Complaint> void saveToFile(Complaint selected, String file) {
    }

    @FXML
    public <Complaint> void validateResolutionButton(ActionEvent actionEvent) {

        Complaint selected = (Complaint) unresolvedListView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (!selected.getClass().isEmpty()) {
                selected.setStatus("Resolved");
                statusLabel.setText("Complaint marked as resolved.");
                unresolvedListView.refresh();
            } else {
                statusLabel.setText("Add remarks before validating.");
            }
        } else {
            statusLabel.setText("Select a complaint to validate.");
        }
    }

    @FXML
    public <Complaint, ProcessEnvironment> void backToMenuButton(ActionEvent actionEvent) {

        try {
            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
            PaintRenderJob backToMenuButton;
            Stage stage = (Stage) backToMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <Complaint> void saveToFile(Complaint complaint, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(complaint.getClass() + "," + complaint.getClass() + "," +
                    complaint.getClass() + "," + complaint.getClass());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public <Complaint, ProcessEnvironment> void archiveCaseButton(ActionEvent actionEvent) {

        Complaint selected = (Complaint) unresolvedListView.getSelectionModel().getSelectedItem();
        if (selected != null && "Resolved".equals(selected.getClass())) {
            saveToFile(selected, "archived_complaints.txt");
            ProcessEnvironment complaints = null;
            complaints.remove(selected);
            statusLabel.setText("Complaint archived.");
        } else {
            statusLabel.setText("Only resolved complaints can be archived.");
        }
    }
}