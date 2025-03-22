package project;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static Scanner scanner = new Scanner(System.in);
	static ArrayList<Store> allStores = new ArrayList<>();
	static Customer currentCustomer = null;
	public static int mainMenu() {
		System.out.println("1: Enter Business perspective");
		System.out.println("2: Enter Customer perspective");
		System.out.println("Enter 1 or 2 to pick: ");
		int picked = scanner.nextInt();
		scanner.nextLine();
		return picked;
	}
	//Prompts for Customer View
	
	public static void createCustomer() {
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
	public static void customerMenu() {
		System.out.println("Welcome to our car business. Please select a location from the list below.");
		for(int i = 1; i <= allStores.size(); i++) {
			System.out.println(i + ": " + allStores.get(i-1).toString());
		}
		int picked = scanner.nextInt();
		storeMenu(allStores.get(picked));
	}
		
	public static void storeMenu(Store store) {
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
	
	public static Vehicle displayCars(Store store, int choice) {
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
	
	public static ArrayList<Vehicle> searchCustomCar(Store store) {
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
	
	public static Order processOrder(Vehicle vehicle) {
		Order order = new Order(currentCustomer, vehicle.getLocation().getEmployees().get(0), vehicle.getLocation());
		order.addToOrder(vehicle);
		
		if(vehicle instanceof Rentable) {
			System.out.println("You have selected a rental. How many days would you like to rent it? Enter number of days: ");
			int days = scanner.nextInt();
			scanner.nextLine();
			((Rentable)vehicle).setDaysRented(days);
		} else {
			System.out.println("You have selected to purchase a vehicle.");
		}
			return order;
	}
	
	//Prompts for Business View
	public static void businessMenu() {
		System.out.println("Choose which location to manage below.");
		for(int i = 0; i < allStores.size(); i++) {
			System.out.println(i+1 + ": " + allStores.get(i).toString());
		}
		int picked = scanner.nextInt();
		businessStoreMenu(allStores.get(picked));
	}
	public static void businessStoreMenu(Store store) {
		System.out.println(store.toString());
		System.out.println("1: Manage Vehicles");
		System.out.println("2: Manage Employees");
		System.out.println("Enter a choice (1 or 2): ");
		
		int picked = scanner.nextInt();
		
	}
	
	public static void manageVehiclesMenu() {
		System.out.println("1: Add Vehicles");
		System.out.println("2: Delete Vehicles");
	}
	
	public static void manageEmployeeMenu() {
		System.out.println("1: Add Employees");
		System.out.println("2: Delete Employees");
	}
	
	//Exit method
	public static void exit() {
		scanner.close();
		System.exit(0);
	}
	
    public static void main(String[] args) {
    	//Preloading the system with Generic values
        allStores.add(new Store("California", "San Jose"));
        allStores.add(new Store("California", "San Diego"));
        allStores.add(new Store("California", "San Fransico"));
        allStores.add(new Store("California", "Santa Barbara"));
        allStores.add(new Store("California", "Los Angeles"));
        
        Employee employee1 = new Employee("Vincent", 100, allStores.get(0));
        Employee employee2 = new Employee("Harry", 101, allStores.get(0));
        Employee employee3 = new Employee("Joe", 102, allStores.get(0));
        Employee employee4 = new Employee("David", 103, allStores.get(0));
        Employee employee5 = new Employee("Kenny", 104, allStores.get(0));
        
        allStores.get(0).getEmployees().add(employee1);
        allStores.get(1).getEmployees().add(employee2);
        allStores.get(2).getEmployees().add(employee3);
        allStores.get(3).getEmployees().add(employee4);
        allStores.get(4).getEmployees().add(employee5);
        
        allStores.get(0).getInventory().addVehicle(new Car("ABCD1234", "Toyota", "Prius", 2012, 10100.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new Car("123123C4", "Toyota", "Highlander", 2015, 10400.0, allStores.get(1)));
        allStores.get(0).getInventory().addVehicle(new Car("ABDDZ114", "Honda", "Civic", 2009, 10300.0, allStores.get(2)));
        allStores.get(0).getInventory().addVehicle(new Car("LL22441A", "Honda", "Accord", 2017, 10080.0, allStores.get(3)));
        allStores.get(0).getInventory().addVehicle(new Car("DC45A1B9", "Toyota", "Corrolla", 2011, 19000.0, allStores.get(4)));

        
        int choice = mainMenu();
        if(choice == 1) {
        	businessMenu();
        } else {
        	createCustomer();
        	customerMenu();
        }
        
        
        
	    
    }
}
