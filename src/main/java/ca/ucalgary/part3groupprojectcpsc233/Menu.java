package ca.ucalgary.part3groupprojectcpsc233;

import ca.ucalgary.part3groupprojectcpsc233.comparators.*;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;
import ca.ucalgary.part3groupprojectcpsc233.util.Reader;

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
public class Menu {
    private static final Scanner in = new Scanner(System.in);

    private static final Data data = new Data();

    public static boolean loadData = false;

    public static void setLoadData(boolean shouldLoad) {
        loadData = shouldLoad;
    }

    /**
     * Print name of program
     */
    public static void printExpositionMessage() { //message which prints when the tracker first starts
        System.out.println("A Valorant eSports team statistics tracker.");
    }

    /**
     * Prints all options available to user, mainly used in menuLoop
     */
    public static void printAllOptions() { //displays all options for the data tracker
        System.out.println("Options:");
        System.out.println("\n0.\tExit\n");
        System.out.println("Add People & Register/Add Data:\n1.\tAdd a person and their personal info\n2.\tRegister players\n3.\tAdd kills");
        System.out.println("4.\tAdd Assists\n5.\tAdd Deaths\n6.\tAdd Average Damage Per Round(ADR)\n7.\tAdd Average Combat Score (ACS)\n\nAdd Team Data:\n8.\tAdd a team and their players\n");
        System.out.println("Output General Data:\n9.\tWhat are all of the teams?\n10.\tWhat are all the players on a specific team?\n11.\tPrint all players and their stats\n12.\tPrint all people and their personal info\n");
        System.out.println("Output Special Data:\n13.\tWhat people are from what country?\n14.\tWhat player has the highest ACS?\n15.\tWhat player has the highest ADR?\n16.\t" +
                "What player has the most kills?\n17.\tWhat player has the most assists?\n18.\tWhat player has the most deaths?\n19.\tWhat players are over a certain age?\n20.\tRecommend a team of five based on ACS\n21.\tSave Players, Teams, and People\n22.\tLoad Player, Teams, and People");
    }

    /**
     * Menu loop meant for handling when user enters an option, invalid or not, or to exit program
     * Inspired by Dr. Hudson's implementation in his YouTube video on D2L
     */
    public static void menuLoop() { // inspired from Youtube Video on D2L
        if (loadData){ // flag is set from main when arguments are found
            System.out.println("Loading data from arguments...");
            Reader.loadAll(data);
            System.out.println("Loading complete. Press enter to continue: ");
            in.nextLine();
        }
        printAllOptions();
        String choice = in.nextLine(); //takes input from user to determine what action they want to do
        int option = Integer.parseInt(choice); //convert the input from a string into and integer
        while (option != 0) { //loop options until the users
            if (option > 0 && option <= 22) { //if the users input is one of the options
                System.out.println("Selected option " + option);
                System.out.println("Press enter to continue");
                in.nextLine(); //empty input used to stall the following options until the user is ready to proceed
            }
            switch (option) {
                case 1 -> enterNewPerson(); //enter player and their info (nationality, age)
                case 2 -> enterNewPlayer();// enter person and stats
                case 3 -> enterKills(); //enter a player and change their number of kills
                case 4 -> enterAssists(); //enter a player and store their number of assists
                case 5 -> enterDeaths(); //enter a player and store their num of deaths
                case 6 -> enterADR(); //enter a player and store their ADR
                case 7 -> enterACS(); //enter a player and store their ACS
                case 8 -> enterNewTeam(); //add a team's name and add their 5 players
                case 9 -> getTeams(); //display all the teams and their players
                case 10 -> getPlayersOnTeam(); //displays all players on team x
                case 11 -> getPlayersAndStats(); //displays all players with their stats
                case 12 -> getPersonsAndInfo(); //displays all players with their personal info
                case 13 -> getPlayerWithNationality(); //look at all players, return all players who are x nationality (Ex. Canadian)
                case 14 -> getHighestStat(new PlayerACSComparator(), "ACS"); //looks thru all stats, returns the highest ACS and which player has it
                case 15 -> getHighestStat(new PlayerADRComparator(), "ADR"); //looks thru all stats, returns the highest ADR and which player has it
                case 16 -> getHighestStat(new PlayerKillComparator(), "kills"); //looks thru all stats, returns the highest num of kills and which player has it
                case 17 -> getHighestStat(new PlayerAssistsComparator(), "assists"); //looks thru all stats, returns the highest num of assists and which player has it
                case 18 -> getHighestStat(new PlayerDeathComparator(), "deaths"); //looks thru all stats, returns the highest num of deaths and which player has it
                case 19 -> getPlayersOverAge(); //looks at all players, returns all players over age x (Ex. players over age 25)
                case 20 -> getRecommendedTeam(); //find 5 players with highest ACS, recommend those 5 players to the user
                case 21 -> Reader.saveAll(data); //saves all added players, people, and teams
                case 22 -> Reader.loadAll(data); //loads all saved players, people, and teams

                default -> System.out.println("Option " + option + " not valid.");
            }
            System.out.println("Press Enter to see menu again.");
            in.nextLine();
            printAllOptions(); //display the options to the user again
            choice = in.nextLine();
            option = Integer.parseInt(choice);
        }
        System.out.println("Thank you for using this program!");
    }

    /**
     * Stores new Person into database,along with age and nationality.
     */
    private static void enterNewPerson() {
        boolean finished = false; //keeps track of if the correct variables username, nationality, age have been inputted
        String username; // initial value of null
        String tempAge; // initial value of null
        Nationality nationality; // initial value of null
        while (!finished) { //while the inputs are not valid, or the function is in progress
            System.out.println("Enter the Players Username: ");
            username = in.nextLine(); // user inputs the players name
            System.out.printf("Enter %s's Age: ", username);
            tempAge = in.nextLine(); //user inputs the age of the player
            int age = Integer.parseInt(tempAge);
            System.out.println("Available nationalities:");
            for (Nationality nationalities : Nationality.values()) {
                System.out.println("-" + nationalities);
            }
            nationality = Nationality.getNationality(in.nextLine()); // user inputs the nationality of the player
            if (nationality == null){
                System.out.println("Invalid nationality!");
            }
            else{
                finished = data.storeNewPerson(username, nationality, age);
            }
        }
    }

    /**
     * Stores new Player into database, given an already existing Person
     */
    private static void enterNewPlayer() {
        System.out.println("What player would you like to register?");
        String username = in.nextLine();
        if (data.checkIfPersonExist(username)) {
            Person newPlayer = data.querySpecificPerson(username);
            if(data.storeNewPlayer(newPlayer)){
                System.out.println("Registered!");
            }
            else{
                System.out.println("Player already added");
            }
        } else {
            System.out.println("ERROR: Person not found.");
        }
    }

    /**
     * Updates kills for a given player by the user
     */
    private static void enterKills() {
        int kills; //initial kill value of 0
        String username; //initial username of null
        System.out.println("Enter the player's Username: ");
        username = in.nextLine(); //ask user for name of a player
        System.out.printf("Enter %s's number of kills: ", username);
        kills = in.nextInt(); //user inputs number of kill the player has
        in.nextLine(); // eats up next line to avoid menu issues
        if (kills < 0) { //if kills are negative, default to zero
            kills = 0;
        }
        data.storeKillsToPlayer(username, kills); //store the provided kills for the specific player
    }

    /**
     * Updates assists for a given player by the user
     */
    private static void enterAssists() {
        int assists; // initial assists of 0
        String username; //initial username of null
        System.out.println("Enter the player's Username: ");
        username = in.nextLine(); //ask user to input name of a player
        System.out.printf("Enter %s's number of Assists: ", username);
        assists = in.nextInt(); // ask user to input number of assists of that player
        in.nextLine(); // eats up next line to avoid menu issues
        if (assists < 0) { //if the assists are negative, default to 0
            assists = 0;
        }
        data.storeAssistsToPlayer(username, assists); //store the given assists for that player
    }

    /**
     * Updates deaths for a given player by the user
     */
    private static void enterDeaths() {
        int deaths; //initial deaths of 0
        String username; //no initial username
        System.out.println("Enter the player's Username: ");
        username = in.nextLine(); //ask user to input a players name
        System.out.printf("Enter %s's number of Deaths: ", username);
        deaths = in.nextInt(); //ask user to input number of deaths for that player
        in.nextLine(); // eats up next line to avoid menu issues
        if (deaths < 0) { //if deaths are negative, deaths defaults to 0
            deaths = 0;
        }
        data.storeDeathsToPlayer(username, deaths); //store the provided deaths to the players death stat
    }

    /**
     * Updates ADR for a given player by the user
     */
    private static void enterADR() {
        int adr; //initial adr of 0
        String username; // initial username of null
        System.out.println("Enter the player's Username: ");
        username = in.nextLine(); //ask user to input a name of a player
        System.out.printf("Enter %s's ADR (Rounded): ", username);
        adr = in.nextInt();// ask user to input an ADR value for that player
        in.nextLine(); // eats up next line to avoid menu issues
        if (adr < 0) { //if adr is negative, default its value to 0
            adr = 0;
        }
        data.storeADRToPlayer(username, adr); //store the adr value given to the players adr stat
    }

    /**
     * Updates ACS for a given player by the user
     */
    private static void enterACS() {
        int acs; //initial acs value of 0
        String username; //initial username of null
        System.out.println("Enter the player's Username: ");
        username = in.nextLine(); //input the username of a player
        System.out.printf("Enter %s's ACS (Rounded): ", username);
        acs = in.nextInt(); //input the ACS of that player
        in.nextLine(); // eats up next line to avoid menu issues
        if (acs < 0) { //if acs is negative, the value becomes 0
            acs = 0;
        }
        data.storeACSToPlayer(username, acs); //store the ACS into that players stats
    }

    /**
     * Enters new team into database, allows player to input existing players and team name
     * Will give option to add a new player if a player added to the team does not exist
     */
    private static void enterNewTeam() {//inputs for getting team name and each of the players on that team
        boolean finished = false; //used to tell if all players have been added or not
        String teamName; //initial team name of null
        ArrayList<String> allPlayers; //5 empty slots on the roster of a team
        teamName = enterTeamName(); //input the name of the team being added
        while (!finished) { //while there is still players to be added
            allPlayers = enterPlayersIntoTeam(); //add players into the team
            finished = data.storeNewTeam(teamName, allPlayers); //true if 5 players have been added into the team
            if (!finished) { //less than 5 players currently on the team
                System.out.println("Add player? (Y)es, or any other key for no.");
                String choice = in.nextLine().trim();
                if (choice.equals("Y")) {
                    enterNewPlayer(); //enter the name of a player you wish to add on the team
                    System.out.println("Try again adding to team:");
                } else {
                    break;
                }
            }
        }
    }

    /**
     * Helper function to ask for team name from user
     *
     * @return Team name user provided
     */
    private static String enterTeamName() {
        System.out.println("Enter a team name: ");
        return in.nextLine().trim();
    }

    /**
     * Helper function to help enter players into a team
     *
     * @return An Array of the 5 players to be entered into a team
     */
    private static ArrayList<String> enterPlayersIntoTeam() {
        ArrayList<String> allPlayers = new ArrayList<>(); //empty roster of 5 spots for players to be added
        for (int count = 0; count < 5; count++) { //for 5 players
            String player; //player name starts as null
            System.out.printf("Enter Name of Player %s: ", count + 1);
            player = in.nextLine().trim(); //input name of the player
            allPlayers.add(count, player); //player (1,2,3,4, and 5 depending on the position in the loop) is assigned a name
        }
        return allPlayers; //return the roster of 5 players
    }

    /**
     * Prints all the teams with their players to the user in a table format
     */
    private static void getTeams() {
        ArrayList<Team> teams = data.queryAllTeams(); //a variable containing the teams and the players on that team
        if (teams == null) { //if theres no teams
            System.out.println("ERROR: No teams exist in database");
        } else {
            System.out.printf("%-15s | %-10s | %-10s | %-10s | %-10s | %-10s%n", "Team Name", "Player 1", "Player 2", "Player 3", "Player 4", "Player 5");
            System.out.println("--------------------------------------------------------------------------------");
            for (Team team : teams) {
                String teamName = team.getTeamName();
                ArrayList<Player> players = team.getTeamMembers();
                System.out.printf("%-15s | %-10s | %-10s | %-10s | %-10s | %-10s%n", teamName, players.get(0).getUsername(), players.get(1).getUsername(),
                        players.get(2).getUsername(), players.get(3).getUsername(), players.get(4).getUsername());
            }
        }
    }

    /**
     * Prints all player's names, nationality, and age in a table style format
     */
    private static void getPersonsAndInfo() {
        ArrayList<Person> playerInfo = data.queryAllPersons(); //a collection of the players and their personal info
        if (playerInfo == null) { //if theres no players
            System.out.println("ERROR: No Players Exist in Database");
        } else { //when there is players
            System.out.printf("%-15s | %-15s | %-10s%n", "Player", "NATIONALITY", "AGE");
            System.out.println("--------------------------------------------------------------------------------");
            for (Person person : playerInfo) { //for every player in the database
                String username = person.getUsername(); //players name is the key to the hashmap
                Nationality nationality = person.getNationality();
                int age = person.getAge();
                //the line below displays the name, nationality, and age of the player
                System.out.printf("%-15s | %-15s | %-10s%n", username, nationality, age);
            }
        }
    }

    /**
     * Prints the players on a specific team given by the user
     */
    private static void getPlayersOnTeam() {
        System.out.println("What team are you looking for?");
        String team = in.nextLine(); //ask user to input the name of the team whose players you wish to know
        ArrayList<Player> playersOnTeam = data.queryAllPlayersOnTeam(team); //find that team in the database
        if (playersOnTeam == null) { //team has no players -> team doesnt exist
            System.out.println("ERROR: No such team found.");
        } else {
            System.out.println("All players on team " + team + ":");
            for (Player player : playersOnTeam) { //for every player on that team, print out their name
                System.out.print(player.getUsername() + ", ");
            }
        }
    }

    /**
     * Prints all the players and their stats to the user, in a table-like format
     */
    private static void getPlayersAndStats() {
        ArrayList<Player> playerStats = data.queryAllPlayers(); //a collection of the players name and their stats
        if (playerStats == null) { //if theres no players
            System.out.println("ERROR: No Players Currently In Database");
        } else { //when there is players, display them in a table format
            System.out.printf("%-15s | %-10s | %-10s | %-10s | %-10s | %-10s%n", "Player", "ACS", "KILLS", "ASSISTS", "DEATHS", "ADR");
            System.out.println("--------------------------------------------------------------------------------");
            for (Player player : playerStats) { //for each player stored
                String username = player.getUsername();
                int kills = player.getKills();
                int acs = player.getAcs();
                int assists = player.getAssists();
                int deaths = player.getDeaths();
                int adr = player.getAdr();// their stats are a list of values, which are the values corresponding to the keys
                System.out.printf("%-15s | %-10s | %-10s | %-10s | %-10s | %-10s%n", username, acs, kills, assists, deaths, adr);
            }
        }
    }

    /**
     * Prints all players with a user given nationality
     */
    private static void getPlayerWithNationality() {
        System.out.println("Enter nationality of interest:");
        String nationality = in.nextLine(); //input desired nationality
        System.out.printf("The players of %s are:\n", nationality);
        ArrayList<Person> people = data.findPeopleWithNationality(nationality); //finds players of the nationality that the user inputted
        if (people == null) { // if theres no players
            System.out.printf("There are no players with %s", nationality); //display this message
            System.out.println();
        } else {
            for (int i = 0; i < people.size(); i++) { //for every player with that nationality that exists
                System.out.printf("\t%d\t%s", i + 1, people.get(i).getUsername()); //display the first, second, third, etc. player until the end
                System.out.println();
            }
        }
    }

    /**
     * Prints player with highest of given stat
     *
     * @param comparator Comparator to be used, passed through by Menu loop
     * @param stat String representation of stat to enable printing, passed through by Menu loop
     */
    private static void getHighestStat(Comparator<Player> comparator, String stat) {
        Player topPlayer = data.findHighestBasedOn(comparator);
        if (topPlayer == null){
            System.out.println("No players in database!");
        }
        else{
            System.out.println("The player with the highest " + stat + " is " + topPlayer.getUsername());
        }
    }

    /**
     * Finds and prints players over a certain user given age
     */
    private static void getPlayersOverAge() {
        System.out.println("What age do you want to find for players over it?");
        String ageString = in.nextLine(); //ask user for input of what age the players have to be over
        int age = Integer.parseInt(ageString);
        ArrayList<Person> peopleOverAge = data.findPeopleOverAge(age); //finds and store player who are over inputted age
        if (peopleOverAge == null) { //no player over inputted age exist
            System.out.println("ERROR: No players in database found.");
        } else { //if players over specific age exist
            System.out.println("All players over " + age + ":"); //display message to user
            for (Person people : peopleOverAge) { //for all players that are over the age of x
                System.out.print(people.getUsername() + " ,"); //display to the user those players
            }
        }
        System.out.println();
    }

    /**
     * Finds the 5 players with the highest ACS and prints them to the user
     */
    private static void getRecommendedTeam() {
        System.out.println("Your recommended team is:");
        Player[] recommendedTeam = data.findRecommendedTeam(); //a list of the 5 best players
        if (recommendedTeam == null) { //if there's not enough players to recommend
            System.out.println("At least 5 players with ACS data needed to form recommended team."); //display message to player
        } else { //there is a team to recommend
            for (int player = 0; player < 5; player++) { //for every player in the recommended team
                //display to the user, all the info above: the recommended players name, stats, nationality
                System.out.printf("\t%d. " + recommendedTeam[player].toString(), player + 1, recommendedTeam[player]);
                System.out.println();
            }
        }
    }
}