package proj;


public class Customer {
    // Attributes
    private String contactInfo;
    private double payment;
    private double memberDiscount;
    private boolean hasGoodDrivingRecord;

    // Constructor
    public Customer(String contactInfo, double payment, double memberDiscount, boolean hasGoodDrivingRecord) {
        this.contactInfo = contactInfo;
        this.payment = payment;
        this.memberDiscount = memberDiscount;
        this.hasGoodDrivingRecord = hasGoodDrivingRecord;
    }

    // Methods
    public void pay(Employee employee) {
        System.out.println("Customer is paying with the help of employee: " + employee.getHourlyStatus());
    }

    public double getDiscount() {
        return memberDiscount;
    }

    public boolean validate() {
        return hasGoodDrivingRecord;
    }

    public void displayInfo() {
        System.out.println("Customer Info:");
        System.out.println("Contact: " + contactInfo);
        System.out.println("Payment Amount: $" + payment);
        System.out.println("Member Discount: " + (memberDiscount * 100) + "%");
        System.out.println("Driving Record: " + (hasGoodDrivingRecord ? "Good" : "Bad"));
    }

    // Getters and Setters
    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public boolean hasGoodDrivingRecord() {
        return hasGoodDrivingRecord;
    }

    public void setHasGoodDrivingRecord(boolean hasGoodDrivingRecord) {
        this.hasGoodDrivingRecord = hasGoodDrivingRecord;
    }
}
