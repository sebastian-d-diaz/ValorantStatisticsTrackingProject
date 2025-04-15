package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddADRController {

    @FXML
    private TextField adr; //input field for adr

    @FXML
    private TextField name; //player who user wishes to add data to

    @FXML
    private Label status; //status of the window

    private Data data;

    public void setData(Data data) { //creating instance of data.java
        this.data = data;
    }

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the storeADR function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) { //triggered when user presses add button
        String username = name.getText(); //get inputted name of player
        try {
            int numADR = Integer.parseInt(adr.getText()); //get the inputted acs of the player
            boolean success = this.data.storeADRToPlayer(username, numADR);//store the adr to that player
            if (!success) { //unsuccessful, no player found
                status.setText("No player found with username.");
            } else { //successful
                status.setText("Success!");
            }
        }catch (NumberFormatException e) { //if user inputs an invalid integer
            status.setText("ERROR, ADR must be an Integer");
        }
    }

}
