package com.oop.groupfourteen.group14simulating_operations_of_titas_gas.Alif;

public class DiscountRequest {
    private String requestId;
    private String customerId;
    private String discountType;
    private double discountAmount;
    private String status;
    private String reason;

    public DiscountRequest(String requestId, String customerId, String discountType, double discountAmount, String status, String reason) {
        this.requestId = requestId;
        this.customerId = customerId;
        this.discountType = discountType;
        this.discountAmount = discountAmount;
        this.status = status;
        this.reason = reason;
    }

    public String getRequestId() { return requestId; }
    public String getCustomerId() { return customerId; }
    public String getDiscountType() { return discountType; }
    public double getDiscountAmount() { return discountAmount; }
    public String getStatus() { return status; }
    public String getReason() { return reason; }

    public void setStatus(String status) { this.status = status; }
}
