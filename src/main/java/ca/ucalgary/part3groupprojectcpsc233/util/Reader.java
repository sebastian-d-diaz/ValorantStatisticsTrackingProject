package ca.ucalgary.part3groupprojectcpsc233.util;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;

import java.io.*;
import java.util.ArrayList;


public class Reader {
    //constants for indexing
    public static final int INDEX_NAME = 0;
    public static final int INDEX_NATIONALITY = 1;
    public static final int INDEX_AGE = 2;
    public static final int INDEX_KILLS = 1;
    public static final int INDEX_ASSISTS = 2;
    public static final int INDEX_DEATHS = 3;
    public static final int INDEX_ACS = 4;
    public static final int INDEX_ADR = 5;
    public static final int INDEX_TEAMNAME = 0;
    public static final int INDEX_PLAYER1 = 1;
    public static final int INDEX_PLAYER2 = 2;
    public static final int INDEX_PLAYER3 = 3;
    public static final int INDEX_PLAYER4 = 4;
    public static final int INDEX_PLAYER5 = 5;

    public static boolean GUIsave(Data data, File file){
        //Checking if there is data
        FileWriter fw = null;
        BufferedWriter bfw = null;
        try{
            fw = new FileWriter(file);
            bfw = new BufferedWriter(fw);
            //saving people
            ArrayList<Person> people = data.queryAllPersons();
            bfw.write("true\n");//there are people
            bfw.write(data.queryAllPersons().size()+"\n");//Number of people
            for(Person person:people){
                bfw.write(person.getUsername()+","+String.valueOf(person.getNationality())+","+person.getAge()+"\n");//"username,nationality,age\n" for each person
            }
            //saving Players
            ArrayList<Player> players = data.queryAllPlayers();
            if(players!=null) {//checking if there are players
                bfw.write("true+\n");
                bfw.write(players.size() + "\n");//Number of players
                for (Player player : players) {
                    bfw.write(player.getUsername() + "," + player.getAge() + "," + player.getKills() + "," + player.getAssists() + "," + player.getDeaths() + "," + player.getAcs() + "," + player.getAdr() + "\n");//"username,age,kills,assists,deaths,acs,adr\n"
                }
            }
            else{
                bfw.write("false\n");//no players
            }
            //Saving Teams
            ArrayList<Team> teams = data.queryAllTeams();
            if(teams!=null) {//checking if there are teams
                bfw.write("true\n");//there are players
                bfw.write(teams.size() + "\n");//number of teams
                for (Team team : teams) {
                    bfw.write(team.getTeamName() + ",");//writing team name, note a newline character is not present here
                    ArrayList<Player> playersOnTeam = team.getTeamMembers();
                    String usernameP1 = playersOnTeam.get(INDEX_PLAYER1).getUsername();
                    String usernameP2 = playersOnTeam.get(INDEX_PLAYER2).getUsername();
                    String usernameP3 = playersOnTeam.get(INDEX_PLAYER3).getUsername();
                    String usernameP4 = playersOnTeam.get(INDEX_PLAYER4).getUsername();
                    String usernameP5 = playersOnTeam.get(INDEX_PLAYER5).getUsername();
                    bfw.write(usernameP1 + "," + usernameP2 + "," + usernameP3 + "," + usernameP4 + "," + usernameP5 + "\n");
                }
            }
            else{
                bfw.write("false\n");//There are no players
            }


        }catch(IOException e){
            return false;
        }
        finally{
            try{
                fw.close();
                bfw.close();
            }catch(IOException e){
                return false;
            }
        }
        return true;
    }

    public static boolean GUIload(Data data, File file) {
        FileReader fr = null;
        BufferedReader bfr = null;
        try {
            fr = new FileReader(file);
            bfr = new BufferedReader(fr);
            //clearing existing data
            data.reset();
            //checking if file is empty
            String line=bfr.readLine();
            if(line==null){
                return false;//unable to read empty file
            }
            //Loading people data
            //checking if there are players
            if(!line.equals("true")){
                return false;//no players
            }
            line=bfr.readLine();
            int popoulationSize = Integer.parseInt(line);
            //Loading all people
            for(int pe=0;pe<popoulationSize;pe++){
                line=bfr.readLine();
                String[] splitLine=line.split(",");
                String name = splitLine[INDEX_NAME];
                String nationalityString = splitLine[INDEX_NATIONALITY];
                Nationality nationality = Nationality.getNationality(nationalityString); //convert string nationality into an enum
                int age = Integer.parseInt(splitLine[INDEX_AGE]);
                Person person = new Person(name, nationality, age); //create the person object
                data.storePersonFromFile(person); //store that person into the database
            }
            //Loading players
            //checking if there are players
            line = bfr.readLine();
            if(!line.equals("true")){
                return true;//no players therefore no teams
            }
            line = bfr.readLine();
            int playerCount = Integer.parseInt(line);
            for(int pl=0;pl<playerCount;pl++){
                line = bfr.readLine();
                String[] splitLine = line.split(",");
                Person person = data.queryAllPersons().get(pl);
                data.storeNewPlayer(person);
                //loading stats
                String name = splitLine[INDEX_NAME];
                Nationality nationality = data.querySpecificPerson(name).getNationality();
                int age = data.querySpecificPerson(name).getAge();
                int kills = Integer.parseInt(splitLine[INDEX_KILLS]);
                int assists = Integer.parseInt(splitLine[INDEX_ASSISTS]);
                int deaths = Integer.parseInt(splitLine[INDEX_DEATHS]);
                int acs = Integer.parseInt(splitLine[INDEX_ACS]);
                int adr = Integer.parseInt(splitLine[INDEX_ADR]);
                Player player = new Player(name, nationality, age, kills, assists, deaths, acs, adr);
                data.storePlayerFromFile(player);
            }
            //loading teams
            //checking if there are players
            line = bfr.readLine();
            if(!line.equals("true")){
                return true;
            }
            line = bfr.readLine();
            int teamCount = Integer.parseInt(line);
            for(int t=0;t<teamCount;t++){
                line = bfr.readLine();
                String[] splitLine = line.split(",");
                String teamName = splitLine[INDEX_TEAMNAME];
                String player1 = splitLine[INDEX_PLAYER1];
                String player2 = splitLine[INDEX_PLAYER2];
                String player3 = splitLine[INDEX_PLAYER3];
                String player4 = splitLine[INDEX_PLAYER4];
                String player5 = splitLine[INDEX_PLAYER5];
                ArrayList players = new ArrayList();
                players.add(data.querySpecificPlayer(player1));
                players.add(data.querySpecificPlayer(player2));
                players.add(data.querySpecificPlayer(player3));
                players.add(data.querySpecificPlayer(player4));
                players.add(data.querySpecificPlayer(player5));
                Team team = new Team(teamName, players);
                data.storeTeamFromFile(team);
            }

        } catch (IOException e) {
            return false;
        } finally {
            try {
                fr.close();
                bfr.close();
            } catch (IOException e) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initiate saving process for all files
     *
     * @param data Data parameter passed in through Menu to ensure right Data is saved to file
     */
    public static void saveAll(Data data) { //used in menu
        savePlayer(data);
        savePerson(data);
        saveTeam(data);
    }

    /**
     * Resets current Data stored, before initiating loading process for all files
     *
     * @param data Data parameter passed in through Menu to ensure right Data is loaded to Menu
     */
    public static void loadAll(Data data) { //used in menu
        data.reset();
        loadPerson(data);
        loadPlayer(data);
        loadTeam(data);
    }

    /**
     * Save all player's attributes to file
     *
     * @param data Data to get Player info from
     */
    private static void savePlayer(Data data) {
        File file = new File("player.csv");
        ArrayList<Player> allPlayers = data.queryAllPlayers(); //each entry is an object player
        if (allPlayers == null) { //if theres no players
            System.out.println("No players are in the Database, No Players saved");
        } else{ //when there is players
            try{
                // check if file exists, if not make it
                if (!file.exists()){
                    file.createNewFile();
                }
                if (file.exists() && file.isFile() && file.canWrite()){ //ensure file exists and that it can be written to
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    //for each player, write their stats: username, nationality, age, kills, assists, deaths, acs, adr
                    for (Player player : allPlayers) {
                        String info = player.getCSVInfo(); //csv of players
                        bw.write(info + "\n"); //write the csv into the file
                    }
                    bw.flush();
                    System.out.printf("Saved %s Players to File\n", allPlayers.size());
                }
                else {
                    System.out.println("Could not save to file");
                }
            }
            catch (IOException e) {
                System.out.println("Write Error Occurred");
            }
        }
    }

    /**
     * Save Team and attributes to file
     *
     * @param data Data to get Team info from
     */
    private static void saveTeam(Data data) {
        File file = new File("team.csv");
        ArrayList<Team> teamList = data.queryAllTeams(); //each entry is an object Team
        if (teamList == null) { //if no teams exist
            System.out.println("No Teams Are in The Database, No Teams Saved");
        } else{ //when a team exists
            try{
                // check if file exists, if not, make it
                if (!file.exists()){
                    file.createNewFile();
                }
                if (file.exists() && file.isFile() && file.canWrite()){ //if the file exists and it can be written to
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    //for each team, write their team name, and players 1-5
                    for (Team team : teamList) {
                        String info = team.getCSVInfo();
                        bw.write(info + "\n"); //add the csv of the team to the file
                    }
                    bw.flush();
                    System.out.printf("Saved %s Team to File\n", teamList.size());
                }
                else {
                    System.out.println("Could not save to file");
                }
            }
            catch (IOException e) {
                System.out.println("Write Error Occurred");
            }
        }
    }

    /**
     * Save all People and their attributes to file
     *
     * @param data Data to get Person data from
     */
    private static void savePerson(Data data) {
        File file = new File("person.csv");
        ArrayList<Person> personList = data.queryAllPersons(); //each entry is an object Person
        if (personList == null) {//if no people exist
            System.out.println("No People Are in The Database, No People saved");
        } else{ //a person exists
            try{
                // check if file exists, if not make it
                if (!file.exists()){
                    file.createNewFile();
                }
                if (file.exists() && file.isFile() && file.canWrite()){
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    //for each person, write their name, nationality, and age
                    for (Person person : personList) {
                        String info = person.getCSVInfo(); //write the person's csv into the file
                        bw.write(info + "\n");
                    }
                    bw.flush();
                    System.out.printf("Saved %s People to File\n", personList.size());
                }
                else{
                    System.out.println("Could not save to file");
                }
            }
            catch (IOException e) {
                System.out.println("Write Error Occurred");
            }
        }
    }

    /**
     * Loads Player data from file and saves it
     *
     * @param data Data to save Player data to
     */
    private static void loadPlayer(Data data) {
        try {
            FileReader file_reader = new FileReader("player.csv"); //read the contents of the file
            BufferedReader br = new BufferedReader(file_reader); //pass the contents into the buffered reader
            String line = br.readLine();
            while (line != null) { //while the line has info on it
                String[] playerInfo = line.split(","); //split the info into an array where each index has a corresponding data type
                String name = playerInfo[INDEX_NAME];
                String nationalityString = playerInfo[INDEX_NATIONALITY];
                Nationality nationality = Nationality.getNationality(nationalityString);
                int age = Integer.parseInt(playerInfo[INDEX_AGE]);
                int kills = Integer.parseInt(playerInfo[INDEX_KILLS]);
                int assists = Integer.parseInt(playerInfo[INDEX_ASSISTS]);
                int deaths = Integer.parseInt(playerInfo[INDEX_DEATHS]);
                int acs = Integer.parseInt(playerInfo[INDEX_ACS]);
                int adr = Integer.parseInt(playerInfo[INDEX_ADR]);
                Player player = new Player(name, nationality, age, kills, assists, deaths, acs, adr); //create the player object
                data.storePlayerFromFile(player); //store the data of the player into the database
                line = br.readLine(); //read the next line
            }
            System.out.println("Loaded all Players");
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Player file not found");
        } catch (IOException e) {
            System.out.println("Exception Occurred, Players not Loaded");
        }

    }

    /**
     * Saves Team data from file
     *
     * @param data Data to save Team data from
     */
    private static void loadTeam(Data data) {
        try{
            FileReader file_reader = new FileReader("team.csv"); //read the contents of the file
            BufferedReader br = new BufferedReader(file_reader);
            String line = br.readLine(); //first line of the file
            while(line!=null) { //if the line in the file is not empty
                String[] teamInfo = line.split(","); //split the info into an array where each index has a corresponding data type
                String teamName = teamInfo[INDEX_TEAMNAME];
                String p1 = teamInfo[INDEX_PLAYER1];
                String p2 = teamInfo[INDEX_PLAYER2];
                String p3 = teamInfo[INDEX_PLAYER3];
                String p4 = teamInfo[INDEX_PLAYER4];
                String p5 = teamInfo[INDEX_PLAYER5];
                ArrayList<Player> players = new ArrayList<>(); //initialize an empty player array list
                players.add(data.querySpecificPlayer(p1)); //add each player object to the array list
                players.add(data.querySpecificPlayer(p2));
                players.add(data.querySpecificPlayer(p3));
                players.add(data.querySpecificPlayer(p4));
                players.add(data.querySpecificPlayer(p5));
                Team team = new Team(teamName, players); //initialize the team object
                data.storeTeamFromFile(team); //store that team into the data base
                line = br.readLine(); //read next line
            }
            System.out.println("Loaded All Teams");
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Team File not Found");
        } catch (IOException e) {
            System.out.println("Exception Occurred, Teams not Loaded");
        }
    }

    /**
     * Saves Person info from file into Data
     *
     * @param data Data to save Person info into
     */
    private static void loadPerson(Data data) {
        try{
            FileReader file_reader = new FileReader("person.csv"); //read the contents of the file
            BufferedReader br = new BufferedReader(file_reader);
            String line = br.readLine(); //read first line of file
            while(line!=null) { //while line has info on it
                String[] personInfo = line.split(","); //split the info into an array where each index has a corresponding data type
                String name = personInfo[INDEX_NAME];
                String nationalityString = personInfo[INDEX_NATIONALITY];
                Nationality nationality = Nationality.getNationality(nationalityString); //convert string nationality into an enum
                int age = Integer.parseInt(personInfo[INDEX_AGE]);
                Person person = new Person(name, nationality, age); //create the person object
                data.storePersonFromFile(person); //store that person into the database
                line = br.readLine(); //read next line
            }
            System.out.println("Loaded All People");
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Team File not Found");
        } catch (IOException e) {
            System.out.println("Exception Occurred, Teams not Loaded");
        }
    }

}
