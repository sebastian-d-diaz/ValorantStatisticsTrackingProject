package ca.ucalgary.part3groupprojectcpsc233.app;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import javafx.stage.Stage;

public class MainController {

    //Objects facilitating communication with rest of program
    private Data data;
    //setters
    public void setData(Data data){
        this.data=data;
    }
    //File MenuItems
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
        data.reset();
    }
    @FXML
    protected void open_MenuItem(){
        FileChooser fc = new FileChooser();
        fc.setTitle("Open file");
        File file = fc.showOpenDialog(Open.getParentPopup().getOwnerWindow());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.cvs)", "*.cvs");
        fc.getExtensionFilters().add(extFilter);
        //Non-null file case comes first as this is assumed to be the most common case
        if(file!=null){
            labelStatus.setLayoutX(750.0);
            labelStatus.setText("File Loaded Successfully :)");
        }
        //If some issue occurred
        else{
            labelStatus.setLayoutX(760.0);
            labelStatus.setText("Unable to Load File :(");
        }

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
    void addPerson() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPerson.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddPersonController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add New Person");
        stage.setScene(scene);
        stage.showAndWait();
    }
    @FXML
    void addPlayer(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPlayer.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddPlayerController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add New Player");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void addKills(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddKills.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddKillsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add New Person");
        stage.setScene(scene);
        stage.showAndWait();
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