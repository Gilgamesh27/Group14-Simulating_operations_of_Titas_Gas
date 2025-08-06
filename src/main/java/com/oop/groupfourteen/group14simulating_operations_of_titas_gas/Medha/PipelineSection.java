package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

public class PipelineSection {

    private final String zoneId;
    private String status;
    private boolean isFlagged;
    private final double sensorReading;
    private final double inspectionReading;

    public PipelineSection(String zoneId, String status, double sensorReading, double inspectionReading) {
        this.zoneId = zoneId;
        this.status = status;
        this.sensorReading = sensorReading;
        this.inspectionReading = inspectionReading;
        this.isFlagged = false;
    }

    public String getZoneId() {
        return zoneId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public double getSensorReading() {
        return sensorReading;
    }

    public double getInspectionReading() {
        return inspectionReading;
    }

    public void flagAsDamaged() {
        this.status = "Damaged";
        this.isFlagged = true;
    }

    public boolean isSensorValidated() {
        return Math.abs(sensorReading - inspectionReading) < 2.0;
    }

    public String getFormattedInfo() {
        return "Zone: " + zoneId + "\nStatus: " + status + "\nSensor: " + sensorReading + " PSI\nInspection: " + inspectionReading + " PSI";
    }
}

