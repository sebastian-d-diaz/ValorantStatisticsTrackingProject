package ca.ucalgary.part3groupprojectcpsc233.objects;

import java.util.ArrayList;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class Team {

    /**
     * Team name
     */
    String teamName;

    /**
     * ArrayList of all Players in given team
     */
    ArrayList<Player> teamMembers;

    /**
     * Constructor to set up a Team
     *
     * @param teamName name of Team
     * @param teamMembers members of Team
     */
    public Team(String teamName, ArrayList<Player>teamMembers) {
        this.teamName = teamName;
        this.teamMembers = teamMembers;
    }


    /**
     * Getter function for a Team's team name
     *
     * @return String of team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Getter function for a Team's members
     *
     * @return ArrayList of all Players on Team
     */
    public ArrayList<Player> getTeamMembers() {
        return teamMembers;
    }

    /**
     * Helper function used for saving to file, representing Team's attributes in CSV friendly format
     *
     * @return String of Team's attributes in a CSV friendly format
     */
    public String getCSVInfo() { //uses getters in team object and converts data types to strings, combines the data into a csv format
        String teamInfo = getTeamName();
        ArrayList<Player> players = getTeamMembers();
        for (Player each: players) {
            String playerString = each.getUsername();
            teamInfo += ("," + playerString);
        }
        return teamInfo;
    }
}
