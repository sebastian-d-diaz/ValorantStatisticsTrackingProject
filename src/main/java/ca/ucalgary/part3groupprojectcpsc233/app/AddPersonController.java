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
    private TextField age;

    @FXML
    private TextField name;

    @FXML
    private TextField nationality;

    @FXML
    private Label status;

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }


    @FXML
    void add(ActionEvent event) {
        String username = name.getText().stripTrailing();
        Nationality nat = getNationality(nationality.getText());
        try {
            int personAge = Integer.parseInt(age.getText());//add error checking after
            data.storeNewPerson(username,nat,personAge);
            status.setText("Success");
        } catch (NumberFormatException e) {
            status.setText("Failed, Age Must be an Integer");
        }

    }

}
