package dataLayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import businessLayer.MenuItem;

public class RestaurantSerializator implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void serialization(MenuItem item) throws IOException {
		FileOutputStream fileOutputStream = new FileOutputStream("restaurant.ser");
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
		objectOutputStream.writeObject(item.getName());
		objectOutputStream.flush();
		objectOutputStream.close();
		fileOutputStream.close();
	}

	@SuppressWarnings("unchecked")
	public static ArrayList<MenuItem> deserialization() throws ClassNotFoundException, IOException, FileNotFoundException {

		ArrayList<MenuItem> menu = new ArrayList<MenuItem>();

		FileInputStream fileInputStream = new FileInputStream("restaurant.ser");
		ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
		menu = (ArrayList<MenuItem>) objectInputStream.readObject();
		objectInputStream.close();
		fileInputStream.close();

		return menu;
	}
}
