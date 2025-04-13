package ca.ucalgary.part3groupprojectcpsc233.app;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;

public class MainController {


    @FXML
    private MenuItem New;
    @FXML
    private MenuItem Open;
    @FXML
    private MenuItem Save;
    @FXML
    private MenuItem SaveAs;
    @FXML
    private MenuItem Quit;

    @FXML
    private TextField inputBox;

    @FXML
    private Text inputTitle;

    @FXML
    private Button confirmInputButton;

    private Label labelViewing;

    @FXML
    private Label labelStatus;

    @FXML
    private TextArea textAreaCanada;

    @FXML
    private TextArea textAreaAsia;

    @FXML
    private TextArea textAreaEurope;

    @FXML
    private TextArea textAreaUSA;

    @FXML
    private TextArea textAreaBrazil;

    @FXML
    private TextArea textAreaArgentina;

    @FXML
    private TextArea textAreaChile;

    @FXML
    private ToolBar inputToolBar;

    public void initialize() {
        inputTitle.setVisible(false);
        inputToolBar.setVisible(false);
    }

    @FXML
    protected void new_MenuItem(){
        //Add clear methods in Data first
    }
    @FXML
    protected void open_MenuItem(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Open file");
        File file = fc.showOpenDialog(Open.getParentPopup().getOwnerWindow());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.cvs)", "*.cvs");
        fileChooser.getExtensionFilters().add(extFilter);

    }
    @FXML
    protected void save_MenuItem(){

    }
    @FXML
    protected void saveAs_MenuItem(){

    }
    @FXML
    protected void quit_MenuItem(){

    }
    @FXML
    void aboutPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Welcome!");
        alert.setContentText("Authors: Sebastian Diaz, Brian Chhan, Daniel Zhang\n\nEmails: sebastian.diaz@ucalgary.ca\n" +
                "daniel.zhang2@ucalgary.ca\nbrian.chhan@ucalgary.ca\n\nVersion: 3.0\n This is a Valorant eSports Statistics Tracker");
        alert.show();
    }

    @FXML
    void addPerson() {
        inputTitle.setText("Enter Player's Username");
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }
    @FXML
    void addPlayer(){
        inputTitle.setText("Enter Player's Username");
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }

    @FXML
    void addKills(){
        inputTitle.setText("Enter Player's Username"); //enter user name
        //get text
        //clear text, ask for input for kills
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }

    @FXML
    void addAssists(){
        inputTitle.setText("Enter Player's Username"); //enter user name
        //get text
        //clear text, ask for input for Assists
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }

    @FXML
    void addDeaths(){
        inputTitle.setText("Enter Player's Username"); //enter user name
        //get text
        //clear text, ask for input for deaths
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }

    @FXML
    void addACS(){
        inputTitle.setText("Enter Player's Username"); //enter user name
        //get text
        //clear text, ask for input for Acs
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }

    @FXML
    void addADR(){
        inputTitle.setText("Enter Player's Username"); //enter user name
        //get text
        //clear text, ask for input for ADR
        inputTitle.setVisible(true);
        inputToolBar.setVisible(true);
        confirmInputButton.setVisible(true);
    }


}