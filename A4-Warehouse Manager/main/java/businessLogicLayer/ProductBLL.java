package businessLogicLayer;

import java.util.ArrayList;
import dataAccessLayer.ProductDAO;
import model.Product;

public class ProductBLL {

	private ProductDAO productDAO;

	public ProductBLL() {
		productDAO = new ProductDAO();
	}

	/**
	 * Apeleaza functia insert pentru clasa Client
	 * 
	 * @param 
	 * @return
	 */
	public void insertProduct(Product product) {
		productDAO.insert(product);
	}

	/**
	 * Apeleaza functia delete pentru clasa Client
	 * 
	 * @param 
	 * @return 
	 */
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}
	
	/**
	 * Apeleaza functia update pentru clasa Product
	 * 
	 * @param 
	 * @return
	 */
	public void updateProduct(Product product) {
		productDAO.update(product);
	}
	
	/**
	 * Apeleaza functia findBy pentru clasa Product
	 * 
	 * @param 
	 * @return un obiect de tipul Client
	 */
	public Product findProduct(String nume) {
		Product p = productDAO.findBy(nume);
		return p;
	}

	/**
	 * Apeleaza functia findALL pentru clasa Product
	 * 
	 * @param 
	 * @return un arrayList de obiecte de tipul Product
	 */
	public ArrayList<Product> reportProduct() {
		ArrayList<Product> p = new ArrayList<Product>();
		p = productDAO.findAll();
		return p;
	}

}
