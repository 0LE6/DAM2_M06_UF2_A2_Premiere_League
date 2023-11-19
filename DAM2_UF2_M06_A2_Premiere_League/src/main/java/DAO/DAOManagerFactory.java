package DAO;

import java.sql.SQLException;

public class DAOManagerFactory {

	public DAOManager createDAOManager() throws SQLException {
		return new DAOManagerJDBCImpl();
	}
}