package project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	Scanner scanner = new Scanner(System.in);
	ArrayList<Store> allStores = new ArrayList<>();
	Customer currentCustomer = null;
	public void mainMenu() {
		System.out.println("To Enter Business perspective enter: 1, To Enter Customer Perspective enter: 2");
	}
	//Prompts for Customer View
	
	public void createCustomer() {
		System.out.println("Please enter your name:");
		String name = scanner.nextLine();
		
		System.out.println("Are you a member? Answer: (y/n)");
		String isMember = scanner.nextLine();
		boolean membership = false;
		if(isMember.equalsIgnoreCase("y")) {
			membership = true;
		}
		
		currentCustomer = new Customer(name, 12345, membership);
	}
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
		scanner.nextLine();
		if(picked != 3) {
			displayCars(store, picked);
		} else {
			searchCustomCar(store);
		}
	}
	
	public Vehicle displayCars(Store store, int choice) {
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
		
		System.out.println("Enter the number of the car you are interested in. If there is none, enter -1. ");
		int picked = scanner.nextInt();
		scanner.nextLine();
		
		if(picked != -1 && picked < allCars.size()) {
			return allCars.get(picked-1);
		} else {
			return null;
		}
	}
	
	public ArrayList<Vehicle> searchCustomCar(Store store) {
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
		
		return matchingCars;
	}
	
	public void processOrder(Vehicle vehicle) {
		if(vehicle instanceof Rentable) {
			System.out.println("You have selected a rental. How many days would you like to rent it? Enter number of days: ");
			int days = scanner.nextInt();
			scanner.nextLine();
			Order order = new Order(currentCustomer, vehicle.getLocation().getEmployees().get(0), vehicle.getLocation());
			order.addToOrder(vehicle);
			((Rentable)vehicle).setDaysRented(days);
		} else {
			boolean success = false;
			System.out.println("You have selected to purchase a vehicle.");
		
		}
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
		scanner.close();
		System.exit(0);
	}
	
    public static void main(String[] args) {
    	
        Store store = new Store("California", "San Jose");
        Employee employee = new Employee("Vincent", 100, store);
        Customer customer = new Customer("Kendrick", 85845, true, true, 0.1);
        Vehicle vehicle = new Vehicle("VIN88754", "Toyota", "Corolla", 2022, 10000);
        
        
        
	    
    }
}
