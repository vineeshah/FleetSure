package project;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class Tests {
	
	@Test
	public void objectOverCreationTest() {
		
	}
	
	@Test
	public void invalidInputsTest() {
		
	}
	
	@Test
	public void generalIntegrationTest() {
		
	}
	
	@Test
	public void searchFunctionTest() {
		Inventory inventory = new Inventory();
		inventory.addVehicle( new Car("ABCD1234", "Toyota", "Prius", 2015, 101.0,null)); 
		inventory.addVehicle( new Car("ABCD1235", "Honda", "Accord", 2012, 50100.0,null)); 
		inventory.addVehicle( new Car("ABCD1236", "BMW", "Prius", 2011, 60100.0,null)); 
		inventory.addVehicle( new Car("ABCD1237", "Toyota", "Highlander", 2009, 17100.0,null)); 
		inventory.addVehicle( new Car("ABCD1238", "Honda", "Civic", 2010, 1180.0,null)); 
		inventory.addVehicle( new Car("ABCD1239", "Toyota", "Prius", 2016, 200.0,null)); 
		
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
}
