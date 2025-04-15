package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class FormTeamController {

    @FXML

    private Data data;
    public void setData(Data data){
        this.data=data;
    }
    @FXML
    private TextField TeamNameField;
    @FXML
    private TextField Player1Field;
    @FXML
    private TextField Player2Field;
    @FXML
    private TextField Player3Field;
    @FXML
    private TextField Player4Field;
    @FXML
    private TextField Player5Field;
    @FXML
    private Label Status1;
    @FXML
    private Label Status2;
    @FXML
    private Label Status3;
    @FXML
    private Label Status4;
    @FXML
    private Label Status5;
    @FXML
    protected void formTeamButton(){
        boolean validTeam=true;
        String teamName = TeamNameField.getText();

        String name1=Player1Field.getText();
        String name2=Player2Field.getText();
        String name3=Player3Field.getText();
        String name4=Player4Field.getText();
        String name5=Player5Field.getText();

        //Checking if all player names exist
        if(!data.checkIfPlayerExist(name1)){
            Status1.setText("Invalid name");
            validTeam=false;
        }
        if(!data.checkIfPlayerExist(name2)){
            validTeam=false;
            Status2.setText("Invalid name");
        }
        if(!data.checkIfPlayerExist(name3)){
            validTeam=false;
            Status3.setText("Invalid name");
        }
        if(!data.checkIfPlayerExist(name4)){
            validTeam=false;
            Status4.setText("Invalid name");
        }
        if(!data.checkIfPlayerExist(name5)){
            validTeam=false;
            Status5.setText("Invalid name");
        }
        if(validTeam==true){
            ArrayList<String>players = new ArrayList<>();
            players.add(name1);
            players.add(name2);
            players.add(name3);
            players.add(name4);
            players.add(name5);

            data.storeNewTeam(teamName,players);

        }
    }


}
