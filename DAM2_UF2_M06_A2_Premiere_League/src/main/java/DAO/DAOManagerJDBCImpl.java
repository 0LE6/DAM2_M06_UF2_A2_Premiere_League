package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.CallableStatement;

import MODEL.Match;
import MODEL.Team;

public class DAOManagerJDBCImpl implements DAOManager{

	private final String JDBC_URL = "jdbc:mysql://localhost:3306/1premiereleague?serverTimezone=UTC";
	private final String USER = "root";
	private final String PASSWORD = "";
	private Connection connection;
	
	// Constructor to make an connection 
	public DAOManagerJDBCImpl() {
		try {
			this.connection = DriverManager.getConnection(
					this.JDBC_URL, 
					this.USER, 
					this.PASSWORD);
		} 
		catch (SQLException e) {
            e.printStackTrace();
        }	
	}
	
	@Override
	public boolean AddTeam(Team oneTeam) {
	    boolean success = true; // if everything is OK i'll return true

	    // Using : try-with-resources
	    try (CallableStatement callableStatement 
	    		= connection.prepareCall("{call AddTeam(?, ?, ?, ?)}")) {
	        
	    	// AutoCommit -> OFF
	        connection.setAutoCommit(false);

	        callableStatement.setString(1, oneTeam.getClubName());
	        callableStatement.setString(2, oneTeam.getAbv());
	        callableStatement.setString(3, oneTeam.getHexCode());
	        callableStatement.setString(4, oneTeam.getLogoLink());

	        connection.commit(); // if there's no problem, it'll commit
	        
	    } catch (SQLException e) {
	        success = false; // if there's a problem, it'll return false
	        e.printStackTrace();
	    }

	    return success;
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
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
