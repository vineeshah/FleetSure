package project;

public interface Rentable {
	public double generateRate();
	
	public void returnToLot(Store store);
	
	public boolean rent(Customer customer, int days);
	
	public double calculateLateFees(int days);
			
	public void extendRental(int days);
	
	public int getDaysRented();
	
	public void setDaysRented(int days);
}
