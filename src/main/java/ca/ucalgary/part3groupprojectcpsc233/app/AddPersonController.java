package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import static ca.ucalgary.part3groupprojectcpsc233.enums.Nationality.getNationality;

public class AddPersonController {

    @FXML
    private TextField age;//input field for age

    @FXML
    private TextField name;//input field for name

    @FXML
    private TextField nationality;//input field for nationality

    @FXML
    private Label status;//status of the window

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }//creating instance of data.java

    /**
     * After filling out required text fields and pressing the add button,
     * take the inputs and use them for the store person function in data
     * @param event user presses button in GUI
     */
    @FXML
    void add(ActionEvent event) {
        String username = name.getText().stripTrailing(); //get inputted name
        Nationality nat = getNationality(nationality.getText());//get inputted nationality
        //checking nationality
        if(nat==null){ //nationality doesn't exist
            status.setText("Failed, unknown nationality");
            return;
        }
        //checking Age
        try {
            int personAge = Integer.parseInt(age.getText());//get inputted age
            this.data.storeNewPerson(username,nat,personAge); //store name, nationality,age
            status.setText("Success");
        } catch (NumberFormatException e) { //invalid age given
            status.setText("Failed, Age Must be an Integer");
        }
    }

}
