package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AddDeathsController {

    @FXML
    private TextField deaths;

    @FXML
    private TextField name;
    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    @FXML
    void add(ActionEvent event) {
        String username = name.getText();
        int numDeaths = Integer.parseInt(deaths.getText());
        data.storeDeathsToPlayer(username,numDeaths);
    }

}
