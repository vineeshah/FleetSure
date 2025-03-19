package project;

public class MovingTruck extends Vehicle implements Rentable {
    private double capacityInLBS;
    private boolean hasInsurance;
    private int daysRented;

    public MovingTruck (String VIN, String brand, String model, int year, double mileage, Store location, double capacityInLBS, boolean hasInsurance){
        super(VIN, brand, model, year, mileage, location); //comes from parent class
        this.capacityInLBS = capacityInLBS;
        this.hasInsurance = hasInsurance;
        this.daysRented = 0; //defualt days rented
    }

    // Getters and Setters
    public double getCapacityInLBS() {
        return capacityInLBS;
    }

    public void setCapacityInLBS(double capacityInLBS) {
        this.capacityInLBS = capacityInLBS;
    }

    public boolean hasInsurance() {
        return hasInsurance;
    }

    public void setInsurance(boolean hasInsurance) {
        this.hasInsurance = hasInsurance;
    }

    public int getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(int daysRented) {
        this.daysRented = daysRented;
    }
    //implement Rentable interface methods
    @Override
    public double generateRate(){
        double baseRate = 60.0; //Base daily rate
        double insuranceFee;

        if (hasInsurance){
            insuranceFee = 0;
        }else{
            insuranceFee = 10.0;
        }
        return (baseRate + insuranceFee) * daysRented;

    }











}
