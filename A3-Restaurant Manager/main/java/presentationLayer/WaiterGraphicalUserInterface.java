package presentationLayer;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WaiterGraphicalUserInterface {

	private JFrame frame;
	private JLabel table;
	private JLabel menu;
	private JLabel order;
	private JTextField textTable;
	private JTextField textDate;
	public JComboBox<String> selectItem;
	private JButton buttonAdd;
	private JButton buttonCreateOrder;
	private JButton buttonCreateBill;
	private JButton buttonDisplayOrders;

	public WaiterGraphicalUserInterface() {

		frame = new JFrame("Waiter");
		frame.setSize(500, 350);
		frame.setLocation(20, 350);
		frame.setVisible(true);
		frame.setLayout(null);

		order = new JLabel("Create order");
		order.setBounds(20, 10, 200, 30);
		frame.add(order);

		table = new JLabel("Table:");
		table.setBounds(20, 50, 100, 30);
		frame.add(table);

		textTable = new JTextField();
		textTable.setBounds(70, 50, 80, 29);
		frame.add(textTable);

		table = new JLabel("Date:");
		table.setBounds(170, 50, 100, 30);
		frame.add(table);

		textDate = new JTextField();
		textDate.setBounds(210, 50, 80, 29);
		frame.add(textDate);

		menu = new JLabel("Menu:");
		menu.setBounds(20, 90, 80, 30);
		frame.add(menu);

		selectItem = new JComboBox<String>();
		selectItem.setBounds(70, 90, 250, 30);
		frame.add(selectItem);

		buttonAdd = new JButton("Add item");
		buttonAdd.setBounds(350, 90, 120, 30);
		frame.add(buttonAdd);

		buttonCreateOrder = new JButton("Create order");
		buttonCreateOrder.setBounds(350, 140, 120, 30);
		frame.add(buttonCreateOrder);

		buttonCreateBill = new JButton("Create bill");
		buttonCreateBill.setBounds(350, 190, 120, 30);
		frame.add(buttonCreateBill);

		buttonDisplayOrders = new JButton("Display orders");
		buttonDisplayOrders.setBounds(350, 240, 120, 30);
		frame.add(buttonDisplayOrders);

		frame.repaint();
	}

	public String getTable() {
		return textTable.getText();
	}

	public String getDate() {
		return textDate.getText();
	}

	public String getSelectedItem() {
		return (String) selectItem.getSelectedItem();
	}

	public void addItemButtonActionListener(ActionListener actionListener) {
		buttonAdd.addActionListener(actionListener);
	}

	public void addCreateOrderButtonActionListener(ActionListener actionListener) {
		buttonCreateOrder.addActionListener(actionListener);
	}

	public void addCreateBillButtonActionListener(ActionListener actionListener) {
		buttonCreateBill.addActionListener(actionListener);
	}

	public void addDisplayButtonActionListener(ActionListener actionListener) {
		buttonDisplayOrders.addActionListener(actionListener);
	}

	public void addSelectItemButtonActionListener(ActionListener actionListener) {
		selectItem.addActionListener(actionListener);
	}
}
