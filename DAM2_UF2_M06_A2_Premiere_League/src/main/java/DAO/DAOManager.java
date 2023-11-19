package DAO;
import MODEL.Match;
import MODEL.Team;
import java.sql.Date;
import java.util.ArrayList;

public interface DAOManager extends AutoCloseable{

	public boolean AddTeam(Team oneTeam);
	public void ImportTeams(String fileTeams);
	public Team GetTeam(String teamAbbreviation);
	public String GetTeamAbbreviation(String teamName);
	public boolean AddMatch(Match oneMatch);
	public void ImportMatches(String fileMatches);
	public Match GetMatch(Date matchDay, Team home, Team away);
	public int HomeGoals();
	public int AwayGoals();
	public ArrayList<Match> MatchesOfTeam(Team oneTeam);
	public int RedCards(Team oneTeam);
	public ArrayList<Team> TopRedCards();
	
}

/*public interface EmployeeDAO extends AutoCloseable {

	public void add(Employee empl);
	public void update(Employee empl);
	public void delete(int id);
	public Employee findById(int id);
	public Employee[] getAllEmployees();
}
*/