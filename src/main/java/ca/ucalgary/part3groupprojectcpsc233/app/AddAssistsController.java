package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddAssistsController {

    @FXML
    private TextField assists;

    @FXML
    private TextField name;

    @FXML
    private Label status;

    private Data data;
    public void setData(Data data) {
        this.data = data;
    }


    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        int numAssists = Integer.parseInt(assists.getText());
        boolean success = data.storeAssistsToPlayer(username,numAssists);

        if(!success){
            status.setText("No player found with username.");
        }
        else{
            status.setText("Success!");
        }


    }

}
