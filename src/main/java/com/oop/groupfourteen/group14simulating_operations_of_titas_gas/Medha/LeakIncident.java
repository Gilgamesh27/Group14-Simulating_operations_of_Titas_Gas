package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

import java.time.LocalDateTime;

public class LeakIncident {

    private final String zoneId;
    private final boolean sensorDetectedLeak;
    private boolean manualConfirmed;
    private boolean isShutdown;
    private LocalDateTime shutdownTime;
    private boolean incidentClosed;

    public LeakIncident(String zoneId, boolean sensorDetectedLeak) {
        this.zoneId = zoneId;
        this.sensorDetectedLeak = sensorDetectedLeak;
        this.manualConfirmed = false;
        this.isShutdown = false;
        this.incidentClosed = false;
    }

    public void confirmManual() {
        this.manualConfirmed = true;
    }

    public void shutDownZone() {
        if (sensorDetectedLeak && manualConfirmed) {
            this.isShutdown = true;
            this.shutdownTime = LocalDateTime.now();
        }
    }

    public void closeIncident() {
        if (isShutdown) {
            this.incidentClosed = true;
        }
    }

    public String getStatusReport() {
        return "Zone: " + zoneId + "\nLeak Detected: " + sensorDetectedLeak +
                "\nManually Confirmed: " + manualConfirmed +
                "\nShutdown: " + isShutdown +
                (shutdownTime != null ? "\nShutdown Time: " + shutdownTime : "") +
                "\nClosed: " + incidentClosed;
    }
}

