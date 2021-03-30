package dataAccessLayer;

import model.Product;

public class ProductDAO extends DBOperations<Product> {

	/**
	 * creaza partea proprie a instructiunii findBy pentru tabela Product
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	protected String createFindBy(String nume) {
		String s = super.createFindBy(nume);
		StringBuilder sb = new StringBuilder();
		sb.append(s);
		sb.append("nume='");
		sb.append(nume);
		sb.append("'");
		return sb.toString();
	}
	
	/**
	 * creaza partea proprie a instructiunii insert pentru tabela product
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createInsert(Product produs) {
		String s = super.createInsert(produs);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append("('");
		insert.append(produs.getNume());
		insert.append("', ");
		insert.append(produs.getCantitate());
		insert.append(", ");
		insert.append(produs.getPret());
		insert.append(")");
		return insert.toString();
	}
	
	/**
	 * creaza partea proprie a instructiunii delete pentru tabela product
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createDelete(Product produs) {
		String s = super.createDelete(produs);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append(" nume=");
		insert.append("'");
		insert.append(produs.getNume());
		insert.append("'");
		return insert.toString();
	}
	
	/**
	 * creaza partea proprie a instructiunii update pentru tabela product
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createUpdate(Product produs) {
		String s = super.createUpdate(produs);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append("cantitate=");
		insert.append(produs.getCantitate());
		insert.append(" WHERE ");
		insert.append("nume = ");
		insert.append("'");
		insert.append(produs.getNume());	
		insert.append("' ");
		return insert.toString();
	}
	
}
