package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAssistsController {

    @FXML
    private TextField assists; //input field for assists

    @FXML
    private TextField name; //input field for username

    @FXML
    private Label status; //status of the window

    private Data data;
    public void setData(Data data) { //creating instance of data.java
        this.data = data;
    }

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the store assists function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) {
        String username = name.getText(); //get inputted username
        try {
            int numAssists = Integer.parseInt(assists.getText()); //get inputted assists
            boolean success = this.data.storeAssistsToPlayer(username, numAssists); //store assists to player
            if (!success) { //unsuccessful, no player found
                status.setText("No player found with username.");
            } else { //successful
                status.setText("Success!");
            }
        } catch (NumberFormatException e) { //invalid integer input
            status.setText("ERROR, Input a valid Integer for assists");
        }
    }

}
