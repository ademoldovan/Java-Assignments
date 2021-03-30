package dataAccessLayer;

import model.Orders;

public class OrderDAO extends DBOperations<Orders>{

	/**
	 * creaza partea proprie a instructiunii insert pentru tabela Order
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	public String createInsert(Orders order) {
		String s = super.createInsert(order);
		StringBuilder insert = new StringBuilder();
		insert.append(s);
		insert.append("(");
		insert.append(order.getNrOrder());
		insert.append(", '");
		insert.append(order.getNumeC());
		insert.append("', '");
		insert.append(order.getNumeP());
		insert.append("', ");
		insert.append(order.getCantitate());
		insert.append(")");
		return insert.toString();
	}
}
