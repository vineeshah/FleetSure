package project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Store> allStores = new ArrayList<>();
	public void mainMenu() {
		System.out.println("To Enter Business perspective enter: 1, To Enter Customer Perspective enter: 2");
	}
	//Prompts for Customer View
	public void customerMenu() {
		System.out.println("Welcome to our car business. Please select a location from the list below.");
		for(int i = 1; i <= allStores.size(); i++) {
			System.out.println(i + ": " + allStores.get(i-1).toString());
		}
		int picked = scanner.nextInt();
		businessStoreMenu(allStores.get(picked));
	}
		
	public void storeMenu(Store store) {
		System.out.println(store.toString());
		System.out.println("1: Search for Rentals");
		System.out.println("2: Search for Cars on Sale");
		System.out.println("3: Custom Search");
		System.out.println("Enter a choice (1 - 3): ");
		
		int picked = scanner.nextInt();
		
		if(picked != 3) {
			displayCars(store, picked);
		} else {
			searchCustomCar(store);
		}
	}
	
	public void displayCars(Store store, int choice) {
		Inventory inventory = store.getInventory();
		ArrayList<Vehicle> allCars = new ArrayList<>();
		if(choice == 1) {
			allCars = inventory.search("Rentable:True");
		} else {
			allCars = inventory.search("ForSale:True");
		}
		
		for(int i = 0; i < allCars.size(); i++) {
			System.out.println(i+1 +": " + allCars.get(i).toString());
		}
	}
	
	public void searchCustomCar(Store store) {
		System.out.println("We will ask you some questions to help find a car that fits your needs. Answer \"N/A\" if that aspect doesn't matter");
		System.out.println("What is the maximum mileage you would like?");
		String mileage = scanner.nextLine();
		
		System.out.println("What is the minimum year you would like?");
		String year = scanner.nextLine();
		
		System.out.println("What brand would you like?");
		String brand = scanner.nextLine();
		
		System.out.println("What model would you like?");
		String model = scanner.nextLine();
		
		System.out.println("Are you looking to buy or rent? Enter: buy or rent");
		String purchaseType = scanner.nextLine();
		
		ArrayList<Vehicle> matchingCars = new ArrayList<>();
		Inventory current = store.getInventory();
		
		if(!mileage.equalsIgnoreCase("n/a")) {
			matchingCars = current.search("mileage:"+mileage);
		}
		if(!year.equalsIgnoreCase("n/a")) {
			matchingCars.retainAll(current.search("year:"+ year));
		}
		if(!brand.equalsIgnoreCase("n/a")) {
			matchingCars.retainAll(current.search("brand:"+ brand));
		}
		if(!model.equalsIgnoreCase("n/a")) {
			matchingCars.retainAll(current.search("model:"+ model));
		}
		if(!purchaseType.equalsIgnoreCase("n/a")) {
			if(purchaseType.equalsIgnoreCase("buy")) {
				matchingCars.retainAll(current.search("forsale:true"));
			} else {
				matchingCars.retainAll(current.search("rentable:true"));
			}
		}
		
		for(int i = 0; i < matchingCars.size(); i++) {
			System.out.println(i+1 +": " + matchingCars.get(i).toString());
		}
		
		System.out.println("E");
	}
	//Prompts for Business View
	public void businessMenu() {
		System.out.println("Choose which location to manage below.");
		for(int i = 0; i < allStores.size(); i++) {
			System.out.println(i+1 + ": " + allStores.get(i).toString());
		}
		int picked = scanner.nextInt();
		businessStoreMenu(allStores.get(picked));
	}
	public void businessStoreMenu(Store store) {
		System.out.println(store.toString());
		System.out.println("1: Manage Vehicles");
		System.out.println("2: Manage Employees");
		System.out.println("Enter a choice (1 or 2): ");
		
		int picked = scanner.nextInt();
		
	}
	
	public void manageVehiclesMenu() {
		System.out.println("1: Add Vehicles");
		System.out.println("2: Delete Vehicles");
	}
	
	public void manageEmployeeMenu() {
		System.out.println("1: Add Employees");
		System.out.println("2: Delete Employees");
	}
	
	//Exit method
	public void exit() {
		System.exit(0);
	}
	
    public static void main(String[] args) {
    	
        Store store = new Store("California", "San Jose");
        Employee employee = new Employee("Vincent", 100, store);
        Customer customer = new Customer("Kendrick", 85845, true, true, 0.1);
        Vehicle vehicle = new Vehicle("VIN88754", "Toyota", "Corolla", 2022, 10000);
        
        
        
	    
    }
}
