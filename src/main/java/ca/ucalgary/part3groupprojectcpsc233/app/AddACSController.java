package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddACSController {

    @FXML
    private TextField acs; //input from user in popup window

    @FXML
    private Label label; //status of the window

    @FXML
    private TextField name; //username of the player the user wants to change
    private Data data;


    public void setData(Data data) { //creating instance of data.java
        this.data = data;
    }

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the storeACS function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) { //activates when user presses add in GUI
        boolean success;
        String username = name.getText(); //get the inputted username
        try {
            int numACS = Integer.parseInt(acs.getText()); //get inputted acs
            success = this.data.storeACSToPlayer(username,numACS); //store the acs to the player
            if (!success){ //unsuccessful, player not found
                label.setText("No player found.");
            }
            else{ //successful
                label.setText("Success!");
            }
        } catch (NumberFormatException e) { //if the input was not a valid integer
            label.setText("Error, ACS must be an integer"); //display error message
        }
    }
}
