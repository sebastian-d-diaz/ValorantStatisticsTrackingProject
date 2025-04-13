package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddACSController {

    @FXML
    private TextField acs;

    @FXML
    private Label label;

    @FXML
    private TextField name;
    private Data data;


    public void setData(Data data) {
        this.data = data;
    }

    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        int numACS = Integer.parseInt(acs.getText());
        boolean success = data.storeACSToPlayer(username,numACS);

        if (!success){
            label.setText("No player found.");
        }
        else{
            label.setText("Success!");
        }


    }

}
