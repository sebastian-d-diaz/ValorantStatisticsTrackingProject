package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import static ca.ucalgary.part3groupprojectcpsc233.enums.Nationality.getNationality;

public class AddPlayerController {

    @FXML
    private TextField name;

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }


    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        Person newPlayer = data.querySpecificPerson(username);
        data.storeNewPlayer(newPlayer);
    }

}
