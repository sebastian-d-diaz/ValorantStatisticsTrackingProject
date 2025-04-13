package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
        data = new Data();
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


    void refreshPersonFields(){
        ArrayList<Person> canadians = data.findPeopleWithNationality("Canada");
        textAreaCanada.setText("");
        for (Person person : canadians){
            textAreaCanada.appendText(person.toString() + "\n");
        }

        ArrayList<Person> americans = data.findPeopleWithNationality("USA");
        textAreaUSA.setText("");
        for (Person person : americans){
            textAreaUSA.appendText(person.toString() + "\n");
        }

        ArrayList<Person> brazilians = data.findPeopleWithNationality("brazil");
        textAreaBrazil.setText("");
        for (Person person : brazilians){
            textAreaBrazil.appendText(person.toString() + "\n");
        }

        ArrayList<Person> chile = data.findPeopleWithNationality("chile");
        textAreaChile.setText("");
        for (Person person : chile){
            textAreaChile.appendText(person.toString() + "\n");
        }

        ArrayList<Person> europeans = data.findPeopleWithNationality("europe");
        textAreaEurope.setText("");
        for (Person person : europeans){
            textAreaEurope.appendText(person.toString() + "\n");
        }

        ArrayList<Person> argentineans = data.findPeopleWithNationality("argentina");
        textAreaArgentina.setText("");
        for (Person person : argentineans){
            textAreaArgentina.appendText(person.toString() + "\n");
        }

        ArrayList<Person> asians = data.findPeopleWithNationality("asia");
        textAreaAsia.setText("");
        for (Person person : asians){
            textAreaAsia.appendText(person.toString() + "\n");
        }
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
        refreshPersonFields();
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
        refreshPersonFields();
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
        stage.setTitle("Add a Players Kills");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void addAssists(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAssists.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddAssistsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Assists");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void addDeaths(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddDeaths.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddDeathsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Deaths");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void addACS(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddACS.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddACSController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ACS");
        stage.setScene(scene);
        stage.showAndWait();
    }

    @FXML
    void addADR(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddADR.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddADRController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ACS");
        stage.setScene(scene);
        stage.showAndWait();
    }


}