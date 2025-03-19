package project;

import java.util.ArrayList;

public class Store {
	private Inventory inventory;
	private String city;
	private String state;
	private ArrayList<Employee> employees;

}
	public Store(String city, String state) {
        this.city = city;
        this.state = state;
        this.inventory = new Inventory();
        this.employees = new ArrayList<>(); 
}

