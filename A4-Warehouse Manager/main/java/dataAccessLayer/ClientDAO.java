package dataAccessLayer;

import model.Clients;
public class ClientDAO extends DBOperations<Clients> {

	/**
	 * creaza partea proprie a instructiunii insert pentru tabela Client
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createInsert(Clients client) {
		String s = super.createInsert(client);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append("('");
		insert.append(client.getNume());
		insert.append("', '");
		insert.append(client.getAdresa());
		insert.append("')");
		return insert.toString();
	}
	/**
	 * creaza partea proprie a instructiunii findBy pentru tabela Client
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
	 * creaza partea proprie a instructiunii delete pentru tabela Client
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createDelete(Clients client) {
		String s = super.createDelete(client);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append(" nume=");
		insert.append("'");
		insert.append(client.getNume());
		insert.append("'");
		return insert.toString();
	}
	
}
