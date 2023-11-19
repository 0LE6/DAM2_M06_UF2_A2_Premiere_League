package TEST;

import DAO.DAOManager;
import DAO.DAOManagerFactory;
import MODEL.Team;

public class MainTest {

	public static void main(String[] args) {

		// Here I'll do the test for all methods
		final String CLUBS_FILE = "clubs.csv";
		final String RESULTS_FILE = "results.csv";
		
		
		DAOManagerFactory daoFac = new DAOManagerFactory();
		DAOManager dao = daoFac.createDAOManager();
		
		// Methods 1 & 2
		//dao.ImportTeams(CLUBS_FILE);
		
		// Method 3
		System.out.println(dao.GetTeam("CRY"));
		
		// Method 4

	}

}
