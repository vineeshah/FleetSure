package project;

import java.util.ArrayList;

public class RentalCar extends Vehicle implements Rentable {
	private boolean isAvailable;
	private double dailyRate;
	private int daysRented;
	Customer currentOwner;
	
	//Constructor Declaration
	public RentalCar(String VIN, String brand, String model, int year, double mileage) {
		super(VIN, brand, model, year, mileage);
		this.isAvailable = true;
		this.dailyRate = 0;
		this.daysRented = 0;
		this.currentOwner = null;
	}
	
	//Setters and Getters
	public boolean isAvailable() {
		return this.isAvailable;
	}
	
	public void setAvailibility(boolean availibility) {
		this.isAvailable = availibility;
	}
	
	public double getDailyRate() {
		return this.dailyRate;
	}
	
	public void setDailyRate(double newRate) {
		this.dailyRate = newRate;
	}
	
	public int getDaysRented() {
		return this.daysRented;
	}
	
	public void setDaysRented(int numDays) {
		this.daysRented = numDays;
	}
	
	public Customer getCurrentOwner() {
		return this.currentOwner;
	}
	
	public void setCurrentOwner(Customer newOwner) {
		this.currentOwner = newOwner;
	}
	
	//Methods from Rentable Interface
	@Override
	public double generateRate() {
		double baseRate = 50;
		String[] luxuryBrands = {"Lexus","Mercedes", "BMW", "Porsche"};
		
		if(this.getYear() < 2010) {
			baseRate -= 20;
		} 
		
		for(String brand: luxuryBrands) {
			if(this.getBrand().equals(brand)) {
				baseRate *= 1.15;
				break;
			}
		}
		
		return baseRate;
	}

	@Override
	public void returnToLot(Store store) {
		// TODO Auto-generated method stub
		Inventory inventory = store.getInventory();
		
		inventory.addVehicle(this);
		this.setAvailibility(true);
		this.setDaysRented(0);
	}

	@Override
	public boolean rent(Customer customer, int days) {
		if(!this.isAvailable()) {
			System.out.println("Sorry! This rental car has already rented, please wait " + this.getDaysRented() + " and try again later. ");
			return false;
		}
		
		this.setCurrentOwner(customer);
		this.setAvailibility(false);
		this.setDaysRented(days);
		
		System.out.println("Success! You have successfully rented out the car.");
		return true;
	}

	@Override
	public double calculateLateFees(int daysOverdue) {
		double lateFee = daysOverdue * this.getDailyRate();
		
		if(daysOverdue >= 7) {
			lateFee *= 1.1;
		}
		
		return lateFee;
	}

	@Override
	public void extendRental(int days) {
		if(days < 0) {
		System.out.println("Failed to extend rental. Please enter a positive integer for days.");
		}
		
		this.setDaysRented(this.getDaysRented() + days);
	}
	
	//Methods from Vehicle Superclass
	@Override
	public double calculateValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void reportDamage(int cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fixDamage(int cost) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void compareValue(Vehicle other) {
		// TODO Auto-generated method stub
		
	}
}
