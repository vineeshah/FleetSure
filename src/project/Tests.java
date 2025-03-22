package project;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class Tests {
	
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
	
	
}
