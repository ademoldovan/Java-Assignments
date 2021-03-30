package main;

import java.io.FileNotFoundException;
import com.itextpdf.text.DocumentException;
import presentation.Parser;

public class MainClass {

	public static void main(String [] args) throws DocumentException {	
	
		Parser p = new Parser();
		try {
			p.commands("commands.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
}
