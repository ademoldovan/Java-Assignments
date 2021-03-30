package businessLogicLayer;

import java.util.ArrayList;
import dataAccessLayer.ClientDAO;
import model.Clients;

public class ClientBLL {

	private ClientDAO clientDAO;

	public ClientBLL() {
		clientDAO = new ClientDAO();
	}
	/**
	 * Apeleaza functia findBy pentru clasa Order
	 * 
	 * @param 
	 * @return un arrayList de obiecte de tipul Order
	 */
	public Clients findClient(String nume) {
		Clients c = clientDAO.findByC(nume);
		return c;
	}
	/**
	 * Apeleaza functia insert pentru clasa Client
	 * 
	 * @param 
	 * @return
	 */
	public void insertClient(Clients client) {
		clientDAO.insert(client);
	}
	/**
	 * Apeleaza functia delete pentru clasa Client
	 * 
	 * @param 
	 * @return 
	 */
	public void deleteClient(Clients client) {
		clientDAO.delete(client);
	}

	/**
	 * Apeleaza functia findALL pentru clasa Client
	 * 
	 * @param 
	 * @return un arrayList de obiecte de tipul Client
	 */
	public ArrayList<Clients> reportClient() {
		ArrayList<Clients> cl = new ArrayList<Clients>();
		cl = clientDAO.findAll();
		return cl;
	}

}
