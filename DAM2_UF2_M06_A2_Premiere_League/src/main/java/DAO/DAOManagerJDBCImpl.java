package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
		
	    boolean success = true; // if everything is OK it'll return true
	    
	    // Using : try-with-resources to manage the Statement
	    try (CallableStatement callableStatement = connection.prepareCall("{call AddTeam(?, ?, ?, ?)}")) {
	        
	    	try {
	    		// AutoCommit -> OFF
		        connection.setAutoCommit(false);

		        callableStatement.setString(1, oneTeam.getClubName());
		        callableStatement.setString(2, oneTeam.getAbv());
		        callableStatement.setString(3, oneTeam.getHexCode());
		        callableStatement.setString(4, oneTeam.getLogoLink());
		        callableStatement.execute();

		        connection.commit(); // if there's no problem, it'll commit
		        
	    	} catch (SQLException e) {
		        success = false; // if there's a problem, it'll return false
		        e.printStackTrace();
		    } finally { connection.setAutoCommit(true); /* AutoCommit -> ON */ }
	    	
	    } catch (SQLException e) { e.printStackTrace(); }
	    
	    return success;
	}


	@Override
	public void ImportTeams(String fileTeams) {

		try (
			FileInputStream fis = new FileInputStream(fileTeams);
		    BufferedReader bR = new BufferedReader(new InputStreamReader(fis))) {

		    String line;
		    bR.readLine(); // Avoiding the 1st line

		    while ((line = bR.readLine()) != null) {
		        String[] fields = line.split(",");

		        Team team = new Team(fields[0], fields[1], fields[2], fields[3]);
		        AddTeam(team);
		    }
 
			} catch (IOException ex) { ex.printStackTrace(); }
	}

	@Override
	public Team GetTeam(String teamAbbreviation) {
	    Team team = null;
	    boolean found = false;
	    
	    try (CallableStatement callableStatement = connection.prepareCall("call GetTeam(?)")) {
	        // Passing the IN parameter
	        callableStatement.setString(1, teamAbbreviation);
	        found = callableStatement.execute();

	        // If there's any result
	        if (found) {
	            // we enter here and process it w/ ResultSet
	            try (ResultSet resultSet = callableStatement.getResultSet()) {
	                // Try-With-Resources to process the ResultSet and autoClose it
	                if (resultSet.next()) { /* if there's next, create a team */
	                    team = new Team(
	                            resultSet.getString("club_name"),
	                            teamAbbreviation,
	                            resultSet.getString("hex_code"),
	                            resultSet.getString("logo_link"));
	                }
	            }
	        }
	    } catch (SQLException e) { e.printStackTrace(); }

	    return team;
	}


	@Override
	public String GetTeamAbbreviation(String teamName) {
		
	    String equipName = null;

	    try (CallableStatement callableStatement = connection.prepareCall("{call GetTeamAbbreviation(?,?)}")) {
	        
	    	callableStatement.setString(1, teamName);
	        callableStatement.registerOutParameter(2, Types.VARCHAR);

	        callableStatement.execute(); 

	        equipName = callableStatement.getString(2);
	        
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	    }

	    return equipName;
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
