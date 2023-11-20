# Premier2223 DAOManager Project

Este proyecto se centra en la gestión de datos de la temporada 2022-2023 de la Premier League utilizando el patrón DAO y una base de datos MySQL llamada 1premiereleague.

## Paso a Paso

### Paso A: Crear la Base de Datos

1. Abre phpMyAdmin.
2. Crea la base de datos `1premiereleague`.
3. Crea las siguientes tablas:

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


### Paso B: Crear el Proyecto Maven

1. Crea un proyecto Maven.
2. Agrega las dependencias necesarias.

### Paso C: Modelos y DAOManager

1. **Model Classes:**
   - `Team`: Representa la información de un equipo.
   - `Match`: Representa la información de un partido.

2. **DAOManager Interface:**
   - Define las operaciones CRUD necesarias.

3. **DAOManagerJDBCImpl:**
   - Implementa las operaciones definidas en la interfaz utilizando el patrón DAO.
   - Incluye procedimientos almacenados para operaciones específicas.

### Métodos Implementados en DAOManagerJDBCImpl:

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

### Ejemplo de Uso

```java
// Crea una instancia de DAOManagerJDBCImpl
DAOManager daoManager = new DAOManagerJDBCImpl();

// Ejecuta y prueba cada método
daoManager.AddTeam(new Team("Arsenal", "ARS", "#FF0000", "arsenal_logo.png"));
daoManager.ImportTeams("teams.csv");
// (Continúa con las demás operaciones)

// Cierra la conexión al finalizar
daoManager.close();

![image](https://github.com/0LE6/DAM2_M06_UF2_A2_Premiere_League/assets/135649528/04207c66-af8a-491b-8707-c1290f533d31)
