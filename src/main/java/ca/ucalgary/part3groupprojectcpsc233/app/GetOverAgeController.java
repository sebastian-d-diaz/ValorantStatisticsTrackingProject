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
    private Label status;

    @FXML
    private TextField inputAge;

    @FXML
    private TextArea textArea;

    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    @FXML
    void find() {
        String playersOver = "";
        try {
            int age = Integer.parseInt(inputAge.getText());
            data.findPeopleOverAge(age);
            playersOver += "All players over " + age + ":\n";
            ArrayList<Person> peopleOverAge = data.findPeopleOverAge(age); //finds and store player who are over inputted age
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
        } catch (NumberFormatException e) {
            status.setText("ERROR, Age must be an Integer");
        }
    }

}
