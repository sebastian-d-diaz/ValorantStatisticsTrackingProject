package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class GetOverAgeController {

    @FXML
    private Label status; //status of the window

    @FXML
    private TextField inputAge; //input field for age

    @FXML
    private TextArea textArea; //text display for players over X age

    private Data data;

    public void setData(Data data) { //creating instance of data.java
        this.data = data;
    }

    /**
     * function used to change the displayed text for players over a certain age
     * uses a user inputted age and finds all players over that age
     */
    @FXML
    void find() {
        String playersOver = ""; //initialize string
        try {
            int age = Integer.parseInt(inputAge.getText()); //get inputted age
            playersOver += "All players over " + age + ":\n"; //format the displaying of names
            ArrayList<Person> peopleOverAge = this.data.findPeopleOverAge(age); //finds and store player who are over inputted age
            if (peopleOverAge == null) { //no player over inputted age exist
                textArea.setText("No Players Found");
                status.setText("Displaying Players");
            } else { //if players over specific age exist
                for (Person people : peopleOverAge) { //for all players that are over the age of x
                    playersOver += people.getUsername() + ", Age: " + people.getAge() + "\n"; //display to the user those players
                }
                textArea.setText(playersOver);
                status.setText("Displaying Players");
            }
        } catch (NumberFormatException e) { //invalid age integer provided
            status.setText("ERROR, Age must be an Integer");
        }
    }

}
