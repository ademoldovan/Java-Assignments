package dataLayer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import businessLayer.MenuItem;
import businessLayer.Order;

public class FileWriterClass {
	public FileWriterClass() {

	}

	public void generateBill(Order ord, ArrayList<MenuItem> menu) {
		try {
			File myObj = new File("BILL.txt");
			if (myObj.createNewFile()) {
			} else {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FileWriter myWriter = new FileWriter("BILL.txt");
			myWriter.write("		BILL \n");
			myWriter.write("Data:" + ord.getDate() + "\n");
			myWriter.write("Masa:" + ord.getTable() + "\n");
			myWriter.write("Produse comandate: \n");
			double suma = 0;
			for (MenuItem i : menu) {
				myWriter.write("-> " + i.getName() + ". . . . . " + i.computePrice() + "\n");
				suma = suma + i.computePrice();
			}
			myWriter.write("Suma de platit: " + suma);
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
