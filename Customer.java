package proj;


public class Customer {

	private String contactInfo;
    private double payment;
    private double memberDiscount;
    private boolean GoodDriver;

    //creating information space
    public Customer(String contactInfo, double payment, double memberDiscount, boolean goodRecord) {
        this.contactInfo = contactInfo;
        this.payment = payment;
        this.memberDiscount = memberDiscount;
        this.GoodDriver = goodRecord;
    }
    
    // when customer pays we call employee to help 
    public void pay implments Employee {
        System.out.println("Hi I am here to help you pay: " + Employee.getHourlyStatus());
    }

    public double getDiscount() {
        return memberDiscount;
    }

    public boolean validate() {
        return GoodDriver;
    }

    public void displayInfo() {
        System.out.println("Your information:");
        System.out.println("Contact information (Best way to reach you): " + contactInfo);
        System.out.println("Total Due: $" + payment);
        System.out.println("Member Discount: " + (memberDiscount * 20) + "%");
        System.out.println("Driving Record: " + (GoodDriver ? "Good" : "Bad"));
    }
    
    //Getting information from customer about their status (Info, payment, and driving record)

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
        return GoodDriver;
    }

    public void setHasGoodDrivingRecord(boolean GoodDriver) {
        this.GoodDriver = GoodDriver;
    }
}
