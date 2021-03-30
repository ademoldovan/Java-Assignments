package businessLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import businessLayer.Restaurant;
import presentationLayer.WaiterGraphicalUserInterface;

public class ControllerWaiter {

	private WaiterGraphicalUserInterface view;
	private Restaurant model;

	public ControllerWaiter(WaiterGraphicalUserInterface view, Restaurant model) {
		this.view = view;
		this.model = model;
		this.view.addCreateOrderButtonActionListener(new CreateOrderListener());
		this.view.addItemButtonActionListener(new AddItemListener());
		this.view.addCreateBillButtonActionListener(new CreateBillListener());
		this.view.addDisplayButtonActionListener(new DisplayListener());
		
		view.selectItem.removeAllItems();
		ArrayList<MenuItem> meniu = model.displayMenuItems();
		for (MenuItem i : meniu) {
			view.selectItem.addItem(i.getName());
		}
	}

	class CreateOrderListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String table = new String();
			String date = new String();
			String item = new String();
			table = view.getTable();
			date = view.getDate();
			item = view.getSelectedItem();
			Order order = new Order(date, Integer.parseInt(table));		
			model.createNewOrder(order, item);
		}
	}

	public class AddItemListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String table = new String();
			String date = new String();
			String item;
			table = view.getTable();
			date = view.getDate();
			item = view.getSelectedItem();			
			model.addItemToOrder(date, Integer.parseInt(table),item);
		}
	}

	public class CreateBillListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String table = new String();
			String date = new String();
			table = view.getTable();
			date = view.getDate();			
			model.generateBill(table,date);
		}
	}

	public class DisplayListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			HashMap<Order, ArrayList<MenuItem>> orders = model.getOrder();
			
			JFrame frame = new JFrame();
			frame = new JFrame("Orders");
			frame.setSize(400, 320);
			frame.setLocation(500, 20);
			frame.setVisible(true);

			String[][] data = new String[orders.size()][4];
			int j = 0;
			for (int k = 0; k < orders.size(); k++) {
				for (Order i : orders.keySet()) {
					j=0;
					data[k][j] = String.valueOf(i.getOrderID());
					data[k][j+1] =  String.valueOf(i.getTable());
					data[k][j+2] = i.getDate();
					String s = new String();
					for(MenuItem m : orders.get(i)) {
						s= s + m.getName() + "; ";
					}
					data[k][j+3] = s;
					k++;
				}
			}
			String[] column = new String[] { "ID", "Table", "Date", "Order" };
			JTable jt = new JTable(data, column);
			jt.setBounds(30, 40, 200, 300);
			jt.setVisible(true);
			JScrollPane scrollPane = new JScrollPane(jt);
			frame.add(scrollPane);
			frame.repaint();

		}

	}


}
