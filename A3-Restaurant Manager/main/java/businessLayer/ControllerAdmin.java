package businessLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import presentationLayer.AdministratorGraphicalUserInterface;

public class ControllerAdmin {

	private AdministratorGraphicalUserInterface view;
	private Restaurant model;

	public ControllerAdmin(AdministratorGraphicalUserInterface view, Restaurant model) {
		this.view = view;
		this.model = model;

		this.view.addDeleteButtonActionListener(new DeleteListener());
		this.view.addDeleteIngredientButtonActionListener(new DeleteIngredientListener());
		this.view.addDisplayMenuButtonActionListener(new DisplayMenuListener());
		this.view.addEditButtonActionListener(new EditButtonListener());
		this.view.addItemButtonActionListener(new AddItemListener());

		ArrayList<MenuItem> menu = model.displayMenuItems();
		// initializare combo box
		view.existingItemForAdd.removeAllItems();
		for (MenuItem i : menu) {
			view.existingItemForAdd.addItem(i.getName());
		}
		view.deletingIngredient.removeAllItems();
		for (MenuItem i : menu) {
			view.deletingIngredient.addItem(i.getName());
		}
		view.editItem.removeAllItems();
		for (MenuItem i : menu) {
			view.editItem.addItem(i.getName());
		}
		view.deleteItem.removeAllItems();
		for (MenuItem i : menu) {
			view.deleteItem.addItem(i.getName());
		}
	}

	// butonul pentru ADD item
	class AddItemListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = new String();
			String price = new String();
			String item = new String();
			int tip = 0;

			name = view.getNumeAdd();

			price = view.getPriceAdd();
			tip = view.getTip();
			item = view.getItemComboBoxAdd();
			if (tip == 1) {
				if (model.cautaInMeniu(name) == 0) {
					model.createNewBaseMenuItem(name, price);
					view.existingItemForAdd.addItem(name);
					view.deletingIngredient.addItem(name);
					view.editItem.addItem(name);
					view.deleteItem.addItem(name);
				}
			} else if (tip == 2) {				
				if (model.cautaInMeniu(name) == 0) {
					view.existingItemForAdd.addItem(name);
					view.deletingIngredient.addItem(name);
					view.editItem.addItem(name);
					view.deleteItem.addItem(name);
				}
				model.createNewCompositeMenuItem(name, item);
			}
		}
	}

	// butonul pentru a edita un produs
	class EditButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = new String();
			String price = new String();
			name = view.getItemComboEdit();
			price = view.getPriceEdit();
			model.editMenuItem(name, price);
		}
	}

	// butonul pentru delete ingredient
	class DeleteIngredientListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String name = new String();
			String ingredient = new String();
			name = view.getItemComboEdit();
			ingredient = view.getDeletingIngredient();
			model.deleteIngredient(name, ingredient);
		}
	}

	// butonul pentru DELETE item
	class DeleteListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String item = new String();
			item = view.getDeletingItem();
			model.deleteMenuItem(item);

			view.existingItemForAdd.removeItem(item);
			view.deletingIngredient.removeItem(item);
			view.editItem.removeItem(item);
			view.deleteItem.removeItem(item);
		}
	}

	// afiseaza meniul in tabel
	class DisplayMenuListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			ArrayList<MenuItem> menu = model.getMenu();
			JFrame frame = new JFrame();
			frame = new JFrame("Meniu");
			frame.setSize(400, 320);
			frame.setLocation(750, 20);
			frame.setVisible(true);

			String[][] data = new String[menu.size()][4];
			int j = 0;
			for (int k = 0; k < menu.size(); k++) {
				j = 0;
				for (MenuItem i : menu) {
					if (i instanceof CompositeProduct) {
						j = 0;
						data[k][j] = i.getName();
						data[k][j + 1] = i.getType();
						data[k][j + 2] = String.valueOf(i.computePrice());
						String s = new String();
						for (MenuItem m : ((CompositeProduct) i).getComposit()) {
							s = s + m.getName() + "; ";
						}
						data[k][j + 3] = s;
						k++;
					}
					if (i instanceof BaseProduct) {
						j = 0;
						data[k][j] = i.getName();
						data[k][j + 1] = i.getType();
						data[k][j + 2] = String.valueOf(i.getPrice());
						data[k][j + 3] = " - ";
						k++;
					}
				}
			}
			String[] column = new String[] { "Product", "Base/Composite", "Price", "Ingredients" };
			JTable jt = new JTable(data, column);
			jt.setBounds(30, 40, 200, 300);
			jt.setVisible(true);
			JScrollPane scrollPane = new JScrollPane(jt);
			frame.add(scrollPane);
			frame.repaint();
		}
	}

}
