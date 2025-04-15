package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddKillsController {

    @FXML
    private TextField kills;//kills input field

    @FXML
    private TextField name;//name input field

    @FXML
    private Label label;//status of the window

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }//creating instance of data.java

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the store kills function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) {
        String username = name.getText();//get inputted username
        try {
            int numKills = Integer.parseInt(kills.getText());//get inputted kills
            boolean success = this.data.storeKillsToPlayer(username, numKills); //store kills to player
            if (!success) {//unsuccessful, no player found
                label.setText("No player found.");
            } else { //successful
                label.setText("Success!");
            }
        }catch (NumberFormatException e) {//invalid input given
            label.setText("ERROR: Kills must be an Integer");
        }
    }

}
