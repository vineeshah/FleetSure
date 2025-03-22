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
		storeMenu(allStores.get(picked-1));
	}
		
	public static void storeMenu(Store store) {
		System.out.println(store.toString());
		System.out.println("1: Search for Rentals");
		System.out.println("2: Search for Cars on Sale");
		System.out.println("3: Custom Search");
		System.out.println("4: Exit the program");
		System.out.println("Enter a choice (1 - 4): ");
		
		int picked = scanner.nextInt();
		scanner.nextLine();
		
		if(picked < 3) {
			displayCars(store, picked);
		} else if(picked == 3){
			searchCustomCar(store);
		} else {
			exit();
		}
	}
	
	public static void displayCars(Store store, int choice) {
		Inventory inventory = store.getInventory();
		ArrayList<Vehicle> allCars = inventory.getAllVehicles();
		if(choice == 1) {
			allCars = inventory.search("rental:True");
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
			processOrder(allCars.get(picked));
		} else {
			System.out.println("Sorry you didn't find an option you liked. Returning you to the main menu.");
			System.out.println();
			customerMenu();
		}
	}
	
	public static void searchCustomCar(Store store) {
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
		
		Inventory current = store.getInventory();
		ArrayList<Vehicle> matchingCars = current.getAllVehicles();
		
		
		if(!mileage.equalsIgnoreCase("n/a")) {
			matchingCars.retainAll(current.search("mileage:"+ mileage));
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
				matchingCars.retainAll(current.search("rental:true"));
			}
		}
		
		for(int i = 0; i < matchingCars.size(); i++) {
			System.out.println(i+1 +": " + matchingCars.get(i).toString());
		}
		
		System.out.println("Enter the number of the car you are interested in. If there is none, enter -1. ");
		int picked = scanner.nextInt();
		scanner.nextLine();
		
		if(picked != -1 && picked < matchingCars.size()) {
			processOrder(matchingCars.get(picked));
		} else {
			System.out.println("Sorry you didn't find an option you liked. Returning you to the main menu.");
			System.out.println();
			customerMenu();
		}
	}
	
	public static void processOrder(Vehicle vehicle) {
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
		System.out.println(order.toString());
		System.out.println("Please enter your 16 digit credit card number to pay for the order. (No Spaces)");
		String cardnum = scanner.nextLine();
		if(cardnum.length() == 16 && cardnum.matches("\\d+")) {
			order.acceptPayment();
		} else {
			System.out.println("We could not validate this purchase. Please try again later.");
		}
		System.out.println("Redirecting you to our main menu!");
		System.out.println();
		customerMenu();
		
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
		System.out.println("3: Exit the program");
		System.out.println("Enter a choice (1 - 3): ");
		
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
        allStores.add(new Store("San Jose", "California" ));
        allStores.add(new Store("San Diego","California"));
        allStores.add(new Store("San Francisco", "California"));
        
        Employee employee1 = new Employee("Vincent", 100, allStores.get(0));
        Employee employee2 = new Employee("Harry", 101, allStores.get(1));
        Employee employee3 = new Employee("Joe", 102, allStores.get(2));

        
        allStores.get(0).getEmployees().add(employee1);
        allStores.get(1).getEmployees().add(employee2);
        allStores.get(2).getEmployees().add(employee3);
        
        allStores.get(0).getInventory().addVehicle(new Car("ABCD1234", "Toyota", "Prius", 2012, 10100.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new Car("123123C4", "Toyota", "Highlander", 2015, 10400.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new Car("ABDDZ114", "Honda", "Civic", 2009, 10300.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new Car("LL22441A", "Honda", "Accord", 2017, 10080.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new Car("DC45A1B9", "Toyota", "Corrolla", 2011, 19000.0, allStores.get(0)));
        
        allStores.get(1).getInventory().addVehicle(new Car("XYZ98765", "Ford", "Focus", 2018, 12000.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new Car("LMN45678", "Chevrolet", "Malibu", 2014, 13500.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new Car("GHJ23489", "Nissan", "Altima", 2016, 9800.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new Car("QWE56473", "Hyundai", "Elantra", 2020, 8700.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new Car("ASD45896", "Kia", "Sorento", 2013, 15000.0, allStores.get(1)));

        allStores.get(2).getInventory().addVehicle(new Car("ZXCV6789", "Tesla", "Model 3", 2021, 5000.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new Car("POIU9876", "BMW", "X5", 2019, 11000.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new Car("LKJH5432", "Mercedes", "C-Class", 2017, 8700.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new Car("MNBV1243", "Subaru", "Outback", 2015, 9600.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new Car("YUIO8765", "Jeep", "Wrangler", 2018, 14300.0, allStores.get(2)));
        
        allStores.get(0).getInventory().addVehicle(new RentalCar("ABCD1235", "Toyota", "Prius", 2012, 10170.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new RentalCar("123123C5", "Toyota", "Highlander", 2015, 1070.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new RentalCar("ABDDZ115", "Honda", "Civic", 2009, 11030.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new RentalCar("LL224415", "Honda", "Accord", 2017, 150080.0, allStores.get(0)));
        allStores.get(0).getInventory().addVehicle(new RentalCar("DC45A1B5", "Toyota", "Corrolla", 2011, 51000.0, allStores.get(0)));
        
        allStores.get(1).getInventory().addVehicle(new RentalCar("XYZ98766", "Ford", "Focus", 2018, 1200.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new RentalCar("LMN45676", "Chevrolet", "Malibu", 2014, 18300.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new RentalCar("GHJ23486", "Nissan", "Altima", 2016, 98800.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new RentalCar("QWE56476", "Hyundai", "Elantra", 2020, 68700.0, allStores.get(1)));
        allStores.get(1).getInventory().addVehicle(new RentalCar("ASD45896", "Kia", "Sorento", 2013, 12500.0, allStores.get(1)));

        allStores.get(2).getInventory().addVehicle(new RentalCar("ZXCV6787", "Tesla", "Model 3", 2021, 50200.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new RentalCar("POIU9877", "BMW", "X5", 2019, 11900.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new RentalCar("LKJH5437", "Mercedes", "C-Class", 2017, 87080.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new RentalCar("MNBV1247", "Subaru", "Outback", 2015, 96090.0, allStores.get(2)));
        allStores.get(2).getInventory().addVehicle(new RentalCar("YUIO8767", "Jeep", "Wrangler", 2018, 147300.0, allStores.get(2)));
        
        allStores.get(0).getInventory().addVehicle(new MovingTruck("A9CD1235", "Ford", "Transit", 2012, 10170.0, allStores.get(0), 1500, true));
        allStores.get(1).getInventory().addVehicle(new MovingTruck("193123C5", "GMC", "Savana", 2015, 1070.0, allStores.get(1), 700, false));
        allStores.get(2).getInventory().addVehicle(new MovingTruck("A9DDZ115", "Chevrolet", "Express", 2009, 11030.0, allStores.get(2), 950, true));
       
        //Prompt User to begin the program.
        int choice = mainMenu();
        if(choice == 1) {
        	businessMenu();
        } else {
        	createCustomer();
        	customerMenu();
        }  
    }
}
