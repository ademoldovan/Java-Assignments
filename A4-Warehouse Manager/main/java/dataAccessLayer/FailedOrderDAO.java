package dataAccessLayer;

import model.FailedOrder;

public class FailedOrderDAO extends DBOperations<FailedOrder>{
	
	/**
	 * creaza partea proprie a instructiunii insert pentru tabela FailedOrder
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createInsert(FailedOrder order) {
		String s = super.createInsert(order);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append("(");
		insert.append(order.getNrOrder());
		insert.append(", '");
		insert.append(order.getNumeC());
		insert.append("', '");
		insert.append(order.getNumeP());
		insert.append("')");
		return insert.toString();
	}
	
}
