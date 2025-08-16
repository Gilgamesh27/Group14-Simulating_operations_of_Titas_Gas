package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public class BillingOfficer extends Employee {
    
    public BillingOfficer(String employeeId, String name) {
        super(employeeId, name, "Billing and Metering", "Billing Officer");
    }
    
    @Override
    public void performDuties() {
        System.out.println("Performing billing and metering duties...");
    }
    
    public void recordMeterReading(String meterId, double reading) {
        System.out.printf("Recording meter reading: Meter %s = %.2f units%n", meterId, reading);
    }
    
    public Bill generateBill(String customerId, double previousReading, double currentReading, double consumption, double unitPrice, double totalAmount, String billingPeriod, String dueDate) {
        Bill bill = new Bill(customerId, previousReading, currentReading, consumption, unitPrice, totalAmount, billingPeriod, dueDate);
        System.out.printf("Generated bill for customer %s: $%.2f%n", customerId, totalAmount);
        return bill;
    }
    
    public void updateMeterRecord(String meterId, String newType) {
        System.out.printf("Updated meter %s to type: %s%n", meterId, newType);
    }
    
    public void processRefund(String customerId, double amount, String reason) {
        System.out.printf("Processing refund for customer %s: $%.2f (Reason: %s)%n", 
                         customerId, amount, reason);
    }
    
    public void handleBillingError(String customerId, String errorDescription) {
        System.out.printf("Handling billing error for customer %s: %s%n", 
                         customerId, errorDescription);
    }
}
