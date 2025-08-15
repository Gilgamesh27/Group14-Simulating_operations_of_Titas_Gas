package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ScheduleMaintenanceOperationsController
{
    @FXML
    private TextField crewField;
    @FXML
    private Label maintenanceOutputLabel;
    @FXML
    private TextField scheduleField;

    @FXML
    public void initialize() {
    }

    @FXML
    public void generatePlanButton(ActionEvent actionEvent) {
    }

    @FXML
    public void logTaskButton(ActionEvent actionEvent) {


    }

    @FXML
    public void notifyTeamButton(ActionEvent actionEvent) {

        String scheduledTask;
        scheduledTask = scheduleField.getText();
        String assignedCrew = crewField.getText();

        if (scheduledTask.isEmpty() || assignedCrew.isEmpty()) {
            maintenanceOutputLabel.setText("Please enter both schedule and crew.");
        } else {
            maintenanceOutputLabel.setText("Team " + assignedCrew + " notified for: " + scheduledTask);
        }

        scheduledTask = scheduleField.getText();
        assignedCrew = crewField.getText();

        if (scheduledTask.isEmpty() || assignedCrew.isEmpty()) {
            maintenanceOutputLabel.setText("Please enter both schedule and crew.");
        } else {
            maintenanceOutputLabel.setText("Team " + assignedCrew + " notified for: " + scheduledTask);
        }
    }
}