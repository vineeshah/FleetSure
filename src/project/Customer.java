package project;

import java.util.List;

public class Customer {
    private String name;
    private int memberId;
    private boolean membershipActive;
    private List<Vehicle> Rentable;
    
    public Customer(String name, int memberId, boolean membershipActive) {
        public String getName() {
        return name;
    }

    public int getMemberId() {
        return memberId;
    }

    public boolean hasActiveMembership() {
        return membershipActive;
    }
        
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Try again you forgot to fill in your name.");
        }
        this.name = name;
        this.memberId = memberId;
        this.membershipActive = membershipActive;
        this.Rentable = null;
    }
    
    public void pay(pay order) {
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
        System.out.println(name, amount, order.getOrderId());
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
        if (Rentable == null) {
        	Rentable = new ArrayList<>();
        }

        Rentable.add(vehicle);
        store.removeVehicle(vehicle);
        System.out.println(name + "here is your rental" + vehicleModel);
    }
