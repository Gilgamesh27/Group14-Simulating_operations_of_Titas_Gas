package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public class GasSupplyControllerDomain extends Employee {
    
    public GasSupplyControllerDomain(String employeeId, String name) {
        super(employeeId, name, "Gas Supply Control", "Gas Supply Controller");
    }
    
    @Override
    public void performDuties() {
        System.out.println("Performing gas supply control duties...");
    }
    
    public void adjustSupplyPressure(String pipelineId, double newPressure) {
        System.out.printf("Adjusting supply pressure for pipeline %s to %.2f PSI%n", 
                         pipelineId, newPressure);
    }
    
    public void scheduleSupply(String area, String schedule) {
        System.out.printf("Scheduling gas supply for area %s: %s%n", area, schedule);
    }
    
    public void suspendSupply(String area, String reason) {
        System.out.printf("Suspending gas supply for area %s. Reason: %s%n", area, reason);
    }
    
    public void handleEmergency(String location, String emergencyType) {
        System.out.printf("Handling emergency at %s: %s%n", location, emergencyType);
        System.out.println("Emergency response initiated!");
    }
    
    public void managePeakDemand(String area, double demandLevel) {
        System.out.printf("Managing peak demand for area %s: %.2f units%n", area, demandLevel);
    }
    
    public void generateSupplyReport(String period) {
        System.out.printf("Generating gas supply report for period: %s%n", period);
    }
}
