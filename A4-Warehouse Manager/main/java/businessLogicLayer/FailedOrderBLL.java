package businessLogicLayer;

import java.util.ArrayList;

import dataAccessLayer.FailedOrderDAO;
import model.FailedOrder;

public class FailedOrderBLL {

	private FailedOrderDAO failedOrderDAO;

	
	public FailedOrderBLL() {
		failedOrderDAO = new FailedOrderDAO();
	}

	/**
	 * Apeleaza functia findAll pentru clasa FailedOrder
	 * 
	 * @param 
	 * @return un arrayList de obiecte de tipul FailedOrder
	 */
	public ArrayList<FailedOrder> reportFailedOrder() {
		ArrayList<FailedOrder> o = new ArrayList<FailedOrder>();
		o = failedOrderDAO.findAll();
		return o;
	}
}
