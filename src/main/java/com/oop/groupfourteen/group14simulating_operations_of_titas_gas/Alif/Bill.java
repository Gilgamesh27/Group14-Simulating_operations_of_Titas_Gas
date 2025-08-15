package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import java.time.LocalDate;

public class Bill {
    private String billId;
    private String customerId;
    private double previousReading;
    private double currentReading;
    private double consumption;
    private double unitPrice;
    private double totalAmount;
    private String billingPeriod;
    private String dueDate;
    private String status;
    
    public Bill(String customerId, double previousReading, double currentReading, double consumption, double unitPrice, double totalAmount, String billingPeriod, String dueDate) {
        this.billId = generateBillId();
        this.customerId = customerId;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.consumption = consumption;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
        this.billingPeriod = billingPeriod;
        this.dueDate = dueDate;
        this.status = "Pending";
    }
    
    private String generateBillId() {
        return "BILL-" + System.currentTimeMillis() % 100000;
    }
    
    // Getters
    public String getBillId() { return billId; }
    public String getCustomerId() { return customerId; }
    public double getPreviousReading() { return previousReading; }
    public double getCurrentReading() { return currentReading; }
    public double getConsumption() { return consumption; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalAmount() { return totalAmount; }
    public String getBillingPeriod() { return billingPeriod; }
    public String getDueDate() { return dueDate; }
    public String getStatus() { return status; }
    
    // Setters
    public void setStatus(String status) { this.status = status; }
    
    @Override
    public String toString() {
        return String.format("Bill[ID=%s, Customer=%s, Amount=$%.2f, Status=%s]", 
                           billId, customerId, totalAmount, status);
    }
}
