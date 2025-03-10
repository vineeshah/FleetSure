
public interface Rentable {
	public double generateRate();
	
	public void returnToLot();
	
	public boolean rent();
	
	public double calculateLateFees(int days);
			
	public void extendRental(int days);
}
