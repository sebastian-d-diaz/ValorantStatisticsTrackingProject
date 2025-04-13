package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static ca.ucalgary.part3groupprojectcpsc233.enums.Nationality.getNationality;

public class AddPersonController {

    @FXML
    private TextField age;

    @FXML
    private TextField name;

    @FXML
    private TextField nationality;

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }


    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        Nationality nat = getNationality(nationality.getText());
        int personAge = Integer.parseInt(age.getText());//add error checking after
        data.storeNewPerson(username,nat,personAge);
    }

}
