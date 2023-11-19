package DAO;

import java.sql.Date;
import java.util.ArrayList;
import MODEL.Match;
import MODEL.Team;

public class DAOManagerJDBCImpl implements DAOManager{

	@Override
	public boolean AddTeam(Team oneTeam) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ImportTeams(String fileTeams) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team GetTeam(String teamAbbreviation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String GetTeamAbbreviation(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean AddMatch(Match oneMatch) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void ImportMatches(String fileMatches) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Match GetMatch(Date matchDay, Team home, Team away) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int HomeGoals() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int AwayGoals() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int RedCards(Team oneTeam) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Team> TopRedCards() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
