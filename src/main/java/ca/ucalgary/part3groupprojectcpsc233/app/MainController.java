package ca.ucalgary.part3groupprojectcpsc233.app;

//importing helper-controllers

import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.util.Reader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

    @FXML
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

    @FXML
    private MenuItem GetRecommendedTeam;
    @FXML
    private Label Recommended1;
    @FXML
    private Label Recommended2;
    @FXML
    private Label Recommended3;
    @FXML
    private Label Recommended4;
    @FXML
    private Label Recommended5;

    private boolean viewingPerson;

    //Setting up FileChooser for File menu items
    FileChooser fileChooser = new FileChooser();
    File initialDirectory = new File(System.getProperty("user.dir")+"/src/exampleSaveFiles");

    public void initialize() {
        fileChooser.setInitialDirectory(initialDirectory);
        data = new Data();
        viewingPerson = true;
    }
    //null when no save file exists, used to determine if save should use save or "save as" functionality
    private File saveFile = null;
    @FXML
    protected void new_MenuItem(){
        //Wiping saveFile path
        saveFile=null;
        data.reset();
        //refreshing
        labelStatus.setLayoutX(800.0);
        labelStatus.setText("Created New File");
        //refreshing
        refreshPlayerFields();
        refreshPersonFields();

    }
    @FXML
    protected void open_MenuItem(){
        //unable to open default directory
        if(!initialDirectory.isDirectory()){
            initialDirectory = new File(System.getProperty("c:/"));
            fileChooser.setInitialDirectory(initialDirectory);
        }
        //Getting file
        fileChooser.setTitle("Open file");
        File file = fileChooser.showOpenDialog(Open.getParentPopup().getOwnerWindow());
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        //Non-null file case comes first as this is assumed to be the most common case
        if(file!=null){
            Reader.GUIload(data,file);
            saveFile=file;
            labelStatus.setLayoutX(750.0);
            labelStatus.setText("File Loaded Successfully :)");
        }
        //If some issue occurred
        else{
            labelStatus.setLayoutX(760.0);
            labelStatus.setText("Unable to Load File :(");
        }
        //refreshing
        refreshPlayerFields();
        refreshPersonFields();
    }
    @FXML
    protected void save_MenuItem(){
        //Checking if there is data
        if(data.queryAllPersons()==null){
            labelStatus.setLayoutX(700.0);
            labelStatus.setText("No data entered, unable to save :(");
            return;
        }
        //If save file exists and therefore "save" functionality can be used
        if(saveFile!=null&&saveFile.canWrite()){
            //using reader to save file
            Reader.GUIsave(data,saveFile);
            labelStatus.setLayoutX(750.0);
            labelStatus.setText("File Saved Successfully :)");
        }
        //If save file does not exist and therefore "save as functionality must be used"
        else{
            saveAs_MenuItem();
        }
    }
    @FXML
    protected void saveAs_MenuItem(){
        //Checking if there is data
        if(data.queryAllPersons()==null){
            labelStatus.setLayoutX(700.0);
            labelStatus.setText("No data entered, unable to save :(");
            return;
        }
        fileChooser.setTitle("Save file as");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);
        //displaying popup and getting file
        File file = fileChooser.showSaveDialog(SaveAs.getParentPopup().getOwnerWindow());
        //checking if file can be written to
        if(file!=null){
            //Saving file
            Boolean savedStatus = Reader.GUIsave(data, file);
            //Reader was able to successfully save file
            if(savedStatus){
                labelStatus.setLayoutX(750.0);
                labelStatus.setText("File saved Successfully :)");
                saveFile=file;
            }
            //Reader was not able to save file
            else{
                labelStatus.setLayoutX(760.0);
                labelStatus.setText("Unable to save File :(");
            }
        }
        else{
            labelStatus.setLayoutX(760.0);
            labelStatus.setText("Unable to save File :(");
        }
    }
    @FXML
    protected void quit_MenuItem(){
        Platform.exit();
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
        if (!viewingPerson){
            return;
        }
        //wiping
        textAreaCanada.setText("");
        textAreaUSA.setText("");
        textAreaBrazil.setText("");
        textAreaChile.setText("");
        textAreaEurope.setText("");
        textAreaArgentina.setText("");
        textAreaAsia.setText("");

        ArrayList<Person> canadians = data.findPeopleWithNationality("Canada");
        if (canadians == null){
            labelStatus.setLayoutX(740.0);
            labelStatus.setText("No people found in database.");
            return;
        }

        for (Person person : canadians){
            textAreaCanada.appendText(person.toString() + "\n");
        }

        ArrayList<Person> americans = data.findPeopleWithNationality("USA");
        for (Person person : americans){
            textAreaUSA.appendText(person.toString() + "\n");
        }

        ArrayList<Person> brazilians = data.findPeopleWithNationality("brazil");
        for (Person person : brazilians){
            textAreaBrazil.appendText(person.toString() + "\n");
        }

        ArrayList<Person> chile = data.findPeopleWithNationality("chile");
        for (Person person : chile){
            textAreaChile.appendText(person.toString() + "\n");
        }

        ArrayList<Person> europeans = data.findPeopleWithNationality("europe");
        for (Person person : europeans){
            textAreaEurope.appendText(person.toString() + "\n");
        }

        ArrayList<Person> argentineans = data.findPeopleWithNationality("argentina");
        for (Person person : argentineans){
            textAreaArgentina.appendText(person.toString() + "\n");
        }

        ArrayList<Person> asians = data.findPeopleWithNationality("asia");
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
    void changeMainViewToPeople(){
        viewingPerson = true;
        labelViewing.setText("Viewing: People");
        refreshPersonFields();
    }

    @FXML
    void changeMainViewToPlayers(){
        viewingPerson = false;
        labelViewing.setText("Viewing: Players");
        refreshPlayerFields();
    }


    void refreshPlayerFields(){
        if (viewingPerson){
            return;
        }

        ArrayList<Player> allPlayers = data.queryAllPlayers();

        if (allPlayers == null){
            labelStatus.setText("No players found");
            return;
        }
        //wiping
        textAreaCanada.setText("");
        textAreaArgentina.setText("");
        textAreaBrazil.setText("");
        textAreaAsia.setText("");
        textAreaChile.setText("");
        textAreaUSA.setText("");
        textAreaEurope.setText("");


        for (Player player : allPlayers){
            if (player.getNationality() == Nationality.CAN){
                textAreaCanada.appendText(player + "\n");
            }
            else if (player.getNationality() == Nationality.ARG){
                textAreaArgentina.appendText(player + "\n");
            }
            else if (player.getNationality() == Nationality.BRA){
                textAreaBrazil.appendText(player + "\n");
            }
            else if (player.getNationality() == Nationality.ASIA){
                textAreaAsia.appendText(player + "\n");
            }
            else if (player.getNationality() == Nationality.CHILE){
                textAreaChile.setText(player + "\n");
            }
            else if (player.getNationality() == Nationality.USA){
                textAreaUSA.setText(player + "\n");
            }
            else if (player.getNationality() == Nationality.EURO){
                textAreaEurope.setText(player + "\n");
            }
        }
    }

    @FXML
    void addPlayer(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPlayer.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 241, 246);
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
            scene = new Scene(fxmlLoader.load(), 262, 309);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddKillsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Kills");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }

    @FXML
    void addAssists(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAssists.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 262, 309);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddAssistsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Assists");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }

    @FXML
    void addDeaths(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddDeaths.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 262, 309);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddDeathsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Deaths");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }

    @FXML
    void addACS(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddACS.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 262, 309);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddACSController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ACS");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }

    @FXML
    void addADR(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddADR.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 262, 309);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddADRController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ADR");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }

    @FXML
    void showHighestStats(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HighestStats.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HighestStatsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Highest Stats");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields();
    }
    @FXML
    void recommendedTeam(){
        ArrayList<Player> players = data.queryAllPlayers();
        //If there aren't enough players, anticipating NullPointerException
        if(players==null){
            labelStatus.setLayoutX(770.0);
            labelStatus.setText("No players exist; need 5");
        }
        else if(data.queryAllPlayers().size()>=5){
            Player[] recommended = data.findRecommendedTeam();
            Recommended1.setText(" "+recommended[0].getUsername()+"                  "+recommended[0].getAcs());
            Recommended2.setText(" "+recommended[1].getUsername()+"                  "+recommended[1].getAcs());
            Recommended3.setText(" "+recommended[2].getUsername()+"                  "+recommended[2].getAcs());
            Recommended4.setText(" "+recommended[3].getUsername()+"                  "+recommended[3].getAcs());
            Recommended5.setText(" "+recommended[4].getUsername()+"                  "+recommended[4].getAcs());

            labelStatus.setLayoutX(730.0);
            labelStatus.setText("Recommended Team Found");
        }
        //not enough players
        else{
            //Anticipating NullPointerException

            labelStatus.setLayoutX(740.0);
            labelStatus.setText("Only "+players.size()+" players exist; need 5");
        }
    }

}