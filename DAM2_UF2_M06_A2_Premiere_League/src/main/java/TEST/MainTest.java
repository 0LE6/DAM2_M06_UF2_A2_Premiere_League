package TEST;

import DAO.DAOManager;
import DAO.DAOManagerFactory;
import MODEL.Match;
import MODEL.Team;

import java.sql.Date;

public class MainTest {

	public static void main(String[] args) {
		
		final String CLUBS_FILE = "clubs.csv";
		final String RESULTS_FILE = "results.csv";
		
		
		DAOManagerFactory daoFac = new DAOManagerFactory();
		DAOManager dao = daoFac.createDAOManager();
		
		// Methods 1 & 2
		//dao.ImportTeams(CLUBS_FILE);
		
		// Method 3
		System.out.println(dao.GetTeam("CRY"));
		
		// Method 4
		System.out.println(dao.GetTeamAbbreviation("Man City"));
		
		// Method 5 & 6
		//dao.ImportMatches(RESULTS_FILE);
		
		// Method 
		Date dateOfMatch = Date.valueOf("2022-08-27");
		Team homeTeam = dao.GetTeam("LIV");
		Team awayTeam = dao.GetTeam("BOU");
		System.out.println(dao.GetMatch(dateOfMatch, homeTeam, awayTeam));
		
		// Methods 8 & 9
		System.out.println("The Total Home Goals are --> " + dao.HomeGoals());
		System.out.println("The Total Away Goals are --> " + dao.AwayGoals());
		
		// Method 10
		int count = 0;
		Team testTeam = dao.GetTeam("FUL");
		for (Match match : dao.MatchesOfTeam(testTeam)) {
			
			count++;
			System.out.println(count + "->" + match);
		}
		
		
		// Method 11
		System.out.println(
				"Red Cards for " + testTeam.getClubName() + " -> " +
				dao.RedCards(testTeam) );
		
		
		// Method 12
		for (Team teamWithMoreRedCards : dao.TopRedCards()) {
			
			System.out.println("Team w/ more Red Cards: " + teamWithMoreRedCards);
		}
	}

}
