package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String phoneNumber;
    private String accountNumber;
    private String connectionStatus;
    
    public Customer(String customerId, String name, String address, String phoneNumber) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = generateAccountNumber();
        this.connectionStatus = "Active";
    }
    
    private String generateAccountNumber() {
        return "ACC-" + System.currentTimeMillis() % 100000;
    }
    
    // Getters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAccountNumber() { return accountNumber; }
    public String getConnectionStatus() { return connectionStatus; }
    
    // Setters
    public void setAddress(String address) { this.address = address; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setConnectionStatus(String connectionStatus) { this.connectionStatus = connectionStatus; }
    
    @Override
    public String toString() {
        return String.format("Customer[ID=%s, Name=%s, Account=%s, Status=%s]", 
                           customerId, name, accountNumber, connectionStatus);
    }
}
