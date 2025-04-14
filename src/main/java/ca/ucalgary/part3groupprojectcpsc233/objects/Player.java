package ca.ucalgary.part3groupprojectcpsc233.objects;

import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;

/**
 * CPSC233 Group Project Part 2
 * Valorant eSports statistics tracker
 * Members:
 *  Sebastian Diaz
 *  Daniel Zhang
 *  Brian Chhan
 *  Tutorial 08 March 24th
 */
public class Player extends Person implements Comparable<Player>{

    /**
     * All stats a Player has. Can be changed hence the lack of final
     */
    private int kills, assists, deaths, acs,adr;


    /**
     * Constructor to set up basic information and stats for a Player
     *
     * @param username username of Player, inherited from Person
     * @param nationality nationality of Player, inherited from Person
     * @param age age of Player, inherited from Person
     * @param kills amount of kills, initially set to zero
     * @param assists amount of assists, initially set to zero
     * @param deaths amount of deaths, initially set to zero
     * @param acs ACS amount, initially set to zero
     * @param adr ADR amount, initially set to zero
     */
    public Player(String username, Nationality nationality, int age, int kills, int assists, int deaths, int acs, int adr) {
        super(username, nationality, age);
        this.kills = kills;
        this.assists = assists;
        this.deaths = deaths;
        this.acs = acs;
        this.adr = adr;
    }

    /**
     * Getter function for kills
     *
     * @return Player's kills
     */
    public int getKills() {
        return kills;
    }

    /**
     * Getter function for assists
     *
     * @return Player's assists
     */
    public int getAssists() {
        return assists;
    }

    /**
     * Getter functions for deaths
     *
     * @return Player's deaths
     */
    public int getDeaths() {
        return deaths;
    }

    /**
     * Getter function for ACS
     *
     * @return Player's ACS
     */
    public int getAcs() {
        return acs;
    }

    /**
     * Getter function for ADR
     *
     * @return Player's ADR
     */
    public int getAdr(){
        return adr;
    }

    /**
     * Setter function for kills
     * @param kills Kills to set a Player's kills to
     */
    public void setKills(int kills) {
        this.kills = kills;
    }

    /**
     * Setter function for assists
     *
     * @param assists assists to set a Player's assists to
     */
    public void setAssists(int assists) {
        this.assists = assists;
    }

    /**
     * Setter function for deaths
     *
     * @param deaths deaths to set a Player's deaths to
     */
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    /**
     * Setter function for ACS
     *
     * @param acs ACS to set a Player's ACS to
     */
    public void setAcs(int acs) {
        this.acs = acs;
    }

    /**
     * Setter function for ADR
     *
     * @param adr ADR to set a Player's ADR to
     */
    public void setAdr(int adr){
        this.adr = adr;
    }

    /**
     * Helper function used in saving to file in Reader
     *
     * @return Player's attributes in a CSV friendly format
     */
    public String getCSVInfo() { //uses getters for the player object to convert the data to strings, then compiles them into a csv format
        String usernameString = getUsername();
        String nationalityString = String.valueOf(getNationality());
        String ageString = String.valueOf(getAge());
        String killsString = String.valueOf(getKills());
        String assistsString = String.valueOf(getAssists());
        String deathsString = String.valueOf(getDeaths());
        String acsString = String.valueOf(getAcs());
        String adrString = String.valueOf(getAdr());
        return usernameString + "," + nationalityString + "," + ageString + "," + killsString + "," + assistsString + "," + deathsString + "," + acsString + "," + adrString;
    }

    /**
     * Override of equals to ensure two Players are equal by comparing their username & ACS
     *
     * @param obj Object to compare
     * @return true if both are equal according to comparison, false otherwise
     */
    @Override
    public boolean equals(Object obj){
        if (this == obj){
            return true;
        }
        if (!(obj instanceof Player other)){
            return false;
        }
        return super.equals(obj) && this.acs == other.acs;
    }

    /**
     * Override of toString to allow for easy printing out of a Player's attributes and stats
     *
     * @return String representation of attributes and stats
     */
    @Override
    public String toString() {
        return String.format("User: %-15s ACS: %-4d Nationality: %-12s Kills: %-4d Assists: %-4d Deaths: %-4d ADR: %-4d",
                getUsername().trim(), acs, getNationality(), kills, assists, deaths, adr);


    }

    /**
     * Default comparator for use mainly in recommended team
     *
     * @param player the Player to be compared.
     * @return the difference between the given Player and the current Player's ACS
     */
    @Override
    public int compareTo(Player player){
        return player.acs-acs;
    }

}
