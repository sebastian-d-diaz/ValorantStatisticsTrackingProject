package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddKillsController {

    @FXML
    private TextField kills;

    @FXML
    private TextField name;

    @FXML
    private Label label;

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }


    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        int numKills = Integer.parseInt(kills.getText());
        boolean success = data.storeKillsToPlayer(username,numKills);

        if (!success){
            label.setText("No player found.");
        }
        else{
            label.setText("Success!");
        }
    }

}
