package ca.ucalgary.part3groupprojectcpsc233.app;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class MainController {

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
    void aboutPopup(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Welcome!");
        alert.setContentText("Authors: Sebastian Diaz, Brian Chhan, Daniel Zhang\n\nEmails: sebastian.diaz@ucalgary.ca\n" +
                "daniel.zhang2@ucalgary.ca\nbrian.chhan@ucalgary.ca\n\nVersion: 3.0\n This is a Valorant eSports Statistics Tracker");
        alert.show();
    }

}