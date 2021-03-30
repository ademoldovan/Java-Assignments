package presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

public class Parser {

	public Parser() {
	}

	/**
	 * Citeste datele din fisierul text
	 * 
	 * @param filename - numele fisierului
	 * @return continutul fisierului text, linie cu linie, intr-un arrayList
	 */
	public static ArrayList<String> readFile(String filename) throws FileNotFoundException {
		File f = new File(filename);
		String fullPath = new String();
		if (f.exists()) {
			fullPath = f.getAbsolutePath();
		}
		File file = new File(fullPath);
		Scanner fileReader = new Scanner(file);
		ArrayList<String> line = new ArrayList<String>();
		String text = new String();
		while (fileReader.hasNextLine()) {
			text = fileReader.nextLine();
			line.add(text);
		}
		fileReader.close();
		return line;
	}

	/**
	 * Extrage comenzile din fisierul text
	 * 
	 * @param filename - numele fisierului
	 * @return apeleaza functiile din GenerateReports si creeza pdf-urile
	 */
	public void commands(String filename) throws FileNotFoundException, DocumentException {
		ArrayList<String> line = new ArrayList<String>();
		line = readFile(filename);
		GenerateReports gr = new GenerateReports();
		for (String i : line) {
			String[] values = i.split("[: ,]+");
			if (values[0].compareTo("Insert") == 0) {
				if (values[1].compareTo("client") == 0) {
					gr.insertClient(i);
				}
				if (values[1].compareTo("product") == 0) {
					gr.insertProduct(i);
				}
			}
			if (values[0].compareTo("Delete") == 0) {
				if (values[1].compareTo("client") == 0) {
					gr.deleteClient(i);
				}
				if (values[1].compareTo("product") == 0) {
					gr.deleteProduct(i);
				}
			}
			if (values[0].compareTo("Order") == 0) {
				gr.insertOrder(i);
				gr.createFactura(i);
			}
			if (values[0].compareTo("Report") == 0) {
				if (values[1].compareTo("client") == 0) {
					gr.reportClient();
				}
				if (values[1].compareTo("product") == 0) {
					gr.reportProduct();
				}
				if (values[1].compareTo("order") == 0) {
					gr.reportOrder();
				}
			}
		}
	}
}
