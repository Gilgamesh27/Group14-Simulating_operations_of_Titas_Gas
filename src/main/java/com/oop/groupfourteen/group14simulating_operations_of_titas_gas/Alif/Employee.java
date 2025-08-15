package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public abstract class Employee {
    protected String employeeId;
    protected String name;
    protected String department;
    protected String role;
    
    public Employee(String employeeId, String name, String department, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.role = role;
    }
    
    public String getEmployeeId() { return employeeId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public String getRole() { return role; }
    
    public abstract void performDuties();
    
    @Override
    public String toString() {
        return String.format("Employee[ID=%s, Name=%s, Department=%s, Role=%s]", 
                           employeeId, name, department, role);
    }
}
