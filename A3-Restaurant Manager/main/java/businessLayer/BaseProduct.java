package businessLayer;

public class BaseProduct extends MenuItem{
	
	public BaseProduct(String nume, double price) {
		super(nume,"base",price);
	}
	
	public double computePrice() {
		return this.getPrice();
	}
	
}
