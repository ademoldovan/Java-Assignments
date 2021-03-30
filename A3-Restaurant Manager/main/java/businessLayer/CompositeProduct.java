package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem {

	private ArrayList<MenuItem> composit = new ArrayList<MenuItem>();

	public CompositeProduct(String name) {
		super(name, "composite",0);
	}

	public void addComposit(MenuItem newItem) {
		composit.add(newItem);
	}

	public double computePrice() {
		double price = 0;
		for (MenuItem m : composit) {
			price += m.computePrice();
		}
		this.setPrice(price);
		return price;
	}

	public ArrayList<MenuItem> getComposit() {
		return composit;
	}

	public void setComposit(ArrayList<MenuItem> composit) {
		this.composit = composit;
	}
	
}
