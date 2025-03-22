package project;


public class Employee {
    private final String name;
    private final int id;
    private final Store store;
    static int currentObjects;

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public Employee(String name, int id, Store store) throws ObjectOverLimitException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Please select an employee. Try again.");
        }
        if (store == null) {
            throw new IllegalArgumentException("Try again the store you are looking for did not appear. Please fill in the prompts.");
        }
        this.name = name;
        this.id = id;
        this.store = store;
        
        currentObjects++;
		
		if(currentObjects > 100) {
			throw new ObjectOverLimitException("employee");
		}
    }

    public void processPayment(Order order) {
    	
        double total = order.getAmountDue();
        System.out.println(name + "your total amount was" + total );
    }

    public Order createOrder(Customer customer, Vehicle vehicle) {
        if (customer == null) {
            throw new IllegalArgumentException("Try again fill out your name please.");
        }
        if (vehicle == null) {
            System.out.println("Try again tell us the model vehicle you are looking for");
            return null;
        }

        Order order = new Order(customer, this, store);
        System.out.println(name + " new order for" + customer.getName() + " customer id:" + customer.getMemberId() + " from " + store.toString());
        return order;
    }

    public void applyDiscount(Order order, double discountRate) {
    	
        if (discountRate <= 0 || discountRate > 0.2) {
            System.out.println("Try again the discount applied does not work.");
            return;
        }

        double newTotal = order.getAmountDue() * (1 - discountRate);
        order.setAmountDue(newTotal);
        System.out.println(name + " took off " + (discountRate * 100) + "% and your new total is : $" + newTotal);
    }

    public boolean validateCustomer(Customer customer) {

        boolean activeMembership = customer.activeMembership();
        if (activeMembership) {
            System.out.println(name + customer.getName() + "You qualify for our rentals");
        } else {
            System.out.println(name + customer.getName() + "Sorry you do not qualify for our rentals try again");
        }
        return activeMembership;
    }
    
}
