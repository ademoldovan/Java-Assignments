package businessLogicLayer;

import java.util.ArrayList;
import dataAccessLayer.OrderDAO;
import model.Orders;

public class OrderBLL {

	private OrderDAO orderDAO;

	public OrderBLL() {
		orderDAO = new OrderDAO();
	}
	
	/**
	 * Apeleaza functia insert pentru clasa Order
	 * 
	 * @param 
	 * @return
	 */
	public void insertOrder(Orders order) {
		orderDAO.insert(order);
	}

	/**
	 * Apeleaza functia findAll pentru clasa Order
	 * 
	 * @param 
	 * @return un arrayList de obiecte de tipul Order
	 */
	public ArrayList<Orders> reportOrder() {
		ArrayList<Orders> o = new ArrayList<Orders>();
		o = orderDAO.findAll();
		return o;
	}
}