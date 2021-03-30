package main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import businessLayer.BaseProduct;
import businessLayer.ControllerAdmin;
import businessLayer.ControllerWaiter;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerializator;
import presentationLayer.AdministratorGraphicalUserInterface;
import presentationLayer.ChefGraphicalUserInterface;
import presentationLayer.WaiterGraphicalUserInterface;

public class MainClass {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		MenuItem m = new BaseProduct("Apa plata", 2);
		MenuItem m1 = new BaseProduct("Tiramisu", 12);
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		try {
			RestaurantSerializator.serialization(m);
			RestaurantSerializator.serialization(m1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			menu = RestaurantSerializator.deserialization();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HashMap<Order, ArrayList<MenuItem>> orders = new HashMap<Order, ArrayList<MenuItem>>(); 
		Order o = new Order("12/12/2020", 1);
		orders.put(o, menu);
		
		WaiterGraphicalUserInterface w = new WaiterGraphicalUserInterface();
		AdministratorGraphicalUserInterface a = new AdministratorGraphicalUserInterface();
		
		Restaurant r = new Restaurant(menu,orders);
		ControllerWaiter c = new ControllerWaiter(w,r);
		ControllerAdmin ca = new ControllerAdmin(a, r);
		
		ChefGraphicalUserInterface chef = new ChefGraphicalUserInterface();
		r.addObserver(chef);
	}
}






















