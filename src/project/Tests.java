package project;

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class Tests {
	@Before
	public void resetCounter() {
		Car.currentObjects = 0;
		RentalCar.currentObjects = 0;
		MovingTruck.currentObjects = 0;
	}
	@Test
	public void searchFunctionTest() {
		Inventory inventory = new Inventory();
		Car.currentObjects = 0;
		try {
			inventory.addVehicle( new Car("ABCD1234", "Toyota", "Prius", 2015, 101.0,null));
			inventory.addVehicle( new Car("ABCD1235", "Honda", "Accord", 2012, 50100.0,null)); 
			inventory.addVehicle( new Car("ABCD1236", "BMW", "Prius", 2011, 60100.0,null)); 
			inventory.addVehicle( new Car("ABCD1237", "Toyota", "Highlander", 2009, 17100.0,null)); 
			inventory.addVehicle( new Car("ABCD1238", "Honda", "Civic", 2010, 1180.0,null)); 
			inventory.addVehicle( new Car("ABCD1239", "Toyota", "Prius", 2016, 200.0,null)); 
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		} 
		
		
		ArrayList<Vehicle> cars1 = inventory.search("Brand:toyota");
		assertTrue(cars1.size() == 3);
		assertTrue(cars1.get(0) == inventory.getAllVehicles().get(0));
		
		ArrayList<Vehicle> cars2 = inventory.search("year:2012");
		assertTrue(cars2.size() == 3);
		assertTrue(cars2.get(1) == inventory.getAllVehicles().get(1));
		
		cars1.retainAll(cars2); // Intersection of cars1 and cars2
		assertTrue(cars1.size() == 2);
		assertTrue(cars1.get(0) == inventory.getAllVehicles().get(0));
		assertTrue(cars1.get(1) == inventory.getAllVehicles().get(5));
		
	}
	@Test
	public void objectOverCreationTest() {
		ArrayList<Vehicle> carTest = new ArrayList<>();
		Car.currentObjects = 0;
		try {
            for (int i = 0; i <= 100; i++) {
                carTest.add(new Car(null, null, null, i, i, null));
            }
            fail("ObjectOverLimitException was not thrown");
        } catch (ObjectOverLimitException e) {
            assertEquals("The system can't support greater than 100 instances of car.", e.getMessage());        
        }
		
		try {
            for (int i = 0; i <= 100; i++) {
                carTest.add(new RentalCar("123", "123", "123", i, i, null));
            }
            fail("ObjectOverLimitException was not thrown");
        } catch (ObjectOverLimitException e) {
            assertEquals("The system can't support greater than 100 instances of rental car.", e.getMessage());        
        }
		
		try {
            for (int i = 0; i <= 100; i++) {
                carTest.add(new MovingTruck("123", "123","123", i, i, null, i, false));
            }
            fail("ObjectOverLimitException was not thrown");
        } catch (ObjectOverLimitException e) {
            assertEquals("The system can't support greater than 100 instances of moving truck.", e.getMessage());        
        }
		
	}
	
	@Test
	public void invalidInputsTest() {
		
	}
	
	@Test
	public void generalIntegrationTest() {
		
	}
	
	@Test
	public void discountOrderTest() {
		try {
			Car car = new Car("112222","Toyota", "Prius", 2001, 100000, null);
			Order order = new Order(null, null, null);
			Employee em = new Employee("Joe", 123455432, new Store("City", "State"));
			
			double value = car.calculateValue();
			assertTrue(value == 6750);
			order.addToOrder(car);
			order.calculateAmountDue();
			em.Discount(order, .2);
			
			assertTrue(order.getAmountDue() == 5400);
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
		
		
		
	}

	//testing the employee discount
	@Test
	public void testDiscount() {
        String name = "Duncan Hall";
        int id = 9;
       
        Employee employee;
		try {
			Store store = new Store("City", "State");
			employee = new Employee(name, id, store);
			Order order = new Order(new Customer("Duncan Hall", 9, true), employee, store);
	        order.setAmountDue(500);
	        assertEquals(name, employee.getName());
	        assertEquals(id, employee.getId());
	        assertEquals(store, employee.getStore());
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
        
	}

	//testing employee validating customer 
	@Test
	public void testValidateCustomer() {
        String name = "Tim Cook";
        int id = 8;
        Store store;
		try {
			store = new Store("City", "State");
			Employee employee = new Employee(name, id, store);
	        Customer customer = new Customer("Time Cook", 8, true);
	        assertTrue(employee.validateCustomer(customer));
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
    }
	
	//testing customer membership
	@Test
   	public void testMembership() {
        String name = "Jermey";
        int memberId = 7;
        boolean membershipActive = true;
        Customer customer = new Customer(name, memberId, membershipActive);
        assertTrue(customer.activeMembership());
    }

	//testing customer output
	@Test
   	public void testInitialization() {
        String name = "Jermey";
        int id = 6;
        Store store;
		try {
			store = new Store("City", "State");
			Employee employee = new Employee(name, id, store);
			
	        assertEquals(name, employee.getName());
	        assertEquals(id, employee.getId());
	        assertEquals(store, employee.getStore());
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
        
    }
	

   //testing MovingTruck intialization
    @Test
    public void testTruck() {  
        String VIN = "H3H1J";
        String brand = "Toyota";
        String model = "Tacoma";
        int year = 2020;
        double mileage = 200.0;
        Store location;
		try {
			location = new Store("City", "State");
			double capacityInLBS = 2000.0;
	        boolean hasInsurance = true;
	        MovingTruck movingTruck = new MovingTruck(VIN, brand, model, year, mileage, location, capacityInLBS, hasInsurance);
	        assertEquals(VIN, movingTruck.getVIN());
	        assertEquals(brand, movingTruck.getBrand());
	        assertEquals(model, movingTruck.getModel());
	        assertEquals(year, movingTruck.getYear());
	        assertTrue(mileage == movingTruck.getMileage());
	        assertTrue(capacityInLBS == movingTruck.getCapacityInLBS());
	        assertTrue(movingTruck.hasInsurance());
	        assertTrue(movingTruck.isAvailable());
		} catch (ObjectOverLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

    // making sure it recongized that a truck is standstill but jusst got rented	
	
   // testing generating total 	
    @Test
   public void testGenerateRate() {
        String VIN = "H3H1J";
        String brand = "Toyota";
        String model = "Tacoma";
        int year = 2020;
        double mileage = 200.0;
        Store location;
		try {
			location = new Store("City", "State");
			double capacityInLBS = 2000.0;
	        boolean hasInsurance = true;
	        MovingTruck movingTruck = new MovingTruck(VIN, brand, model, year, mileage, location, capacityInLBS, hasInsurance);
	        double rate = movingTruck.generateRate();
	        assertTrue(0.0 == rate);
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
        
    }

    //Order class collecting funds	
    @Test
    public void testAcceptPayment() {
        Customer customer = new Customer("Jermey", 8, true);
        Employee employee;
		try {
			employee = new Employee("Duncan", 9, new Store("City", "State"));
			Store location = new Store("City", "State");
		    Order order = new Order(customer, employee, location);
		    order.setAmountDue(200.0);
		    order.acceptPayment();
		    assertEquals(0, order.getAmountDue(), 0.01);
		} catch (ObjectOverLimitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }

   //Same idea from MovingTruck to test the Car's funcationality 
   @Test
   public void testCar() {
        String VIN = "DU24H4";
        String brand = "Toyota";
        String model = "Corolla";
        int year = 1986;
        double mileage = 200000.0;
        Store location;
		try {
			location = new Store("City", "State");
			RentalCar rentalCar = new RentalCar(VIN, brand, model, year, mileage, location);
		    assertTrue(rentalCar.isAvailable());
		    assertEquals(0, rentalCar.getDaysRented());
		    assertTrue(30.0 == rentalCar.generateRate());
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		}
       
    }

    @Test
    public void testRent() {
        String VIN = "DU24H4";
        String brand = "Toyota";
        String model = "Corolla";
        int year = 1986;
        double mileage = 200000.0;
        Store location;
		try {
			location = new Store("City", "State");
			 RentalCar rentalCar = new RentalCar(VIN, brand, model, year, mileage, location);
		     Customer customer = new Customer("Takumi Fujiwara", 86, true);
		      rentalCar.rent(customer, 1);
		     assertFalse(rentalCar.isAvailable());
		     assertEquals(customer, rentalCar.getCurrentOwner());
		     assertEquals(1, rentalCar.getDaysRented());
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		} catch (RentalAvailibilityException e) {
			e.printStackTrace();
		}
       
    }

    	@Test
    	public void testReturnToLot() {
        String VIN = "DU24H4";
        String brand = "Toyota";
        String model = "Corolla";
        int year = 1986;
        double mileage = 200000.0;
        Store location;
		try {
			location = new Store("City", "State");
			RentalCar rentalCar = new RentalCar(VIN, brand, model, year, mileage, location);
	        Customer customer = new Customer("Takumi Fujiwara", 86, true);
	        rentalCar.rent(customer, 1);
	        rentalCar.returnToLot(location);
	        assertTrue(rentalCar.isAvailable());
	        assertEquals(0, rentalCar.getDaysRented());
	        assertNull(rentalCar.getCurrentOwner());
		} catch (ObjectOverLimitException e) {
			e.printStackTrace();
		} catch (RentalAvailibilityException e) {
			e.printStackTrace();
		}
        
    }
    	
    	@Test public void availibiltyExceptionTest() {
    		try {
    			RentalCar rental = new RentalCar("VIN", "Brand", "Model", 2000, 10000, null);
    			Customer c = new Customer("Name", 100, true);
    			Customer d = new Customer("Name", 99, true);
    			rental.rent(c, 10); //Will do nothing, should work
    			
    			assertTrue(rental.getCurrentOwner() == c);
    			assertTrue(rental.getDaysRented() == 10);
    			
    			rental.rent(d, 25); //Will throw error
    		} catch(ObjectOverLimitException e) {
    			e.printStackTrace();
    		} catch (RentalAvailibilityException e) {
				assertEquals(e.getMessage(),"This Brand Model is unavailable at this time. Please try again in 10 days." );
			}
    	}

	
}