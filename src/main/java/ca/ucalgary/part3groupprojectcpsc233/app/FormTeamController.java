package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class FormTeamController {
    private Data data;
    public void setData(Data data,Label labelStatus){
        this.data=data;
        this.labelStatus=labelStatus;
    }
    private Label labelStatus;
    @FXML
    private Button FormTeam;
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
    private Label TeamNameStatus;
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
        ArrayList<String>players = new ArrayList<>();
        String teamName = TeamNameField.getText();
        String name1=Player1Field.getText();
        String name2=Player2Field.getText();
        String name3=Player3Field.getText();
        String name4=Player4Field.getText();
        String name5=Player5Field.getText();

        //checking if a valid team name has been entered
        if(teamName.equals("")) {
            validTeam=false;
            TeamNameStatus.setText("Invalid team name");
        }
        else{
            ArrayList<Team> teams= data.queryAllTeams();
            if(teams!=null) {
                for (Team team : teams) {
                    if (team.getTeamName().equals(teamName)) {
                        validTeam = false;
                        TeamNameStatus.setText("Invalid team name");
                    } else {
                        TeamNameStatus.setText("");
                    }

                }
            }
            //A lot more code but more slightly efficient in terms of performance. If we already know there's no teams then we don't need to check for naming conflicts
            else{
                TeamNameStatus.setText("");
            }
        }


        //Checking if all player names exist
        if(name1.equals("")||!data.checkIfPlayerExist(name1)){//Note, there is no need to check if the first name has already been entered because a new ArrayList is created everytime FormTeam button is pressed
            Status1.setText("Invalid name");
            validTeam=false;
        }
        else{
            players.add(name1);
            Status1.setText("");
        }
        if(name3.equals("")||!data.checkIfPlayerExist(name2)||players.contains(name2)){
            validTeam=false;
            Status2.setText("Invalid name");
        }
        else{
            players.add(name2);
            Status2.setText("");
        }
        if(name4.equals("")||!data.checkIfPlayerExist(name3)||players.contains(name3)){
            validTeam=false;
            Status3.setText("Invalid name");
        }
        else{
            players.add(name3);
            Status3.setText("");
        }
        if(name4.equals("")||!data.checkIfPlayerExist(name4)||players.contains(name4)){
            validTeam=false;
            Status4.setText("Invalid name");
        }
        else{
            players.add(name4);
            Status4.setText("");
        }
        if(name5.equals("")||!data.checkIfPlayerExist(name5)||players.contains(name5)){
            validTeam=false;
            Status5.setText("Invalid name");
        }
        else{
            players.add(name5);
            Status5.setText("");
        }
        if(validTeam){
            data.storeNewTeam(teamName,players);
            Stage stage = (Stage) FormTeam.getScene().getWindow();
            labelStatus.setText("Team Formed Successfully");
            stage.close();
        }
    }


}
