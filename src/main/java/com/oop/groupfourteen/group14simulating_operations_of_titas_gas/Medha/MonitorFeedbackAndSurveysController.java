package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class MonitorFeedbackAndSurveysController
{
    @javafx.fxml.FXML
    private ComboBox feedbackCategoryCombo;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {

        feedbackCategoryCombo.getItems().addAll("Positive", "Negative");
    }

    @javafx.fxml.FXML
    public void archiveReportButton(ActionEvent actionEvent) {

        statusLabel.setText("Survey report archived.");
    }

    @javafx.fxml.FXML
    public void accessFeedbackButton(ActionEvent actionEvent) {

        statusLabel.setText("Customer feedback accessed.");
    }

    @javafx.fxml.FXML
    public void validateComplaintsButton(ActionEvent actionEvent) {

        statusLabel.setText("Complaints validated.");
    }

    @javafx.fxml.FXML
    public void recordInsightsButton(ActionEvent actionEvent) {

        statusLabel.setText("Insights recorded.");
    }
}