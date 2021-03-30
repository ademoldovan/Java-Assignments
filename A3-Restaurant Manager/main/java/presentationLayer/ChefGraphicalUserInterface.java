package presentationLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLabel;

import businessLayer.MenuItem;
import businessLayer.Order;

@SuppressWarnings("deprecation")
public class ChefGraphicalUserInterface implements Observer {

	private HashMap<Order, ArrayList<MenuItem>> orders;
	private JFrame frame;
	private JLabel text;

	public ChefGraphicalUserInterface() {
		frame = new JFrame("Chef");
		frame.setSize(400, 250);
		frame.setLocation(770, 10);
		frame.setVisible(true);
		frame.setLayout(null);
		text = new JLabel();
		text.setBounds(10, 10, 700, 90);
		frame.add(text);
		frame.repaint();
	}

	public void update(Observable o, Object arg) {
		String message = "S-a realizat o comanda pentru preparatul: " + ((MenuItem) arg).getName();
		text.setText(message);
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}

}
