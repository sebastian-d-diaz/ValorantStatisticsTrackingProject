package ca.ucalgary.part3groupprojectcpsc233.app.fileMenuControllers;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.util.Reader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

import java.io.File;

public class SaveController {
    @FXML
    private MenuItem save;

    @FXML
    private Label labelStatus;

    //state of the program
    private Data data;

    /**
     * Fetching state of program
     * @param data
     */
    public SaveController(Data data){
        this.data = data;
    }

    @FXML
    public void saveState(File file){
        //Checking if file can be read from
        if(file.canRead()){
            //opening file
            Reader.GUIsave(data,file);
            labelStatus.setLayoutX(750.0);
            labelStatus.setText("File Saved Successfully :)");

        }//unable to read from file
        else{
            labelStatus.setLayoutX(760.0);
            labelStatus.setText("Unable to Save File :(");
        }
    }
}
