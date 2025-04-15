package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class AddPlayerController {

    @FXML
    private TextField name;//input field for name

    @FXML
    private Label status;//status of the window

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }//creating instance of data.java

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the store player function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) {
        String username = name.getText();//get inputted name
        Person newPlayer = this.data.querySpecificPerson(username); //see if a person with that name exists
        if (newPlayer == null){ //person doesn't exist
            status.setText("No person of that name found.");
            return;
        }
        status.setText("Success");
        this.data.storeNewPlayer(newPlayer);//person exists, register the player

    }

}
