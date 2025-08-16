package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public class SessionService {
    
    public void terminateBackgroundTasks() {
        System.out.println("Terminating background tasks...");
        System.out.println("Background tasks stopped successfully");
    }
    
    public void clearSession() {
        System.out.println("Clearing session and cache...");
        System.out.println("Session cleared successfully");
    }
    
    public void logout() {
        System.out.println("Starting logout process...");
        terminateBackgroundTasks();
        clearSession();
        System.out.println("Logout completed successfully");
    }
}