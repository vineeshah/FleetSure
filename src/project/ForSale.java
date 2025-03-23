package project;

public interface ForSale {
	public void sell(Customer customer, double price);
	
	public double appraiseValue();
	
	public void transferOwnership(Customer customer);
	
	public void removeFromInventory();
	
}
