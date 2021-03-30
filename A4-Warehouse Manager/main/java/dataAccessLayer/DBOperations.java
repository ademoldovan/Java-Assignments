package dataAccessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Clients;
import model.Product;

public class DBOperations<T> {
	protected static final Logger LOGGER = Logger.getLogger(DBOperations.class.getName());

	private final Class<T> type;

	@SuppressWarnings("unchecked")
	public DBOperations() {
		this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}


	/**
	 * creaza partea comuna pentru toate tabelele din instructiunea SELECT
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	protected String createFindBy(String nume) {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE ");
		return sb.toString();
	}
	/**
	 * creaza partea comuna pentru toate tabelele din instructiunea INSERT
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	protected String createInsert(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO ");
		sb.append(type.getSimpleName());
		sb.append(" VALUES ");
		return sb.toString();
	}
	/**
	 * creaza partea comuna pentru toate tabelele din instructiunea UPDATE
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	protected String createUpdate(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE ");
		sb.append(type.getSimpleName());
		sb.append(" SET ");
		return sb.toString();
	}
	/**
	 * creaza partea comuna pentru toate tabelele din instructiunea DELETE
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	protected String createDelete(T t) {
		StringBuilder sb = new StringBuilder();
		sb.append("DELETE FROM ");
		sb.append(type.getSimpleName());
		sb.append(" WHERE ");
		return sb.toString();
	}
	/**
	 * creaza partea comuna pentru toate tabelele din instructiunea SELECT
	 * 
	 * @param 
	 * @return Stringul in care se retine query-ul
	 */
	private String createFindAll() {
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT ");
		sb.append(" * ");
		sb.append(" FROM ");
		sb.append(type.getSimpleName());
		return sb.toString();
	}

	/**
	 * Executa query-ul: select* from numeTabela
	 * 
	 * @param 
	 * @return datele din clasa care apeleaza functia
	 */
	public ArrayList<T> findAll() {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = createFindAll();
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			resultSet = statement.executeQuery();
			return createObjects(resultSet);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll" + e.getMessage());
		} finally {
			ConnectionDB.close(resultSet);
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
		return null;
	}

	/**
	 * Executa query-ul: insert into numeTabele values(?,?,?)
	 * 
	 * @param un obiect generalizat de tipul T
	 * @return 
	 */
	public void insert(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createInsert(t);
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		} finally {
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
	}

	/**
	 * Executa query-ul: update numeTabela set numeColoana = valoare
	 * 
	 * @param un obiect generalizat de tipul T
	 * @return 
	 */
	public void update(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createUpdate(t);
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} finally {
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
	}

	/**
	 * Executa query-ul: delete from NumeTabela where numeColoana = valoare
	 * 
	 * @param un obiect generalizat de tipul T
	 * @return 
	 */
	public T delete(T t) {
		Connection connection = null;
		PreparedStatement statement = null;
		String query = createDelete(t);
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
		} finally {
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
		return null;
	}

	/**
	 * Executa query-ul: select* from numeTabela where numeColoana = valoare
	 * 
	 * @param String nume - valoarea care se cauta in tabela
	 * @return 
	 */
	public Product findBy(String nume) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = createFindBy(nume);
		Product p = new Product();
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				String n = rs.getString("nume");
				int cantitate = rs.getInt("cantitate");
				double pret = rs.getDouble("pret");
				p.setCantitate(cantitate);
				p.setNume(n);
				p.setPret(pret);
			}
			return p;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findBy " + e.getMessage());
		} finally {
			ConnectionDB.close(rs);
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
		return null;
	}
	
	public Clients findByC(String nume) {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		String query = createFindBy(nume);
		Clients c = new Clients();
		try {
			connection = ConnectionDB.getConnection();
			statement = connection.prepareStatement(query);
			rs = statement.executeQuery();
			while (rs.next()) {
				String n = rs.getString("nume");
				String a = rs.getString("adresa");
				c.setNume(n);
				c.setAdresa(a);
			}
			return c;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:findBy " + e.getMessage());
		} finally {
			ConnectionDB.close(rs);
			ConnectionDB.close(statement);
			ConnectionDB.close(connection);
		}
		return null;
	}

	/**
	 * Executa query-ul: select* from numeTabela where numeColoana = valoare
	 * 
	 * @param 
	 * @return 
	 */
	private ArrayList<T> createObjects(ResultSet resultSet) {
		ArrayList<T> list = new ArrayList<T>();
		try {
			while (resultSet.next()) {
				T instance = null;
				try {
					instance = type.getDeclaredConstructor().newInstance();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
				for (Field field : type.getDeclaredFields()) {
					Object value = resultSet.getObject(field.getName());
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
					Method method = propertyDescriptor.getWriteMethod();
					method.invoke(instance, value);
				}
				list.add(instance);
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return list;
	}


}
