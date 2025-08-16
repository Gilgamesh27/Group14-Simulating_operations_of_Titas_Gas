package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    private String zoneId;
    private String name;
    private String description;
    private List<String> customerIds;
    private String status;
    
    public Zone(String zoneId, String name) {
        this.zoneId = zoneId;
        this.name = name;
        this.description = "";
        this.customerIds = new ArrayList<>();
        this.status = "Active";
    }
    
    public Zone(String zoneId, String name, String description) {
        this.zoneId = zoneId;
        this.name = name;
        this.description = description;
        this.customerIds = new ArrayList<>();
        this.status = "Active";
    }
    
    // Getters
    public String getZoneId() { return zoneId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<String> getCustomerIds() { return new ArrayList<>(customerIds); }
    public String getStatus() { return status; }
    
    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setStatus(String status) { this.status = status; }
    
    // Business methods
    public void addCustomer(String customerId) {
        if (!customerIds.contains(customerId)) {
            customerIds.add(customerId);
        }
    }
    
    public void removeCustomer(String customerId) {
        customerIds.remove(customerId);
    }
    
    public int getCustomerCount() {
        return customerIds.size();
    }
    
    public boolean hasCustomer(String customerId) {
        return customerIds.contains(customerId);
    }
    
    @Override
    public String toString() {
        return String.format("Zone[ID=%s, Name=%s, Customers=%d, Status=%s]", 
                           zoneId, name, customerIds.size(), status);
    }
}
