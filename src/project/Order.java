package project;
import java.util.ArrayList;

public class Order {
	private ArrayList<Vehicle> itemsInOrder;
	private Customer customer;
	private double amountDue;
	private Employee employee;
	private Store location;
	
	//Constructor
	public Order(Customer customer, Employee employee, Store location) {
		this.customer = customer;
		this.employee = employee;
		this.location = location;
		this.amountDue = 0;
		this.itemsInOrder = new ArrayList<>();
	}
	//Getters & Setters
	public double getAmountDue() {
		return amountDue;
	}
	
	public void setAmountDue(double amount) {
		this.amountDue = amount;
	}
	//Methods 
	public double calculateTax() {
		return this.amountDue*.0725;
	}
	
	public void addFees(int amount) {
		this.amountDue += amount;
	}
	
	public void removeFees(int amount) {
		this.amountDue -= amount;
	}
	
	public void addToOrder(Vehicle vehicle) {
		itemsInOrder.add(vehicle);
	}
	
	public void acceptPayment() {
		amountDue = 0;
		System.out.println("The order has been paid for.");
	}
	
	public double calculateAmountDue() {
		for(Vehicle vehicle: itemsInOrder) {
			if(vehicle instanceof Rentable) {
				Rentable rental = (Rentable) vehicle;
				amountDue += rental.generateRate()* rental.getDaysRented();
			} else if (vehicle instanceof ForSale) {
				amountDue += vehicle.calculateValue();
			}
		}
		return amountDue;
	}
}
