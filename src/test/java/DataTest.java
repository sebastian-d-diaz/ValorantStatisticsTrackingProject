import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.comparators.PlayerACSComparator;
import ca.ucalgary.part3groupprojectcpsc233.comparators.PlayerKillComparator;
import ca.ucalgary.part3groupprojectcpsc233.comparators.*;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataTest {
    @Test
    void storeNewTeam_BasicSuccessfulEntry(){
        Data data = new Data();
        String teamName1 = "TeamNumba1";
        String teamName2 = "TeamNumba2";
        ArrayList<String> team1 = new ArrayList<>();
        ArrayList<String> team2 = new ArrayList<>();
        team1.add("p1");
        data.storeNewPlayer(new Player("p1",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p2");
        data.storeNewPlayer(new Player("p2",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p3");
        data.storeNewPlayer(new Player("p3",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p4");
        data.storeNewPlayer(new Player("p4",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p5");
        data.storeNewPlayer(new Player("p5",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p6");
        data.storeNewPlayer(new Player("p6",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p7");
        data.storeNewPlayer(new Player("p7",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p8");
        data.storeNewPlayer(new Player("p8", Nationality.CAN,1,2,3,4,5,6));
        team2.add("p9");
        data.storeNewPlayer(new Player("p9",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p10");
        data.storeNewPlayer(new Player("p10",Nationality.CAN,1,2,3,4,5,6));
        assertTrue(data.storeNewTeam(teamName1,team1));
        assertTrue(data.storeNewTeam(teamName2,team2));
    }

    @Test
    void storeNewTeam_SuccessfulEntry_ThreeTeams(){
        Data data = new Data();
        String teamName1 = "TeamNumba1";
        String teamName2 = "TeamNumba2";
        String teamName3 = "TeamNumba2";
        ArrayList<String> team1 = new ArrayList<>();
        ArrayList<String> team2 = new ArrayList<>();
        ArrayList<String> team3 = new ArrayList<>();
        team1.add("p1");
        data.storeNewPlayer(new Player("p1",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p2");
        data.storeNewPlayer(new Player("p2",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p3");
        data.storeNewPlayer(new Player("p3",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p4");
        data.storeNewPlayer(new Player("p4",Nationality.CAN,1,2,3,4,5,6));
        team1.add("p5");
        data.storeNewPlayer(new Player("p5",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p6");
        data.storeNewPlayer(new Player("p6",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p7");
        data.storeNewPlayer(new Player("p7",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p8");
        data.storeNewPlayer(new Player("p8",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p9");
        data.storeNewPlayer(new Player("p9",Nationality.CAN,1,2,3,4,5,6));
        team2.add("p10");
        data.storeNewPlayer(new Player("p10",Nationality.CAN,1,2,3,4,5,6));
        team3.add("p11");
        data.storeNewPlayer(new Player("p11",Nationality.CAN,1,2,3,4,5,6));
        team3.add("p12");
        data.storeNewPlayer(new Player("p12",Nationality.CAN,1,2,3,4,5,6));
        team3.add("p13");
        data.storeNewPlayer(new Player("p13",Nationality.CAN,1,2,3,4,5,6));
        team3.add("p14");
        data.storeNewPlayer(new Player("p14",Nationality.CAN,1,2,3,4,5,6));
        team3.add("p15");
        data.storeNewPlayer(new Player("p15",Nationality.CAN,1,2,3,4,5,6));
        assertTrue(data.storeNewTeam(teamName1,team1));
        assertTrue(data.storeNewTeam(teamName2,team2));
        assertTrue(data.storeNewTeam(teamName3,team3));
    }
    //StoreNewPerson() Tests
    @Test
    void storeNewPersonTest_BasicSuccess(){
        Data data = new Data();
        String username1 = "Johan69";
        String username2 = "Bohan69";
        assertTrue(data.storeNewPerson(username1, Nationality.ARG,69));
        assertTrue(data.storeNewPerson(username2, Nationality.CHILE,420));
    }
    @Test
    void storeNewPersonTest_DuplicatedSecondUsername(){
        Data data = new Data();
        String username1 = "Johan69";
        String username2 = "Johan69";//Overlap
        data.storeNewPerson(username1, Nationality.ARG,69);
        assertFalse(data.storeNewPerson(username2, Nationality.CHILE,420));
    }
    @Test
    void storeNewPersonTest_DuplicatedThirdUsername(){
        Data data = new Data();
        String username1 = "Johan69";
        String username2 = "Bohan69";
        String username3 = "Johan69";//Overlap
        data.storeNewPerson(username1, Nationality.ARG,69);
        data.storeNewPerson(username2, Nationality.CHILE,69);
        assertFalse(data.storeNewPerson(username3, Nationality.CHILE,420));
    }
    //checkIfPersonExist() Tests
    @Test
    void checkIfPersonExistTest_True(){
        Data data = new Data();
        data.storeNewPerson("Yohan",Nationality.EURO,420);
        assertTrue(data.checkIfPersonExist("Yohan"));
    }
    @Test
    void checkIfPersonExistTest_False(){
        Data data = new Data();
        data.storeNewPerson("Yohan",Nationality.EURO,420);
        assertFalse(data.checkIfPersonExist("Johan"));

    }

    //findPeopleWithNationality tests
    @Test
    void findPeopleWithNationalityWhenNoSuchExists(){
        Data data = new Data();
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        ArrayList<Person> expected =  new ArrayList<>();
        ArrayList<Person> actual = data.findPeopleWithNationality("Argentina");
        assertEquals(expected, actual);
    }

    @Test
    void findPeopleWithNationalitySuccess(){
        Data data = new Data();

        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(data.querySpecificPerson("ally"));
        expected.add(data.querySpecificPerson("LEBROOON"));

        ArrayList<Person> actual = data.findPeopleWithNationality("USA");
        assertEquals(expected, actual);
    }

    // findPeopleOverAge tests
    @Test
    void findPeopleOverAgeWhenNoSuchExist(){
        Data data = new Data();

        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        ArrayList<Person> expected = new ArrayList<>();

        ArrayList<Person> actual = data.findPeopleOverAge(41);

        assertEquals(expected, actual);
    }

    @Test
    void findPeopleOverAgeSuccess(){
        Data data = new Data();

        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        ArrayList<Person> expected = new ArrayList<>();
        expected.add(data.querySpecificPerson("LEBROOON"));
        expected.add(data.querySpecificPerson("JHuddy"));
        expected.add(data.querySpecificPerson("Snow"));

        ArrayList<Person> actual = data.findPeopleOverAge(19);

        assertEquals(expected, actual);
    }

    // findHighestBased on tests
    @Test
    void findHighestBasedOnWhenNoPlayers(){
        Data data = new Data();
        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        Player expected = null;

        Player actual = data.findHighestBasedOn(new PlayerACSComparator());

        assertEquals(expected, actual);

    }

    @Test
    void findHighestBasedOnWhenMultiplePlayers(){
        Data data = new Data();
        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        data.storeNewPlayer(data.querySpecificPerson("ally"));
        data.storeNewPlayer(data.querySpecificPerson("sebi"));
        data.storeNewPlayer(data.querySpecificPerson("JHuddy"));
        data.storeNewPlayer(data.querySpecificPerson("LEBROOON"));
        data.storeNewPlayer(data.querySpecificPerson("Snow"));

        data.storeACSToPlayer("sebi", 250);
        data.storeACSToPlayer("LEBROOON", 249);
        data.storeACSToPlayer("JHuddy", 12);

        data.storeKillsToPlayer("Snow", 500);

        Player expected = data.querySpecificPlayer("sebi");

        Player actual = data.findHighestBasedOn(new PlayerACSComparator());

        assertEquals(expected, actual);
    }

    @Test
    void findHighestBasedOnStatWhenTwoPeopleTied(){
        Data data = new Data();
        data.storeNewPerson("ally", Nationality.USA, 18);
        data.storeNewPerson("sebi", Nationality.CAN,18);
        data.storeNewPerson("LEBROOON", Nationality.USA, 40);
        data.storeNewPerson("JHuddy", Nationality.BRA,30);
        data.storeNewPerson("Snow", Nationality.ASIA,19);

        data.storeNewPlayer(data.querySpecificPerson("ally"));
        data.storeNewPlayer(data.querySpecificPerson("sebi"));
        data.storeNewPlayer(data.querySpecificPerson("JHuddy"));
        data.storeNewPlayer(data.querySpecificPerson("LEBROOON"));
        data.storeNewPlayer(data.querySpecificPerson("Snow"));

        data.storeACSToPlayer("sebi", 249);
        data.storeACSToPlayer("LEBROOON", 259);
        data.storeACSToPlayer("JHuddy", 12);
        data.storeACSToPlayer("Snow", 250);

        data.storeKillsToPlayer("Snow", 500);
        data.storeKillsToPlayer("sebi",500);
        data.storeKillsToPlayer("ally",499);

        Player expected = data.querySpecificPlayer("Snow");

        Player actual = data.findHighestBasedOn(new PlayerKillComparator());

        assertEquals(expected, actual);
    }
    //Recommended Team tests
    @Test
    void recommendedTeamTest_NoTiedPlayers(){
        Data data = new Data();
        Player[] expected={
                new Player("p1",Nationality.ARG,1,2,3,4,5,6),
                new Player("p2",Nationality.ARG,1,2,3,4,4,6),
                new Player("p3",Nationality.ARG,1,2,3,4,3,6),
                new Player("p4",Nationality.ARG,1,2,3,4,2,6),
                new Player("p5",Nationality.ARG,1,2,3,4,1,6),
        };
        data.storeNewPlayer(expected[0]);
        data.storeACSToPlayer("p1",5);
        data.storeNewPlayer(expected[1]);
        data.storeACSToPlayer("p2",4);
        data.storeNewPlayer(expected[2]);
        data.storeACSToPlayer("p3",3);
        data.storeNewPlayer(expected[3]);
        data.storeACSToPlayer("p4",2);
        data.storeNewPlayer(expected[4]);
        data.storeACSToPlayer("p5",1);
        Player[]actual = data.findRecommendedTeam();
        assertTrue(Arrays.deepEquals(actual,expected));
    }
    @Test
    void recommendedTeamTest_TwoTiedPlayers(){
        Data data = new Data();
        Player[] expected={
                new Player("p1",Nationality.ARG,1,2,3,4,5,6),
                new Player("p2",Nationality.ARG,1,2,3,4,5,6),
                new Player("p3",Nationality.ARG,1,2,3,4,3,6),
                new Player("p4",Nationality.ARG,1,2,3,4,2,6),
                new Player("p5",Nationality.ARG,1,2,3,4,1,6),
        };
        data.storeNewPlayer(expected[0]);
        data.storeACSToPlayer("p1",5);
        data.storeNewPlayer(expected[1]);
        data.storeACSToPlayer("p2",5);
        data.storeNewPlayer(expected[2]);
        data.storeACSToPlayer("p3",3);
        data.storeNewPlayer(expected[3]);
        data.storeACSToPlayer("p4",2);
        data.storeNewPlayer(expected[4]);
        data.storeACSToPlayer("p5",1);
        Player[]actual = data.findRecommendedTeam();
        assertTrue(Arrays.deepEquals(actual,expected));
    }
    @Test void recommendedTeamTest_AllTied(){
        Data data = new Data();
        Player[] expected={
                new Player("p1",Nationality.ARG,1,2,3,4,5,6),
                new Player("p2",Nationality.ARG,1,2,3,4,5,6),
                new Player("p3",Nationality.ARG,1,2,3,4,3,6),
                new Player("p4",Nationality.ARG,1,2,3,4,2,6),
                new Player("p5",Nationality.ARG,1,2,3,4,1,6),
        };
        data.storeNewPlayer(expected[0]);
        data.storeACSToPlayer("p1",5);
        data.storeNewPlayer(expected[1]);
        data.storeACSToPlayer("p2",5);
        data.storeNewPlayer(expected[2]);
        data.storeACSToPlayer("p3",3);
        data.storeNewPlayer(expected[3]);
        data.storeACSToPlayer("p4",2);
        data.storeNewPlayer(expected[4]);
        data.storeACSToPlayer("p5",1);
        Player[]actual = data.findRecommendedTeam();
        assertTrue(Arrays.deepEquals(actual,expected));
    }
    //nationality tests
    @Test
    void getNationalityExistsTest() { //get nationality of someone who has a valid nationality
        Nationality actual = Nationality.getNationality("canada");
        Nationality expected = Nationality.CAN;

        assertEquals(expected, actual);
    }

    @Test
    void getNationalityDoesntExistTest() {
        Nationality actual = Nationality.getNationality("nowhere");
        Nationality expected = null;
        assertEquals(expected, actual);
    }

    //player tests
    @Test
    void getPlayerKillsTest() {
        Player player = new Player("Marvin", Nationality.CAN,22,14,15,10, 200, 98);
        int actual = player.getKills();
        int expected = 14;
        assertEquals(expected, actual);
    }
    @Test
    void setPlayerKillsTest() {
        Player player = new Player("Marvin", Nationality.CAN,22,14,15,10, 200, 98); //initial kills of 14
        player.setKills(25);
        int actual = player.getKills();
        int expected = 25;
        assertEquals(expected,actual);
    }
    @Test
    void getPlayerAssistsTest() {
        Player player = new Player("Stephen", Nationality.ASIA,30,71,25,30, 343, 240);
        int actual = player.getAssists();
        int expected = 25;
        assertEquals(expected, actual);
    }
    @Test
    void setPlayerAssistsTest() {
        Player player = new Player("Stephen", Nationality.ASIA,30,71,25,30, 343, 240);
        player.setAssists(27);
        int actual = player.getAssists();
        int expected = 27;
        assertEquals(expected, actual);
    }
    @Test
    void getPlayerDeathsTest() {
        Player player = new Player("Demon1", Nationality.USA,21,105,30,36, 379, 255);
        int actual = player.getDeaths();
        int expected = 36;
        assertEquals(expected, actual);
    }
    @Test
    void setPlayerDeathsTest() {
        Player player = new Player("Demon1", Nationality.USA,21,105,30,36, 379, 255);
        player.setDeaths(22);
        int actual = player.getDeaths();
        int expected = 22;
        assertEquals(expected, actual);
    }
    @Test
    void getPlayerACSTest(){
        Player player = new Player("Havoc", Nationality.BRA,21,20,30,58, 178, 104);
        int actual = player.getAcs();
        int expected = 178;
        assertEquals(expected,actual);
    }
    @Test
    void setPlayerACSTest(){
        Player player = new Player("Havoc", Nationality.BRA,21,20,30,58, 178, 104);
        player.setAcs(144);
        int actual = player.getAcs();
        int expected = 144;
        assertEquals(expected,actual);
    }
    @Test
    void getPlayerADRTest(){
        Player player = new Player("Tenz", Nationality.CAN, 23, 70,40,51,269,199);
        int actual = player.getAdr();
        int expected = 199;
        assertEquals(expected,actual);
    }
    @Test
    void setPlayerADRTest(){
        Player player = new Player("Tenz", Nationality.CAN, 23, 70,40,51,269,199);
        player.setAdr(222);
        int actual = player.getAdr();
        int expected = 222;
        assertEquals(expected,actual);
    }
    @Test
    void getPlayerCSVInfoTest() {
        Player player = new Player("Lebron James", Nationality.USA, 42,40,40,40,23,6);
        String actual = player.getCSVInfo();
        String exp = "Lebron James,USA,42,40,40,40,23,6";
        assertEquals(exp,actual);
    }

    //team tests
    @Test
    void getTeamNameTest() {
        Player p1 = new Player("Lebron James", Nationality.USA, 42,40,40,40,23,6);
        Player p2 = new Player("Tenz", Nationality.CAN, 23, 70,40,51,269,199);
        Player p3 = new Player("Havoc", Nationality.BRA,21,20,30,58, 178, 104);
        Player p4 = new Player("Demon1", Nationality.USA,21,105,30,36, 379, 255);
        Player p5 = new Player("Stephen", Nationality.ASIA,30,71,25,30, 343, 240);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);

        Team team = new Team("Dream Team", players);
        String actual = team.getTeamName();
        String exp = "Dream Team";
        assertEquals(exp, actual);
    }
    @Test
    void getTeamMembersTest() { //team of 5 players, display just their names to look at the players in the team
        Player p1 = new Player("Lebron", Nationality.USA, 42,40,40,40,23,6);
        Player p2 = new Player("Tenz", Nationality.CAN, 23, 70,40,51,269,199);
        Player p3 = new Player("Havoc", Nationality.BRA,21,20,30,58, 178, 104);
        Player p4 = new Player("Demon1", Nationality.USA,21,105,30,36, 379, 255);
        Player p5 = new Player("Stephen", Nationality.ASIA,30,71,25,30, 343, 240);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);

        Team team = new Team("Dream Team", players);
        String rosterString = "";
        ArrayList<Player> roster = team.getTeamMembers();
        for(Player each: roster) {
            rosterString += each.getUsername()+" ";
        }
        String expected = "Lebron Tenz Havoc Demon1 Stephen ";
        String actual = rosterString;
        assertEquals(expected,actual);
    }
    @Test
    void getTeamCSVInfoTest(){
        Player p1 = new Player("Lebron", Nationality.USA, 42,40,40,40,23,6);
        Player p2 = new Player("Tenz", Nationality.CAN, 23, 70,40,51,269,199);
        Player p3 = new Player("Havoc", Nationality.BRA,21,20,30,58, 178, 104);
        Player p4 = new Player("Demon1", Nationality.USA,21,105,30,36, 379, 255);
        Player p5 = new Player("Stephen", Nationality.ASIA,30,71,25,30, 343, 240);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        players.add(p3);
        players.add(p4);
        players.add(p5);

        Team team = new Team("Dream Team", players);
        String actual = team.getCSVInfo();
        String expected = "Dream Team,Lebron,Tenz,Havoc,Demon1,Stephen";
        assertEquals(expected,actual);
    }
    @Test
    void getPersonUsernameTest(){
        Person person = new Person("Kaplan", Nationality.USA, 27);
        String actual = person.getUsername();
        String expected = "Kaplan";
        assertEquals(expected,actual);
    }
    @Test
    void getPersonNationalityTest(){
        Person person = new Person("Quake", Nationality.BRA, 23);
        Nationality actual = person.getNationality();
        Nationality expected = Nationality.BRA;
        assertEquals(expected,actual);
    }
    @Test
    void getPersonAge(){
        Person person = new Person("Pansy", Nationality.EURO, 30);
        int actual = person.getAge();
        int expected = 30;
        assertEquals(expected,actual);
    }
    @Test
    void getPersonCSVInfo(){
        Person person = new Person("Lebron", Nationality.USA, 42);
        String actual = person.getCSVInfo();
        String expected = "Lebron,USA,42";
        assertEquals(expected,actual);
    }

}
