
/**
 * Authors: Sebastian Diaz, Brian Chhan, Daniel Zhang
 * Tutorial: Tut 08, TA: Samuel Osweiler
 */
package ca.ucalgary.part3groupprojectcpsc233.app;

import ca.ucalgary.part3groupprojectcpsc233.Data;
import ca.ucalgary.part3groupprojectcpsc233.util.Reader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    public static File file = null;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        MainController controller = fxmlLoader.getController();

        stage.widthProperty().addListener((width,oldValue,newValue)->{controller.setWidth((double)oldValue,(double)newValue);});
        stage.heightProperty().addListener((width,oldValue,newValue)->{controller.setHeight((double)oldValue,(double)newValue);});
        if (file != null && file.exists()){
            Data data = new Data();
            Reader.GUIload(data, file);
            controller.setData(data);
            controller.refreshPersonFields();

        }

        stage.setTitle("Valorant Esports Statistics Tracker");
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        if (args.length ==1){
            String filename = args[0];
            file = new File(filename);
        }
        launch(args);
    }
}