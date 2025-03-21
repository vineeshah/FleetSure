package project;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int memberId;
    private boolean membershipActive;
    private List<Vehicle> Rentable;

     public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public boolean activeMembership() {
        return membershipActive;
    }
    
    public Customer(String name, int memberId, boolean membershipActive) {
        
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Try again you forgot to fill in your name.");
        }
        this.name = name;
        this.memberId = memberId;
        this.membershipActive = membershipActive;
        this.Rentable = new Arraylist<>();
    }
    
    public void pay(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have an empty order try again.");
        }
        
        double discount;
        if (membershipActive) {
            discount = 0.2; 
        } else {
            discount = 0.0; 
        }          
        
        double amount = order.getTotalAmount() * (1 - discount);
        System.out.println(name + " your total amount was" + amount);
    }

    public void rentVehicle(Store store, String vehicleModel) {
        if (store == null || vehicleModel == null || vehicleModel.trim().isEmpty()) {
            throw new IllegalArgumentException("The store you selected or car model was not found try again.");
        }
        
        List<String> carModels = List.of( "Toyota", "Honda", "Ford", "Chevrolet", "BMW", "Mercedes", "Audi", "Volkswagen", "Hyundai", "Kia");

        if (!carModels.contains(vehicleModel)) {
             System.out.println("The vehicle you are searching for is not avaible try again.");
             return;
        }

        Vehicle vehicle = new Vehicle(vehicleModel);
        Inventory.add(vehicle);
        Inventory.removeVehicle(vehicle);
        System.out.println("Here is your rental" + vehicleModel + "glad we can make your trip a little easier.");
    }
      public List<Vehicle> getRentedVehicles() {
        return new ArrayList<>(Rentable);
    }

}
