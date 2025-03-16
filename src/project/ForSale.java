package project;

public interface ForSale {
	public void sell(Customer customer, double price);
	
	public double appraiseValue();
	
	public boolean transferOwnership(Customer customer);
	
	boolean removeFromInventory();
	
}
