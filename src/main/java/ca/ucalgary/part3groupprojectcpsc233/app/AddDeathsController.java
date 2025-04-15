package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddDeathsController {

    @FXML
    private TextField deaths; //input field for deaths

    @FXML
    private Label status; //status of the window

    @FXML
    private TextField name;//input field for username
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }//creating instance of data.java

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the store deaths function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) {
        String username = name.getText(); //get inputted username
        try {
            int numDeaths = Integer.parseInt(deaths.getText()); //get inputted deaths
            boolean success = this.data.storeDeathsToPlayer(username, numDeaths); //store deaths to the player
            if (!success) { //unsuccessful, player not found
                status.setText("No player found with username");
            } else {//successful
                status.setText("Success!");
            }
        } catch (NumberFormatException e) {//invalid integer input given
            status.setText("ERROR, Input a valid integer for Deaths");
        }
    }

}
