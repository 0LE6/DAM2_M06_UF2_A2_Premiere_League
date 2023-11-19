package TEST;

import DAO.DAOManager;
import DAO.DAOManagerFactory;

public class ManagerTestInteractive {

	public static void main(String[] args) {
		
		// Here I'll do the test for all methods
		final String CLUBS_FILE = "clubs.csv";
		final String RESULTS_FILE = "results.csv";
		
		
		DAOManagerFactory daoFac = new DAOManagerFactory();
		DAOManager dao = daoFac.createDAOManager();
		
		dao.ImportTeams(CLUBS_FILE);


	}
}
