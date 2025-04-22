/**
 * Authors: Sebastian Diaz, Brian Chhan, Daniel Zhang
 * Tutorial: Tut 08, TA: Samuel Osweiler
 */
package ca.ucalgary.part3groupprojectcpsc233.app;

//importing helper-controllers

import ca.ucalgary.part3groupprojectcpsc233.enums.Nationality;
import ca.ucalgary.part3groupprojectcpsc233.objects.Person;
import ca.ucalgary.part3groupprojectcpsc233.objects.Player;
import ca.ucalgary.part3groupprojectcpsc233.objects.Team;
import ca.ucalgary.part3groupprojectcpsc233.util.Reader;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private MenuItem Open;
    @FXML
    private MenuItem Save;
    @FXML
    private MenuItem SaveAs;
    @FXML
    private MenuItem Quit;

    // main view labels and textareas
    @FXML
    private TextArea teamField;

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
    private Label labelChile;

    @FXML
    private Label labelArgentina;

    @FXML
    private Label labelEurope;


    // items for reccommendedTeam
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

    // booleans tracking what view a user is currently in
    private boolean viewingPerson;

    private boolean viewingTeam;

    //Setting up FileChooser for File menu items
    FileChooser fileChooser = new FileChooser();
    File initialDirectory = new File(System.getProperty("user.dir")+"/src/exampleSaveFiles");

    /**
     * function runs on start, initializes state of specific GUI items
     */
    public void initialize() {
        fileChooser.setInitialDirectory(initialDirectory);
        data = new Data();
        viewingPerson = true;
        viewingTeam = false;
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

    /**
     * Function handling opening/loading from a file
     */
    @FXML
    protected void open_MenuItem(){
        //unable to open default directory
        if(!initialDirectory.isDirectory()){
            initialDirectory = new File(System.getProperty("user.home"));
        }
        fileChooser.setInitialDirectory(initialDirectory);
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
        refreshTeamFields();
        refreshPlayerFields();
        refreshPersonFields();
    }

    /**
     * Function handling saving to an already existing file
     */
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

    /**
     * Function handling saving file as a csv file
     */
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

        File initialDirectory = new File(System.getProperty("user.dir") + "/src/exampleSaveFiles");

        // setting a valid initial directory, vital for use of JAR file
        if (initialDirectory.exists() && initialDirectory.isDirectory()) {
            fileChooser.setInitialDirectory(initialDirectory);
        } else {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        }
        //displaying popup and getting file
        File file = fileChooser.showSaveDialog(SaveAs.getParentPopup().getOwnerWindow());
        //checking if file can be written to
        if(file!=null){
            //Saving file
            boolean savedStatus = Reader.GUIsave(data, file);
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

    /**
     * popup window that displays information about the creators of the project to the user
     */
    @FXML
    void aboutPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Welcome!");
        alert.setContentText("Authors: Sebastian Diaz, Brian Chhan, Daniel Zhang\n\nEmails: sebastian.diaz@ucalgary.ca\n" +
                "daniel.zhang2@ucalgary.ca\nbrian.chhan@ucalgary.ca\n\nVersion: 3.0\n This is a Valorant eSports Statistics Tracker");
        alert.show();
    }

    /**
     * popup window that displays the controls of the program to the user
     */
    @FXML
    void controlsPopout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Controls");
        alert.setHeaderText("How do I use this?");
        alert.setContentText("The majority of this tool is used through the Menu Bar on the very top. From there, you " +
                "can add players, stats to them, add people, and sort through their statistics and information with a " +
                "variety of criteria. To save/load, use the File option and select what you would like to do. Use of the " +
                "middle mouse button or even right click is not required, so a trackpad will do.");
        alert.show();
    }

    /**
     * Function to refresh the fields when viewing People by nationality
     */
    void refreshPersonFields(){
        if (!viewingPerson || viewingTeam){
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

        // correctly set TextAreas
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

    /**
     * used to add a person to the database using a button
     */
    @FXML
    void addPerson() {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPerson.fxml")); //load the fxml for add person
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 400, 400); //try to create a new window
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

    /**
     * Changes main view to display People
     */
    @FXML
    void changeMainViewToPeople(){

        // set correct visibility of TextAreas
        teamField.setDisable(true);
        teamField.setVisible(false);

        textAreaCanada.setDisable(false);
        textAreaCanada.setVisible(true);

        textAreaEurope.setDisable(false);
        textAreaEurope.setVisible((true));
        labelEurope.setText("Europe");

        textAreaUSA.setDisable(false);
        textAreaUSA.setVisible(true);

        textAreaChile.setDisable(false);
        textAreaChile.setVisible(true);
        labelChile.setText("Chile");

        textAreaAsia.setDisable(false);
        textAreaAsia.setVisible(true);

        textAreaBrazil.setDisable(false);
        textAreaBrazil.setVisible(true);

        textAreaArgentina.setDisable(false);
        textAreaArgentina.setVisible(true);
        labelArgentina.setText("Argentina");

        viewingPerson = true;
        viewingTeam = false;
        labelViewing.setText("Viewing: People");
        refreshPersonFields();
    }

    /**
     * Function to change main view to Players
     */
    @FXML
    void changeMainViewToPlayers(){

        // ensure textfields are correct with corresponding view
        teamField.setDisable(true);
        teamField.setVisible(false);

        textAreaCanada.setDisable(false);
        textAreaCanada.setVisible(true);

        textAreaEurope.setDisable(false);
        textAreaEurope.setVisible((true));
        labelEurope.setText("Europe");

        textAreaUSA.setDisable(false);
        textAreaUSA.setVisible(true);

        textAreaChile.setDisable(false);
        textAreaChile.setVisible(true);
        labelChile.setText("Chile");

        textAreaAsia.setDisable(false);
        textAreaAsia.setVisible(true);

        textAreaBrazil.setDisable(false);
        textAreaBrazil.setVisible(true);

        textAreaArgentina.setDisable(false);
        textAreaArgentina.setVisible(true);
        labelArgentina.setText("Argentina");

        viewingPerson = false;
        viewingTeam = false;
        labelViewing.setText("Viewing: Players");
        refreshPlayerFields();
    }

    /**
     * Function to change view based on teams
     */
    @FXML
    void changeMainViewToTeams(){

        // once again change visibility of certain textfields to match
        teamField.setDisable(false);
        teamField.setVisible(true);

        textAreaCanada.setDisable(false);
        textAreaCanada.setVisible(true);

        textAreaEurope.setDisable(false);
        textAreaEurope.setVisible((true));
        labelEurope.setText("");

        textAreaUSA.setDisable(true);
        textAreaUSA.setVisible(false);

        textAreaChile.setDisable(true);
        textAreaChile.setVisible(false);
        labelChile.setText("");

        textAreaAsia.setDisable(true);
        textAreaAsia.setVisible(false);

        textAreaBrazil.setDisable(true);
        textAreaBrazil.setVisible(false);

        textAreaArgentina.setDisable(true);
        textAreaArgentina.setVisible(false);
        labelArgentina.setText("");

        viewingPerson = false;
        viewingTeam = true;
        labelViewing.setText("Viewing: Teams");
        refreshTeamFields();
    }

    /**
     * Refresh textareas to reflect current teams
     */
    void refreshTeamFields(){

        // if booleans suggest that you are not viewing teams, dont proceed
        if (viewingPerson || !viewingTeam){
            return;
        }
        ArrayList<Team> allTeams = data.queryAllTeams();

        if (allTeams == null){
            labelStatus.setText("No teams found.");
        }
        else{
            teamField.setText("");
            for (Team team : allTeams){
                teamField.appendText(team.getTeamName() + ": ");
                for (Player players : team.getTeamMembers()){
                    teamField.appendText(players.getUsername());
                }
                teamField.appendText("\n");
            }
        }

    }

    /**
     * Refresh TextFields to represent current players
     */
    void refreshPlayerFields(){
        if (viewingPerson || viewingTeam){
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

        // actually set up TextFields/TextAreas
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

    /**
     * function that allows user to add/ register a player into the database
     */
    @FXML
    void addPlayer(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddPlayer.fxml")); //load fxml for add player
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); // try to create popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddPlayerController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add New Player");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
    }

    /**
     * function that allows user to add kills to a registered player
     */
    @FXML
    void addKills(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddKills.fxml")); //load fxml file for add kills
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); //try to create popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddKillsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Kills");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
    }

    /**
     * function that allows user to add assists to a registered player
     */
    @FXML
    void addAssists(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddAssists.fxml")); //load the fxml for add assists
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); //try to create the popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddAssistsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Assists");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
    }

    /**
     * function that allows user to add deaths to a registered player
     */
    @FXML
    void addDeaths(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddDeaths.fxml")); //load the fxml file for add deaths
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); //try to create the popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddDeathsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players Deaths");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh the textboxes
    }

    /**
     * function that allows user to add ACS to a player that is registered
     */
    @FXML
    void addACS(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddACS.fxml")); //load the fxml file for add ACS
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); //try to create popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddACSController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ACS");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
    }

    /**
     * function that allows user to add ADR to a registered player
     */
    @FXML
    void addADR(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AddADR.fxml")); //load the fxml for add ADR
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 300, 300); //try to create the popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        AddADRController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Add a Players ADR");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
    }

    /**
     * popup window that allows the user to view various highest stats out of the players in the database
     */
    @FXML
    void showHighestStats(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HighestStats.fxml")); //load the fxml file for highest stats
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 400); //try to create the popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HighestStatsController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Highest Stats");
        stage.setScene(scene);
        stage.showAndWait();
        refreshPlayerFields(); //refresh text boxes
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

    /**
     * function that displays players over a certain age
     */
    @FXML
    void showOverAge(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GetOverAge.fxml")); //load the fxml file for get over age
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(), 600, 500); //try to create popup window
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GetOverAgeController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Player Over Age...");
        stage.setScene(scene);
        stage.showAndWait();
    }

    /**
     * Function concerns when one is forming a team from 5 players
     */
    @FXML
    void FormATeam(){
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("FormTeam.fxml"));
        Scene scene;
        try{
            scene = new Scene(fxmlLoader.load(),400,600);
        }
        catch(IOException e){
            throw new RuntimeException(e);
        }
        FormTeamController controller = fxmlLoader.getController();
        controller.setData(data);
        Stage stage = new Stage();
        stage.setTitle("Form a Team");
        stage.setScene(scene);
        stage.showAndWait();
        refreshTeamFields();
    }

}