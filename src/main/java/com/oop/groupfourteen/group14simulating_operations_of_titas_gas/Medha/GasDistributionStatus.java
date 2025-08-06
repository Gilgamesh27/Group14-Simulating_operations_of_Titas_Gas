package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Medha;

public class GasDistributionStatus {

    private double pressure;
    private double flow;

    public GasDistributionStatus(double pressure, double flow) {
        this.pressure = pressure;
        this.flow = flow;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getFlow() {
        return flow;
    }

    public void setFlow(double flow) {
        this.flow = flow;
    }

    public String getFormattedStatus() {
        return "Pressure: " + pressure + " psi\nFlow: " + flow + " m³/h";
    }
}
