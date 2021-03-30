package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.stream.Stream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import businessLogicLayer.ClientBLL;
import businessLogicLayer.OrderBLL;
import businessLogicLayer.ProductBLL;
import model.Clients;
import model.Orders;
import model.Product;

public class GenerateReports {
	private int client = 1;
	private int order = 1;
	private int produs = 1;
	private int factura = 1;
	private int failedOrder = 1;

	public int getFactura() {
		return factura;
	}

	public void setFactura(int factura) {
		this.factura = factura;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int report) {
		this.client = report;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getProdus() {
		return produs;
	}

	public void setProdus(int produs) {
		this.produs = produs;
	}

	/**
	 * Creaza factura
	 * 
	 * @param line - comanda care trebuie executata
	 * @return creeaza pdf-ul cu factura
	 */
	public void createFactura(String line) throws FileNotFoundException, DocumentException {
		String[] values = line.split("[: ,]+");
		String nume = values[1] + " " + values[2];
		String produs = values[3];
		int cantitate = Integer.parseInt(values[4]);

		// caut produsul ca am nevoie de pret
		ProductBLL cd = new ProductBLL();
		Product find = new Product();
		find = cd.findProduct(produs);

		if (cantitate <= find.getCantitate()) {
			double pret = find.getPret();
			pret = pret * cantitate;
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("Factura" + this.factura + ".pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			Chunk chunk0 = new Chunk("Data/Ora:  " + timestamp, font);
			Chunk chunk = new Chunk("Factura numarul: " + this.factura, font);	
			Chunk chunk1 = new Chunk("Nume client:  " + nume, font);
			Chunk chunk2 = new Chunk("Produs cumparat:  " + produs, font);
			Chunk chunk3 = new Chunk("Cantitate:  " + cantitate, font);
			Chunk chunk4 = new Chunk("Pret total:  " + pret, font);
			document.add(new Paragraph(chunk0));
			document.add(new Paragraph(chunk));
			document.add(new Paragraph(chunk1));
			document.add(new Paragraph(chunk2));
			document.add(new Paragraph(chunk3));
			document.add(new Paragraph(chunk4));
			document.close();
			this.factura++;
			int newCantitate = find.getCantitate() - cantitate;
			find.setCantitate(newCantitate);
			cd.updateProduct(find);
		} else {
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream("FailedOrder" + this.failedOrder + ".pdf"));
			document.open();
			Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
			Chunk chunk = new Chunk("Comanda esuata pentru clientul " + nume + ". Nu sunt suficiente produse in stoc.", font);
			document.add(new Paragraph(chunk));
			document.close();
			this.failedOrder++;
		}
	}

	/**
	 * Insereaza in tabela client
	 * 
	 * @param line - comanda care trebuie executata
	 * @return 
	 */
	public void insertClient(String line) {
		String[] values = line.split("[: ,]+");
		String nume = new String();
		nume = values[2] + " " + values[3];
		Clients client = new Clients();
		client.setNume(nume);
		client.setAdresa(values[4]);
		ClientBLL cb = new ClientBLL();
		Clients find = new Clients();
		find = cb.findClient(nume);
		if (find.getNume() == null) {
			cb.insertClient(client);
		}
	}
	/**
	 * Insereaza in tabela product
	 * 
	 * @param line - comanda care trebuie executata
	 * @return 
	 */
	public void insertProduct(String line) {
		String[] values = line.split("[: ,]+");
		Product produs = new Product();
		produs.setNume(values[2]);
		produs.setCantitate(Integer.parseInt(values[3]));
		produs.setPret(Double.parseDouble(values[4]));
		ProductBLL cd = new ProductBLL();
		Product find = new Product();
		find = cd.findProduct(values[2]);
		if (find.getNume() == null) {
			cd.insertProduct(produs);
		} else {
			Product produs1 = new Product();
			produs1 = produs;
			produs1.setCantitate(produs.getCantitate() + find.getCantitate());
			cd.updateProduct(produs1);
		}
	}

	/**
	 * Insereaza in tabela Order
	 * 
	 * @param line - comanda care trebuie executata
	 * @return 
	 */
	public void insertOrder(String line) {
		String[] values = line.split("[: ,]+");
		Orders order = new Orders();
		order.setNumeC(values[1] + values[2]);
		order.setNumeP(values[3]);
		order.setCantitate(Integer.parseInt(values[4]));
		OrderBLL ob = new OrderBLL();
		ob.insertOrder(order);
	}

	/**
	 * Sterge din tabela client
	 * 
	 * @param line - comanda care trebuie executata
	 * @return 
	 */
	public void deleteClient(String line) {
		String[] values = line.split("[: ,]+");
		String nume = new String();
		nume = values[2] + " " + values[3];
		Clients client = new Clients();
		client.setNume(nume);
		client.setAdresa(values[4]);
		ClientBLL cb = new ClientBLL();
		cb.deleteClient(client);
	}
	/**
	 * Sterge din tabela product
	 * 
	 * @param line - comanda care trebuie executata
	 * @return 
	 */
	public void deleteProduct(String line) {
		String[] values = line.split("[: ,]+");
		String nume = new String();
		nume = values[2];
		Product produs = new Product();
		produs.setNume(nume);
		ProductBLL cd = new ProductBLL();
		cd.deleteProduct(produs);
	}

	/**
	 * Afiseaza toate datele din tabela client
	 * 
	 * @param line - datele pentru comanda care trebuie executata
	 * @return 
	 */
	public void reportClient() throws DocumentException, FileNotFoundException {
		ClientBLL cb = new ClientBLL();
		ArrayList<Clients> list = new ArrayList<Clients>();
		list = cb.reportClient();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("reportClient" + this.client + ".pdf"));
		this.client++;
		document.open();
		PdfPTable table = new PdfPTable(2);
		Stream.of("Nume", "Adresa").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
		for (Clients i : list) {
			table.addCell(i.getNume());
			table.addCell(i.getAdresa());
		}
		document.add(table);
		document.close();
	}

	/**
	 * Afiseaza toate datele din tabela product
	 * 
	 * @param line - datele pentru comanda care trebuie executata
	 * @return 
	 */
	public void reportProduct() throws DocumentException, FileNotFoundException {
		ProductBLL cb = new ProductBLL();
		ArrayList<Product> list = new ArrayList<Product>();
		list = cb.reportProduct();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("reportProduct" + this.produs + ".pdf"));
		this.produs++;
		document.open();
		PdfPTable table = new PdfPTable(3);
		Stream.of("Nume", "Cantitate", "Pret").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
		for (Product i : list) {
			table.addCell(i.getNume());
			table.addCell(String.valueOf(i.getCantitate()));
			table.addCell(String.valueOf(i.getPret()));
		}
		document.add(table);
		document.close();
	}

	/**
	 * Afiseaza toate datele din tabela order
	 * 
	 * @param line - datele pentru comanda care trebuie executata
	 * @return 
	 */
	public void reportOrder() throws DocumentException, FileNotFoundException {
		OrderBLL cb = new OrderBLL();
		ArrayList<Orders> list = new ArrayList<Orders>();
		list = cb.reportOrder();
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream("reportOrder" + this.order + ".pdf"));
		this.order++;
		document.open();
		PdfPTable table = new PdfPTable(4);
		Stream.of("NrOrder", "NumeClient", "NumeProdus", "Cantitate").forEach(columnTitle -> {
			PdfPCell header = new PdfPCell();
			header.setBackgroundColor(BaseColor.LIGHT_GRAY);
			header.setBorderWidth(2);
			header.setPhrase(new Phrase(columnTitle));
			table.addCell(header);
		});
		for (Orders i : list) {
			table.addCell(String.valueOf(i.getNrOrder()));
			table.addCell(i.getNumeC());
			table.addCell(i.getNumeP());
			table.addCell(String.valueOf(i.getCantitate()));
		}
		document.add(table);
		document.close();
	}

}
