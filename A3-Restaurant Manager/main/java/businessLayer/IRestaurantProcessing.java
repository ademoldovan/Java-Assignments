package businessLayer;

public interface IRestaurantProcessing {

	public void deleteMenuItem(String item);

	public void editMenuItem(String nume, String pret);

	public void createNewOrder(Order order, String item);

	public void addItemToOrder(String data, int table, String item);

	public void generateBill(String table, String date);

	public void createNewBaseMenuItem(String nume, String pret);

	public void createNewCompositeMenuItem(String nume,String item);
}
