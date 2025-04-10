package ca.ucalgary.part3groupprojectcpsc233;



import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;

import java.util.*;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class Data {

    private final HashMap<String, Player> player_lookup;

    private final ArrayList<Player> players;

    private final HashMap<String, Person> person_lookup;

    private final ArrayList<Person> personArrayList;

    private final ArrayList<Team> teams;

    private final HashMap<String, Team> team_lookup;

    /**
     * Initializes ArrayLists for all Objects, and lookups to be able to easily get their respective objects
     */
    public Data() {
        player_lookup = new HashMap<>();
        players = new ArrayList<>();
        personArrayList = new ArrayList<>();
        person_lookup = new HashMap<>();
        teams = new ArrayList<>();
        team_lookup = new HashMap<>();
    }

    /**
     * Stores new Team given name and String of members
     *
     * @param teamName Name of team. Checks are made to see names of all teams are unique
     * @param allPlayers ArrayList of usernames of all players inside that team. Must be 5 and not contain duplicates
     * @return False if a player was not found, and True if storing was successful, or edge cases such as duplicate players/team
     */
    public boolean storeNewTeam(String teamName, ArrayList<String> allPlayers) {
        ArrayList<Player> teamMembers = new ArrayList<>();
        int counter = 0;
        for (String username : allPlayers) {
            if (!checkIfPlayerExist(username)) { //check if player doesn't exist
                return false;
            } else {
                teamMembers.add(counter,querySpecificPlayer(username));
                counter++;
            }
        }
        // check to see that no team member is the same as another
        for (int i = 0; i< teamMembers.size(); i++){
            for (int j = i +1; j < teamMembers.size(); j++){
                if (teamMembers.get(i).equals(teamMembers.get(j))){
                    System.out.println("Duplicate team member found. Team not stored.");
                    return true; //since returning false means that a player didn't exist, we return true, but let user know
                }
            }
        }
        // check to see teamName is unique
        for (String teamNames : team_lookup.keySet()){
            if (Objects.equals(teamNames, teamName)){
                System.out.println("Team name must be unique. Team not stored.");
                return true;  //since returning false means that a player didn't exist, we return true, but let user know
            }
        }
        Team team = new Team(teamName, teamMembers);
        teams.add(team);
        team_lookup.put(teamName, team);
        return true;
    }

    /**
     * Stores new Person given username, Nationality and age
     *
     * @param username username of person
     * @param nationality Nationality of person
     * @param age age of person
     * @return false if storing was successful (person has unique name), true otherwise
     */
    public boolean storeNewPerson(String username, Nationality nationality, int age) {
        if (!checkIfPersonExist(username)) {
            Person person = new Person(username, nationality, age);
            personArrayList.add(person);
            person_lookup.put(username, person);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function used to simply add a Player initialized from Reader
     *
     * @param player Player initialized from Reader
     */
    public void storePlayerFromFile(Player player) { //used in reader
        players.add(player);
        player_lookup.put(player.getUsername(), player);
    }

    /**
     * Used to store Team initialized from Reader
     *
     * @param team Team initialized from Reader
     */
    public void storeTeamFromFile(Team team) { //used in reader
        String teamName = team.getTeamName();
        teams.add(team);
        team_lookup.put(teamName, team);
    }

    /**
     * Simply stores given Person initialized from Reader
     *
     * @param person Person initialized from Reader
     */
    public void storePersonFromFile(Person person) { //used in reader
        String username = person.getUsername();
        personArrayList.add(person);
        person_lookup.put(username, person);
    }

    /**
     * Helper function to check if a Person actually exists within Data, given username
     *
     * @param username username to check for
     * @return true if the person exists, false otherwise
     */
    public boolean checkIfPersonExist(String username) {
        return person_lookup.containsKey(username);
    }

    /**
     * Stores new player into Data given Person
     *
     * @param person Person you wish to register as a Player
     * @return true if storing was successful (Player did not already exist) and false otherwise
     */
    public boolean storeNewPlayer(Person person) {
        if (!checkIfPlayerExist(person.getUsername())){
            Player player = new Player(person.getUsername(), person.getNationality(), 0, 0, 0, 0, 0,0);
            players.add(player);
            player_lookup.put(person.getUsername(), player);
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Checks if another player with same username exists
     *
     * @param username username to check
     * @return true if a player does indeed exist with that same username, false otherwise
     */
    public boolean checkIfPlayerExist(String username) {
        return player_lookup.containsKey(username);
    }

    /**
     * Stores kills to given Player (username given, we then query Player with it)
     *
     * @param username username of Player
     * @param kills the amount of kills user wishes to set it to
     */
    public void storeKillsToPlayer(String username, int kills) {
        Player player = querySpecificPlayer(username);
        if (player == null) {
            System.out.printf("%s does not exist. Stats not stored\n", username);
            return;
        }
        if (kills < 0) {
            kills = 0;
        }
        player.setKills(kills);
        System.out.println("Stored");
    }

    /**
     * Stores assists to given Player
     *
     * @param username username of Player
     * @param assists the amount of assists user wishes to set it to
     */
    public void storeAssistsToPlayer(String username, int assists) {
        Player player = querySpecificPlayer(username);
        if (player == null) {
            System.out.printf("%s does not exist. Stats not stored\n", username);
            return;
        }
        if (assists < 0) {
            assists = 0;
        }
        player.setAssists(assists);
        System.out.println("Stored");
    }

    /**
     * Stores deaths to given Player
     *
     * @param username username of Player
     * @param deaths the amount of deaths user wishes to set it to
     */
    public void storeDeathsToPlayer(String username, int deaths) {
        Player player = querySpecificPlayer(username);
        if (player == null) {
            System.out.printf("%s does not exist. Stats not stored\n", username);
            return;
        }
        if (deaths < 0) {
            deaths = 0;
        }
        player.setDeaths(deaths);
        System.out.println("Stored");
    }

    /**
     * Stores ACS to given Player
     *
     * @param username username of Player
     * @param acs ACS user wants to set it to
     */
    public void storeACSToPlayer(String username, int acs) {
        Player player = querySpecificPlayer(username);
        if (player == null) {
            System.out.printf("%s does not exist. Stats not stored\n", username);
            return;
        }
        if (acs < 0) {
            acs = 0;
        }
        player.setAcs(acs);
        System.out.println("Stored");
    }

    /**
     * Stores ADR to given Player
     *
     * @param username username of Player
     * @param adr ADR user wants to set it to
     */
    public void storeADRToPlayer(String username, int adr) {
        Player player = querySpecificPlayer(username);
        if (player == null) {
            System.out.printf("%s does not exist. Stats not stored\n", username);
            return;
        }
        if (adr < 0) {
            adr = 0;
        }
        player.setAdr(adr);
        System.out.println("Stored");
    }

    /**
     * Returns and finds Person given username
     *
     * @param username username of Person
     * @return Person if person was found, null if no Person with name was found
     */
    public Person querySpecificPerson(String username){
        return person_lookup.get(username);
    }

    /**
     * Returns and finds Player given username
     *
     * @param username username of Player
     * @return
     */
    public Player querySpecificPlayer(String username) {
        return player_lookup.get(username);
    }


    /**
     * Returns ArrayList of all Persons
     *
     * @return ArrayList of all Persons, null if there are no people found
     */
    public ArrayList<Person> queryAllPersons() {
        if (personArrayList.isEmpty()) {
            return null;
        }
        return personArrayList;
    }

    /**
     * Returns ArrayList of all Players
     *
     * @return ArrayList of all Players, null if no Players registered
     */
    public ArrayList<Player> queryAllPlayers() {
        if (players.isEmpty()) {
            return null;
        }
        return players;
    }

    /**
     * Returns ArrayList of all Teams
     *
     * @return ArrayList of all Teams, null if no Teams found
     */
    public ArrayList<Team> queryAllTeams(){
        if (teams.isEmpty()){
            return null;
        }
        return teams;
    }

    /**
     * Returns ArrayList of all Players on a given team
     *
     * @param teamName name of team to query
     * @return ArrayList of all Players on a given team, null if no team has given name
     */
    public ArrayList<Player> queryAllPlayersOnTeam(String teamName){
        Team team = team_lookup.get(teamName);
        if (team == null){
            return null;
        }
        else{
            return team.getTeamMembers();
        }
    }

    /**
     * Returns all people with given nationality
     *
     * @param nationality String form of nationality once wishes to find
     * @return Null if there are no Persons stored, ArrayList of all people with given nationality otherwise (could be empty)
     */
    public ArrayList<Person> findPeopleWithNationality(String nationality) {
        if (personArrayList.isEmpty()) {
            return null;
        }
        Nationality nationalityActual = Nationality.getNationality(nationality);
        ArrayList<Person> people = new ArrayList<>();
        for (Person person : personArrayList) {
            if (person.getNationality().equals(nationalityActual)) {
                people.add(person);
            }
        }
        return people;
    }

    /**
     * Find Player with highest stat given appropriate stat comparator
     *
     * @param comparator The comparator one wishes to uses to find Player with highest of that stat
     * @return Player with highest stat, null if no Players are registered
     */
    public Player findHighestBasedOn(Comparator<Player> comparator){
        if (players.isEmpty()){
            return null;
        }
        return Collections.max(players, comparator);

    }

    /**
     * Returns ArrayList of all Persons over a certain age
     *
     * @param age age to find people with an age equal to or greater than it
     * @return ArrayList of all People over given age, null if no people are stored at all
     */
    public ArrayList<Person> findPeopleOverAge(int age) {
        if (personArrayList.isEmpty()) {
            return null;
        }
        ArrayList<Person> people = new ArrayList<>();
        for (Person person : personArrayList) {
            if (person.getAge() >= age) {
                people.add(person);
            }
        }
        return people;
    }

    /**
     * Finds top 5 best players based off of ACS
     *
     * @return Array of the 5 players with the highest ACS, null if there are less than 5 players being stored
     */
    public Player[] findRecommendedTeam() {
        //Less than 5 players available
        if(players.size()<5){
            return null;
        }
        Player[] recommended = new Player[5];
        Collections.sort(players);
        for(int i=0;i<5;i++){
            recommended[i]=players.get(i);
        }
        return recommended;
    }

    public void reset(){
        player_lookup.clear();
        players.clear();
        person_lookup.clear();
        personArrayList.clear();
        teams.clear();
        team_lookup.clear();
    }


}
