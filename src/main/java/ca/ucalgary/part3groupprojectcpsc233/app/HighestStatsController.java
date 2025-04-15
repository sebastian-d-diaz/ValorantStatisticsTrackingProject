package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.comparators.*;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class HighestStatsController {

    @FXML
    private TextArea HighestText; //text displaying the highest X stat
    private Data data;


    public void setData(Data data) { //creating an instance of data.java
        this.data = data;
    }

    /**
     * displays the player with the highest ACS stat
     */
    public void displayHighestACS() {
        Player player = this.data.findHighestBasedOn(new PlayerACSComparator()); //finds player with highest ACS
        if (player == null) { //no player found
            HighestText.setText("No Players Found");
        } else { //player found, display to user
            HighestText.setText("The player with the highest ACS is " + player.getUsername() + " with an ACS of " + player.getAcs());
        }
    }

    /**
     * displays the player with the highest ADR stat
     */
    public void displayHighestADR() {
        Player player = this.data.findHighestBasedOn(new PlayerADRComparator()); //finds player with highest ADR
        if (player == null) { //no player found
            HighestText.setText("No Players Found");
        } else { //player found, display to user
            HighestText.setText("The Player With the Highest ADR is " + player.getUsername() + " with an ADR of " + player.getAdr());
        }
    }

    /**
     * displays the player with the highest kills
     */
    public void displayHighestKills() {
        Player player = this.data.findHighestBasedOn(new PlayerKillComparator()); //finds player with the highest kills
        if (player == null) { //no player found
            HighestText.setText("No Players Found");
        } else { //player found, display to user
            HighestText.setText("The Player With the Highest Kills is " + player.getUsername() + " with " + player.getKills() + " kills");
        }
    }

    /**
     * displays the player with the most deaths
     */
    public void displayHighestDeaths() {
        Player player = this.data.findHighestBasedOn(new PlayerDeathComparator()); //finds player with most deaths
        if (player == null) { //no player found
            HighestText.setText("No Players Found");
        } else { //player found, display to user
            HighestText.setText("The Player With the Highest Deaths is " + player.getUsername() + " with " + player.getDeaths() + " deaths");
        }
    }

    /**
     * displays the player with the most assists
     */
    public void displayHighestAssists() {
        Player player = this.data.findHighestBasedOn(new PlayerAssistsComparator()); //find player with the most assists
        if (player == null) { //no player found
            HighestText.setText("No Players Found");
        } else { //player found, display to the user
            HighestText.setText("The Player With the Highest Assists is " + player.getUsername() + " with " + player.getKills() + " assists");
        }
    }
}
