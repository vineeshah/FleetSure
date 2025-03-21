package project;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Vehicle> allVehicles;
	
	//Constructor
	public Inventory() {
		this.allVehicles = new ArrayList<Vehicle>();
	}
	
	//Getter
	public ArrayList<Vehicle> getAllVehicles() {
		return this.allVehicles;
	}
	// Methods Unique to Inventory
	public ArrayList<Vehicle> search(String specs) {
		ArrayList<Vehicle> matchingVehicles = new ArrayList<>();
		String[] requirement = specs.split(":"); //Input formatted dataType:Value
		
		for(Vehicle vehicle: allVehicles) {
			switch(requirement[0].toLowerCase()) {
				case "mileage":
					if(vehicle.getMileage() <= Double.parseDouble(requirement[1])) {
						matchingVehicles.add(vehicle);
					}
					break;
					
				case "year":
					if(vehicle.getYear() >= Integer.parseInt(requirement[1])) {
						matchingVehicles.add(vehicle);
					}
					break;
					
				case "model": 
					if(vehicle.getModel().equalsIgnoreCase(requirement[1])) {
						matchingVehicles.add(vehicle);
					}
					break;
					
				case "brand":
					if(vehicle.getBrand().equalsIgnoreCase(requirement[1])) {
						matchingVehicles.add(vehicle);
					}
					break;
				case "rental":
					if(requirement[1].equalsIgnoreCase("true") && vehicle instanceof Rentable) {
						matchingVehicles.add(vehicle);
					}
					break;
				case "forsale": 
					if(requirement[1].equalsIgnoreCase("true") && vehicle instanceof ForSale) {
						matchingVehicles.add(vehicle);
					}
					break;
			}
		}
		
		return matchingVehicles;
	}
	
	public void removeFromInventory(Vehicle vehicle) {
		allVehicles.remove(vehicle);
	}
	
	public void addVehicle(Vehicle vehicle) {
		allVehicles.add(vehicle);
	}
	
	public int getTotalInventory() {
		return allVehicles.size();
	}
	
}
