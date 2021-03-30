package presentationLayer;

import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class AdministratorGraphicalUserInterface {

	private JFrame frame;
	private JLabel label;

	private JTextField name1;
	private JTextField price1;
	private JTextField price2;
	private JTextField text;

	public JComboBox<String> editItem;
	public JComboBox<String> deleteItem;
	public JComboBox<String> existingItemForAdd;
	public JComboBox<String> deletingIngredient;

	private JButton buttonAdd;
	private JButton buttonEdit;
	private JButton buttonDelete;
	private JButton buttonDisplayMenu;
	private JButton buttonAddItemToNewItem;
	private JButton buttonDeleteIngredient;

	private JRadioButton r1;
	private JRadioButton r2;
	// private JButton buttonDone;

	public AdministratorGraphicalUserInterface() {

		frame = new JFrame("Administartor");
		frame.setSize(750, 320);
		frame.setVisible(true);
		frame.setLayout(null);
		// ADD
		label = new JLabel("ADD");
		label.setBounds(110, 10, 60, 30);
		frame.add(label);

		label = new JLabel("Name:");
		label.setBounds(20, 50, 80, 30);
		frame.add(label);

		name1 = new JTextField();
		name1.setBounds(70, 50, 140, 29);
		frame.add(name1);

		r1 = new JRadioButton("Base");
		r2 = new JRadioButton("Composite");
		r1.setBounds(30, 90, 80, 30);
		r2.setBounds(150, 90, 100, 30);
		ButtonGroup bg = new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		frame.add(r1);
		frame.add(r2);

		text = new JTextField();
		text.setBounds(115, 95, 2, 130);
		frame.add(text);

		label = new JLabel("Price:");
		label.setBounds(10, 120, 80, 30);
		frame.add(label);

		price1 = new JTextField();
		price1.setBounds(45, 122, 60, 25);
		frame.add(price1);

		existingItemForAdd = new JComboBox<String>();
		existingItemForAdd.setBounds(130, 120, 145, 29);
		frame.add(existingItemForAdd);

		buttonAddItemToNewItem = new JButton("Add to item");
		buttonAddItemToNewItem.setBounds(150, 170, 100, 30);
		// frame.add(buttonAddItemToNewItem);

		buttonAdd = new JButton("Add item");
		buttonAdd.setBounds(85, 230, 100, 30);
		frame.add(buttonAdd);

		text = new JTextField();
		text.setBounds(280, 5, 2, 280);
		frame.add(text);

		// EDIT
		label = new JLabel("EDIT");
		label.setBounds(365, 10, 60, 30);
		frame.add(label);

		label = new JLabel("Name:");
		label.setBounds(290, 50, 80, 30);
		frame.add(label);

		editItem = new JComboBox<String>();
		editItem.setBounds(330, 50, 150, 29);
		frame.add(editItem);

		label = new JLabel("Price:");
		label.setBounds(290, 90, 80, 30);
		frame.add(label);

		price2 = new JTextField();
		price2.setBounds(330, 90, 60, 26);
		frame.add(price2);

		label = new JLabel("(for base product)");
		label.setBounds(390, 90, 110, 25);
		frame.add(label);

		label = new JLabel("Ingredients (for composite product):");
		label.setBounds(290, 130, 250, 25);
		frame.add(label);

		deletingIngredient = new JComboBox<String>();
		deletingIngredient.setBounds(300, 155, 180, 28);
		frame.add(deletingIngredient);

		buttonDeleteIngredient = new JButton("Delete ingredient");
		buttonDeleteIngredient.setBounds(320, 190, 140, 25);
		frame.add(buttonDeleteIngredient);
		buttonEdit = new JButton("Edit item");
		buttonEdit.setBounds(340, 230, 100, 30);
		frame.add(buttonEdit);
 
		text = new JTextField();
		text.setBounds(500, 5, 2, 280);
		frame.add(text);

		// DELETE
		label = new JLabel("DELETE");
		label.setBounds(600, 10, 60, 30);
		frame.add(label);

		label = new JLabel("Name:");
		label.setBounds(520, 50, 80, 30);
		frame.add(label);

		deleteItem = new JComboBox<String>();
		deleteItem.setBounds(570, 50, 150, 29);
		frame.add(deleteItem);

		buttonDelete = new JButton("Delete item");
		buttonDelete.setBounds(565, 100, 100, 30);
		frame.add(buttonDelete);

		text = new JTextField();
		text.setBounds(505, 150, 240, 2);
		frame.add(text);

		buttonDisplayMenu = new JButton("Display menu");
		buttonDisplayMenu.setBounds(540, 210, 150, 30);
		frame.add(buttonDisplayMenu);

		frame.repaint();
	}

	public String getPriceEdit() {
		return price2.getText();
	}
	
	public String getItemComboEdit() {
		return (String) editItem.getSelectedItem();
	}
	
	public String getDeletingIngredient() {
		return (String) deletingIngredient.getSelectedItem();
	}
	
	public String getDeletingItem() {
		return (String) deleteItem.getSelectedItem();
	}
	
	public String getNumeAdd() {
		return name1.getText();
	}

	public String getPriceAdd() {
		return price1.getText();
	}

	public String getItemComboBoxAdd() {
		return (String) existingItemForAdd.getSelectedItem();
	}

	public int getTip() {
		if (r1.isSelected()) {
			return 1;
		}
		if (r2.isSelected()) {
			return 2;
		}
		return 0;
	}

	public void addItemButtonActionListener(ActionListener actionListener) {
		buttonAdd.addActionListener(actionListener);
	}

	public void addEditButtonActionListener(ActionListener actionListener) {
		buttonEdit.addActionListener(actionListener);
	}

	public void addDeleteButtonActionListener(ActionListener actionListener) {
		buttonDelete.addActionListener(actionListener);
	}

	public void addDisplayMenuButtonActionListener(ActionListener actionListener) {
		buttonDisplayMenu.addActionListener(actionListener);
	}

	public void addItemToNewItemButtonActionListener(ActionListener actionListener) {
		buttonAddItemToNewItem.addActionListener(actionListener);
	}

	public void addDeleteIngredientButtonActionListener(ActionListener actionListener) {
		buttonDeleteIngredient.addActionListener(actionListener);
	}

}
