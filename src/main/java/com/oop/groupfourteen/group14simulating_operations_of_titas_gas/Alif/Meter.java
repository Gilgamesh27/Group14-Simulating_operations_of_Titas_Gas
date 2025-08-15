package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import java.time.LocalDate;

public class Meter {
    private String meterId;
    private String meterType;
    private LocalDate installationDate;
    private String location;
    private String status;
    private double currentReading;
    private double previousReading;
    
    public Meter(String meterId, String meterType, LocalDate installationDate, String location) {
        this.meterId = meterId;
        this.meterType = meterType;
        this.installationDate = installationDate;
        this.location = location;
        this.status = "Active";
        this.currentReading = 0.0;
        this.previousReading = 0.0;
    }
    
    // Getters
    public String getMeterId() { return meterId; }
    public String getMeterType() { return meterType; }
    public LocalDate getInstallationDate() { return installationDate; }
    public String getLocation() { return location; }
    public String getStatus() { return status; }
    public double getCurrentReading() { return currentReading; }
    public double getPreviousReading() { return previousReading; }
    
    // Setters
    public void setMeterType(String meterType) { this.meterType = meterType; }
    public void setStatus(String status) { this.status = status; }
    
    public void updateReading(double newReading) {
        if (newReading >= this.currentReading) {
            this.previousReading = this.currentReading;
            this.currentReading = newReading;
        } else {
            throw new IllegalArgumentException("New reading cannot be less than current reading");
        }
    }
    
    public double getConsumption() {
        return currentReading - previousReading;
    }
    
    @Override
    public String toString() {
        return String.format("Meter[ID=%s, Type=%s, Location=%s, Status=%s, Reading=%.2f]", 
                           meterId, meterType, location, status, currentReading);
    }
}
