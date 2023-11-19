package DAO;

public class DAOManagerFactory {

	public DAOManager createDAOManager() {
		return new DAOManagerJDBCImpl();
	}
}