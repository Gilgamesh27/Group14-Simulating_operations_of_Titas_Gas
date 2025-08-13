package com.oop.groupfourteen.group14simulating_operations_of_titas_gas;


public class User {
    private String userId;
    private String password;
    private String role;
    private String dashboardFile;
    private String windowTitle;


    public User(String userId, String password, String role, String dashboardFile, String windowTitle) {
        this.userId = userId;
        this.password = password;
        this.role = role;
        this.dashboardFile = dashboardFile;
        this.windowTitle = windowTitle;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getDashboardFile() {

        return dashboardFile;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public boolean isPasswordCorrect(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public boolean hasUserId(String inputUserId) {
        return this.userId.equals(inputUserId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", dashboardFile='" + dashboardFile + '\'' +
                ", windowTitle='" + windowTitle + '\'' +
                '}';
    }
}
