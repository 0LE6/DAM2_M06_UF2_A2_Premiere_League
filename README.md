# Premier2223 DAOManager Project

This project focuses on managing data from the 2022-2023 Premier League season using the DAO pattern and a MySQL database named 1premiereleague.

**Note:**
This project is part of the Data Access Module at Institut Montili (Girona).

## Step by Step

### Step A: Create the Database

1. Open phpMyAdmin.
2. Create the `1premiereleague` database.
3. Create the following tables:

   #### TEAM Table
   - **Primary Key:** Team Abbreviation
   - *Fields:*
     - Team Abbreviation (Primary Key)
     - Club Name
     - Hex Code
     - Logo Link

   #### MATCH Table
   - **Primary Key:** Match Date + Home Team Abbreviation + Away Team Abbreviation
   - *Foreign Keys:*
     - Home Team Abbreviation (Reference to TEAM)
     - Away Team Abbreviation (Reference to TEAM)
   - *Fields:*
     - Match Date
     - Home Team Abbreviation
     - Away Team Abbreviation
     - (Add other fields as per Kaggle dataset)

![image](https://github.com/0LE6/DAM2_M06_UF2_A2_Premiere_League/assets/135649528/c6710bca-4011-41c1-9a44-85e62d25ccf8)

### Step B: Create the Maven Project

1. Create a Maven project.
2. Add the necessary dependencies.

### Step C: Models and DAOManager

1. **Model Classes:**
   - `Team`: Represents information about a team.
   - `Match`: Represents information about a match.

2. **DAOManager Interface:**
   - Defines necessary CRUD operations.

3. **DAOManagerJDBCImpl:**
   - Implements operations defined in the interface using the DAO pattern.
   - Includes stored procedures for specific operations.

### Implemented Methods in DAOManagerJDBCImpl:

- `public boolean AddTeam(Team oneTeam)`
- `public void ImportTeams(String fileTeams)`
- `public Team GetTeam(String teamAbbreviation)`
- `public String GetTeamAbbreviation(String teamName)`
- `public boolean AddMatch(Match oneMatch)`
- `public void ImportMatches(String fileMatches)`
- `public Match GetMatch(Date matchDay, Team home, Team away)`
- `public int HomeGoals()`
- `public int AwayGoals()`
- `public ArrayList<Match> MatchesOfTeam(Team oneTeam)`
- `public int RedCards(Team oneTeam)`
- `public ArrayList<Team> TopRedCards()`

### Usage Example

```java
final String CLUBS_FILE = "clubs.csv";
final String RESULTS_FILE = "results.csv";


DAOManagerFactory daoFac = new DAOManagerFactory();
DAOManager dao = daoFac.createDAOManager();

// Methods 1 & 2
dao.ImportTeams(CLUBS_FILE);

// Method 3
System.out.println(dao.GetTeam("CRY"));

// Method 4
System.out.println(dao.GetTeamAbbreviation("Man City"));
