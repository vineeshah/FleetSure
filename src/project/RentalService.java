package project;

import java.util.ArrayList;

public class RentalService {
	private ArrayList<Car> cinvent;
	private ArrayList<Bike> binvent;
	
	public void addCar(Car car) {
		cinvent.add(car);
	}
	public void addBike(Bike bike) {
		binvent.add(bike);
	}
	
	
	

}
