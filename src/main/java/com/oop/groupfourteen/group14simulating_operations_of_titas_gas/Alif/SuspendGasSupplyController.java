package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.net.URL;
import java.util.ResourceBundle;

public class SuspendGasSupplyController implements Initializable {

    @FXML private ComboBox<String> zoneComboBox;
    @FXML private ComboBox<String> emergencyTypeComboBox;
    @FXML private TextField suspensionReasonField;
    @FXML private TextArea emergencyDetailsArea;
    @FXML private TableView<TeamData> teamsTable;
    @FXML private TableColumn<TeamData, String> teamIdColumn;
    @FXML private TableColumn<TeamData, String> teamNameColumn;
    @FXML private TableColumn<TeamData, String> contactColumn;
    @FXML private TableColumn<TeamData, String> notificationStatusColumn;

    private ObservableList<TeamData> teamsList;

    public static class TeamData {
        private final String teamId;
        private final String teamName;
        private final String contact;
        private String notificationStatus;

        public TeamData(String teamId, String teamName, String contact, String notificationStatus) {
            this.teamId = teamId;
            this.teamName = teamName;
            this.contact = contact;
            this.notificationStatus = notificationStatus;
        }

        public String getTeamId() { return teamId; }
        public String getTeamName() { return teamName; }
        public String getContact() { return contact; }
        public String getNotificationStatus() { return notificationStatus; }
        public void setNotificationStatus(String status) { this.notificationStatus = status; }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        zoneComboBox.setItems(FXCollections.observableArrayList("Zone A", "Zone B", "Zone C", "Zone D"));
        emergencyTypeComboBox.setItems(FXCollections.observableArrayList("Gas Leak", "Pipeline Rupture", "Equipment Failure"));

        teamIdColumn.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        teamNameColumn.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        contactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        notificationStatusColumn.setCellValueFactory(new PropertyValueFactory<>("notificationStatus"));

        teamsList = FXCollections.observableArrayList(
                new TeamData("T001", "Emergency Response", "555-0101", "Pending"),
                new TeamData("T002", "Maintenance Team", "555-0102", "Pending")
        );
        teamsTable.setItems(teamsList);
    }

    @FXML
    private void handleConfirmSuspension() {
        if (!validateInputs()) return;
        emergencyDetailsArea.setText("Suspension confirmed for " + zoneComboBox.getValue());
    }

    @FXML
    private void handleSubmitSuspension() {
        if (emergencyDetailsArea.getText().isEmpty()) return;
        emergencyDetailsArea.setText("Gas supply suspended in " + zoneComboBox.getValue());
    }

    @FXML
    private void handleNotifyTeams() {
        for (TeamData team : teamsList) team.setNotificationStatus("Notified");
        teamsTable.refresh();
    }

    @FXML
    private void handleCancel() {
        zoneComboBox.setValue(null);
        emergencyTypeComboBox.setValue(null);
        suspensionReasonField.clear();
        emergencyDetailsArea.clear();
        for (TeamData team : teamsList) team.setNotificationStatus("Pending");
        teamsTable.refresh();
    }

    private boolean validateInputs() {
        return zoneComboBox.getValue() != null && emergencyTypeComboBox.getValue() != null
                && !suspensionReasonField.getText().trim().isEmpty();
    }
}
