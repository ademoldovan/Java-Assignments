package businessLayer;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Observable;

import dataLayer.FileWriterClass;
import dataLayer.RestaurantSerializator;

@SuppressWarnings("deprecation")
  
public class Restaurant extends Observable implements Serializable, IRestaurantProcessing {

	private static final long serialVersionUID = 1L;
	private ArrayList<MenuItem> menu; // meniul
	private HashMap<Order, ArrayList<MenuItem>> orders; // comenzile

	public Restaurant() {

	}

	public Restaurant(ArrayList<MenuItem> menu, HashMap<Order, ArrayList<MenuItem>> orders) {
		this.menu = menu;
		this.orders = orders;
	}

	public void createNewOrder(Order order, String item) {
		MenuItem it = null;
		for (MenuItem i : menu) {
			if (i.getName().compareTo(item) == 0) {
				it = i;
			}
		}
		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
		menu.add(it);
		this.orders.put(order, menu);
		setChanged();
		notifyObservers(it);
	}

	public ArrayList<MenuItem> displayMenuItems() {
		return this.menu;
	}

	public void addItemToOrder(String data, int table, String item) {
		MenuItem it = null;
		for (MenuItem i : menu) {
			if (i.getName().compareTo(item) == 0) {
				it = i;
			}
		}
		for (Order i : orders.keySet()) {
			if (i.getTable() == table && i.getDate().compareTo(data) == 0) {
				orders.get(i).add(it);
			}
		}
		setChanged();
		notifyObservers(it);
	}

	public void createNewBaseMenuItem(String nume, String pret) {
		MenuItem bp = new BaseProduct(nume, Double.parseDouble(pret));
		this.menu.add(bp);
		try {
			RestaurantSerializator.serialization(bp);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createNewCompositeMenuItem(String nume, String item) {
		MenuItem it = null;
		int ok = 0;
		// cautam composite product dupa nume
		for (MenuItem i : menu) {
			if (i.getName().compareTo(nume) == 0) {
				it = i;
				ok = 1; // exista deja, adaugam ingrediente la compozitie
			}
		}
		MenuItem base = null;
		// cautam item ul
		for (MenuItem i : menu) {
			if (i.getName().compareTo(item) == 0) {
				base = i;
			}
		}
		if (ok == 0) {
			// nu exista , creeam un produs nou
			CompositeProduct cm = new CompositeProduct(nume);
			cm.addComposit(base);
			this.menu.add(cm);
		} else if (ok == 1) {
			// daca exista adaugam itemul la compozitie
			if (it instanceof CompositeProduct) {
				((CompositeProduct) it).addComposit(base);
			}
		}
		try {
			RestaurantSerializator.serialization(it);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int cautaInMeniu(String nume) {
		for (MenuItem i : this.menu) {
			if (i.getName().compareTo(nume) == 0) {
				return 1;
			}
		}
		return 0;
	}

	public void deleteIngredient(String nume, String ingredient) {
		for (MenuItem i : this.menu) {
			if (i.getName().compareTo(nume) == 0) {
				if (i instanceof CompositeProduct) {
					try {
						for (MenuItem j : ((CompositeProduct) i).getComposit()) {
							if (j.getName().compareTo(ingredient) == 0) {
								((CompositeProduct) i).getComposit().remove(j);
							}
						}
					} catch (ConcurrentModificationException e) {
					}
				}
			}
		}
	}

	public void editMenuItem(String nume, String pret) {
		for (MenuItem i : this.menu) {
			if (i.getName().compareTo(nume) == 0) {
				i.setPrice(Double.parseDouble(pret));
			}
		}
	}

	public void generateBill(String table, String data) {

		Order ord = null;
		ArrayList<MenuItem> produse = new ArrayList<MenuItem>();

		for (Order i : orders.keySet()) {
			if (i.getTable() == Integer.parseInt(table) && i.getDate().compareTo(data) == 0) {
				ord = i;
				produse = orders.get(i);
			}
		}
		FileWriterClass f = new FileWriterClass();
		f.generateBill(ord, produse);
	}

	public void deleteMenuItem(String item) {
		try {
			for (MenuItem i : this.menu) {
				if (i.getName().compareTo(item) == 0) {
					menu.remove(i);
				}
			}
		} catch (ConcurrentModificationException e) {

		}
	}

	public ArrayList<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(ArrayList<MenuItem> menu) {
		this.menu = menu;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrder() {
		return orders;
	}

	public void setOrder(HashMap<Order, ArrayList<MenuItem>> order) {
		this.orders = order;
	}

}
