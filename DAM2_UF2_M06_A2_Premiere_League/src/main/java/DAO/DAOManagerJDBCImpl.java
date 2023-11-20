package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
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

		    String line = bR.readLine(); // Avoiding the 1st line
		    line = bR.readLine(); 

		    while (line != null) {
		        String[] fields = line.split(",");

		        Team team = new Team(fields[0], fields[1], fields[2], fields[3]);
		        AddTeam(team);
		        line = bR.readLine(); 
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

		boolean success = true; // if everything is OK it'll return true
	    
	    // Using : try-with-resources to manage the Statement
	    try (CallableStatement callableStatement = 
	    		connection.prepareCall("{call AddMatch(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}")) {
	    
	    	try {
	    		// AutoCommit -> OFF
		        connection.setAutoCommit(false);

		        callableStatement.setString(1, oneMatch.getDiv());
		        callableStatement.setDate(2, oneMatch.getDate());
		        callableStatement.setTime(3, oneMatch.getTime());
		        
		        // Using the method GetTeamAbbreviation:
		        callableStatement.setString(4, oneMatch.getHomeTeamAbv());
		        callableStatement.setString(5, oneMatch.getAwayTeamAbv());
		        
		        callableStatement.setInt(6, oneMatch.getFthg());
		        callableStatement.setInt(7, oneMatch.getFtag());
		        callableStatement.setString(8, oneMatch.getFtr());
		        callableStatement.setInt(9, oneMatch.getHthg());
		        callableStatement.setInt(10, oneMatch.getHtag());
		        callableStatement.setString(11, oneMatch.getHtr());
		        callableStatement.setString(12, oneMatch.getReferee());
		        callableStatement.setInt(13, oneMatch.getHs());
		        callableStatement.setInt(14, oneMatch.getAs());
		        callableStatement.setInt(15, oneMatch.getHst());
		        callableStatement.setInt(16, oneMatch.getAst());
		        callableStatement.setInt(17, oneMatch.getHf());
		        callableStatement.setInt(18, oneMatch.getAf());
		        callableStatement.setInt(19, oneMatch.getHc());
		        callableStatement.setInt(20, oneMatch.getAc());
		        callableStatement.setInt(21, oneMatch.getHy());
		        callableStatement.setInt(22, oneMatch.getAy());
		        callableStatement.setInt(23, oneMatch.getHr());
		        callableStatement.setInt(24, oneMatch.getAr());
		        callableStatement.execute();

		        connection.commit(); // if there's no problems, it'll commit
		        
	    	} catch (SQLException e) {
		        success = false; // if there's a problem, it'll return false
		        connection.rollback(); // rollback cause we can have a problem with GetTeamAbbreviation
		        e.printStackTrace();
		    } finally { connection.setAutoCommit(true); /* AutoCommit -> ON */ }
	    	
	    } catch (SQLException e) { e.printStackTrace(); }
	    
	    return success;
	}

	@Override
	public void ImportMatches(String fileMatches) {
	    try (FileReader fR = new FileReader(fileMatches);
	         BufferedReader bR = new BufferedReader(fR);
	    ){
	        String line = bR.readLine(); // Avoiding the 1st line 
	        line = bR.readLine(); 

	        while (line != null) {
	        	
	            String[] fields = line.split(",");
	            String[] fieldsDate = fields[1].split("/");
	            
	            // Getting the YYYY-MM-DD
	            LocalDate localDate = LocalDate.of(Integer.parseInt(fieldsDate[2]),
                        Integer.parseInt(fieldsDate[1]),
                        Integer.parseInt(fieldsDate[0]));
	            
				// Conversion from LocalDate to SQL Date
				Date date = Date.valueOf(localDate);

				// Getting the time
	            Time time = Time.valueOf(fields[2] + ":00");
 
	            Match match = new Match(
	            		fields[0], date, time, GetTeamAbbreviation(fields[3]), GetTeamAbbreviation(fields[4]),
                        Integer.parseInt(fields[5]), 
                        Integer.parseInt(fields[6]),
                        fields[7], Integer.parseInt(fields[8]), 
                        Integer.parseInt(fields[9]),
                        fields[10], fields[11], Integer.parseInt(fields[12]),
                        Integer.parseInt(fields[13]), Integer.parseInt(fields[14]),
                        Integer.parseInt(fields[15]), Integer.parseInt(fields[16]),
                        Integer.parseInt(fields[17]), Integer.parseInt(fields[18]),
                        Integer.parseInt(fields[19]), Integer.parseInt(fields[20]),
                        Integer.parseInt(fields[21]), Integer.parseInt(fields[22]),
                        Integer.parseInt(fields[23]));

	            AddMatch(match);
	            line = bR.readLine(); 
	        }
	    } catch (IOException ex) {
	        ex.printStackTrace();
	    }
	}


	@Override
	public Match GetMatch(Date matchDay, Team home, Team away) {
	    Match match = null;
	    boolean found = false;

	    try (CallableStatement callableStatement = connection.prepareCall("{call GetMatch(?,?,?)}")) {

	    	// The INT parameters
	        callableStatement.setDate(1, matchDay);
	        callableStatement.setString(2, home.getAbv());
	        callableStatement.setString(3, away.getAbv());

	        found = callableStatement.execute();

	        // If there's any result
	        if (found) {
	            // Process the ResultSet
	            try (ResultSet resultSet = callableStatement.getResultSet()) {
	                // Try-With-Resources to process the ResultSet and autoClose it
	                if (resultSet.next()) {
	                    // if there's a match, create a Match object
	                    match = new Match(
	                            resultSet.getString("Division"),
	                            resultSet.getDate("DateOfMatch"),
	                            resultSet.getTime("TimeOfMatch"),
	                            resultSet.getString("HomeTeamAbv"),
	                            resultSet.getString("AwayTeamAbv"),
	                            resultSet.getInt("FTHG"),
	                            resultSet.getInt("FTAG"),
	                            resultSet.getString("FTR"),
	                            resultSet.getInt("HTHG"),
	                            resultSet.getInt("HTAG"),
	                            resultSet.getString("HTR"),
	                            resultSet.getString("Referee"),
	                            resultSet.getInt("HS"),
	                            resultSet.getInt("ASS"),
	                            resultSet.getInt("HST"),
	                            resultSet.getInt("AST"),
	                            resultSet.getInt("HF"),
	                            resultSet.getInt("AF"),
	                            resultSet.getInt("HC"),
	                            resultSet.getInt("AC"),
	                            resultSet.getInt("HY"),
	                            resultSet.getInt("AY"),
	                            resultSet.getInt("HR"),
	                            resultSet.getInt("AR"));
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return match;
	}


	@Override
	public int HomeGoals() {

		int totalHomeGoals = 0;

	    try (CallableStatement callableStatement = connection.prepareCall("CALL GetHomeGoals(?)")) {
	    	
	        callableStatement.registerOutParameter(1, Types.INTEGER); /* managing the OUT parameter */
	        callableStatement.execute();
	        totalHomeGoals = callableStatement.getInt(1);
	        
	    } catch (SQLException e) { e.printStackTrace(); }

	    return totalHomeGoals;
	}

	@Override
	public int AwayGoals() {

		int totalAwayGoals = 0;

	    try (CallableStatement callableStatement = connection.prepareCall("CALL GetAwayGoals(?)")) {
	    	
	        callableStatement.registerOutParameter(1, Types.INTEGER); /* managing the OUT parameter */
	        callableStatement.execute();
	        totalAwayGoals = callableStatement.getInt(1);
	        
	    } catch (SQLException e) { e.printStackTrace(); }

	    return totalAwayGoals;
	}

	@Override
	public ArrayList<Match> MatchesOfTeam(Team oneTeam) {
		
	    ArrayList<Match> arrayListOfMatches = new ArrayList<>();

	    try (CallableStatement callableStatement = 
	    		connection.prepareCall("{call GetMatchesOfTeam(?)}")) {

	        // Setting our IN parameter
	        callableStatement.setString(1, oneTeam.getAbv());
	        
	        boolean found = callableStatement.execute();

	        // If there's any result
	        if (found) {
	            // Process the ResultSet
	            try (ResultSet resultSet = callableStatement.getResultSet()) {
	                // Try-With-Resources to process the ResultSet and auto-close it
	                while (resultSet.next()) {
	                    // For each match, create a Match object and add it to the list
	                    Match match = new Match(
	                    		resultSet.getString("Division"),
	                            resultSet.getDate("DateOfMatch"),
	                            resultSet.getTime("TimeOfMatch"),
	                            resultSet.getString("HomeTeamAbv"),
	                            resultSet.getString("AwayTeamAbv"),
	                            resultSet.getInt("FTHG"),
	                            resultSet.getInt("FTAG"),
	                            resultSet.getString("FTR"),
	                            resultSet.getInt("HTHG"),
	                            resultSet.getInt("HTAG"),
	                            resultSet.getString("HTR"),
	                            resultSet.getString("Referee"),
	                            resultSet.getInt("HS"),
	                            resultSet.getInt("ASS"),
	                            resultSet.getInt("HST"),
	                            resultSet.getInt("AST"),
	                            resultSet.getInt("HF"),
	                            resultSet.getInt("AF"),
	                            resultSet.getInt("HC"),
	                            resultSet.getInt("AC"),
	                            resultSet.getInt("HY"),
	                            resultSet.getInt("AY"),
	                            resultSet.getInt("HR"),
	                            resultSet.getInt("AR"));

	                    arrayListOfMatches.add(match);
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return arrayListOfMatches;
	}


	@Override
	public int RedCards(Team oneTeam) {

		int totalRedCards = 0;

	    try (CallableStatement callableStatement = connection.prepareCall("{call GetRedCardsCount(?, ?)}")) {

	        // Set parameters for the stored procedure
	        callableStatement.setString(1, oneTeam.getAbv());
	        callableStatement.registerOutParameter(2, Types.INTEGER);

	        // Execute the stored procedure
	        callableStatement.execute();

	        // Retrieve the OUT parameter value
	        totalRedCards = callableStatement.getInt(2);

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return totalRedCards;
	}

	@Override
	public ArrayList<Team> TopRedCards() {
		
		ArrayList<Team> topTeams = new ArrayList<>();

	    try (CallableStatement callableStatement = connection.prepareCall("{call TopRedCards()}")) {
	        // No need to set parameters for this procedure
 
	        // Execute the stored procedure
	        callableStatement.execute();

	        // Process the ResultSet
	        try (ResultSet resultSet = callableStatement.getResultSet()) {
	            while (resultSet.next()) {
	                // Create a Team object for each team and add it to the list
	                Team team = new Team(
	                        resultSet.getString("club_name"),
	                        resultSet.getString("abv"),
	                        resultSet.getString("hex_code"),
	                        resultSet.getString("logo_link")
	                );
	                topTeams.add(team);
	            }
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return topTeams;
	}
	
	@Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

}
