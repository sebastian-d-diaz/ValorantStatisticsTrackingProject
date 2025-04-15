package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.comparators.*;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextFormatter;

public class HighestStatsController {

    @FXML
    private TextArea HighestText;
    private Data data;


    public void setData(Data data) {
        this.data = data;
    }

    public void displayHighestACS() {
        Player player = this.data.findHighestBasedOn(new PlayerACSComparator());
        if (player == null) {
            HighestText.setText("No Players Found");
        } else {
            HighestText.setText("The player with the highest ACS is " + player.getUsername() + " with an ACS of " + player.getAcs());
        }
    }
    public void displayHighestADR() {
        Player player = this.data.findHighestBasedOn(new PlayerADRComparator());
        if (player == null) {
            HighestText.setText("No Players Found");
        } else {
            HighestText.setText("The Player With the Highest ADR is " + player.getUsername() + " with an ADR of " + player.getAdr());
        }
    }
    public void displayHighestKills() {
        Player player = this.data.findHighestBasedOn(new PlayerKillComparator());
        if (player == null) {
            HighestText.setText("No Players Found");
        } else {
            HighestText.setText("The Player With the Highest Kills is " + player.getUsername() + " with " + player.getKills() + " kills");
        }
    }

    public void displayHighestDeaths() {
        Player player = this.data.findHighestBasedOn(new PlayerDeathComparator());
        if (player == null) {
            HighestText.setText("No Players Found");
        } else {
            HighestText.setText("The Player With the Highest Deaths is " + player.getUsername() + " with " + player.getDeaths() + " deaths");
        }
    }

    public void displayHighestAssists() {
        Player player = this.data.findHighestBasedOn(new PlayerAssistsComparator());
        if (player == null) {
            HighestText.setText("No Players Found");
        } else {
            HighestText.setText("The Player With the Highest Assists is " + player.getUsername() + " with " + player.getKills() + " assists");
        }
    }
}
