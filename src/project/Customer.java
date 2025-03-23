package project;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private int memberId;
    private boolean membershipActive;
    private ArrayList<Vehicle> Rentable;

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

        //Catching user input error if they step a step
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Try again you forgot to fill in your name.");
        }
        this.name = name;
        this.memberId = memberId;
        this.membershipActive = membershipActive;
        this.Rentable = new ArrayList<>();
    }
    
    public void pay(Order order) {
        if (order == null) {
            //User input error if we can't find an order to display
            throw new IllegalArgumentException("You have an empty order try again.");
        }
        
        double discount;
        if (membershipActive) {
            discount = 0.2; 
        } else {
            discount = 0.0; 
        }          
        
        double amount = order.getAmountDue() * (1 - discount);
        System.out.println(name + " your total amount was" + amount);
    }

    public void rentVehicle(Store store, String vehicleModel) {
        //Also checking for any empty inputs so we can tell the user to try again
        if (store == null || vehicleModel == null || vehicleModel.trim().isEmpty()) {
            throw new IllegalArgumentException("The store you selected or car model was not found try again.");
        }
        
        Inventory inventory = store.getInventory();
        ArrayList<Vehicle> vehicles = inventory.getAllVehicles();
        Vehicle vehicle = null;
        int i = 0;
        while(vehicle == null && i < vehicles.size()) {
        	if(vehicles.get(i).getModel().equalsIgnoreCase(vehicleModel)) {
        		vehicle = vehicles.get(i);
        }
        	i++;
        }

        //updating inventory after its been checked out
        if (vehicle == null) {
            System.out.println("The vehicle you are searching for is not avaible try again.");
            return;
       }
        inventory.removeFromInventory(vehicle);
        System.out.println("Here is your rental" + vehicleModel + "glad we can make your trip a little easier.");
    }
      public ArrayList<Vehicle> getRentedVehicles() {
        return Rentable;
    }

}
