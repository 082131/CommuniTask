package com.communitask2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader (App.class.getResource("Login.fxml"));
  
    Scene scene = new Scene(fxmlLoader.load(), 360, 640);
    stage.setTitle("CommuniTASK");
    stage.setScene(scene);
    stage.show();
   
    }
    public static void main(String[] args) {
        launch();
       }
}