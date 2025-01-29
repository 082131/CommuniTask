package com.communitask2;

import com.communitask2.SceneSwitcher;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeSceneController{ // implements Initializable

    @FXML
    private AnchorPane HomeScene;
    @FXML
    private ImageView raiseConcern;
    @FXML
    private ImageView ConcernBtn;
    @FXML
    private ImageView AccountScene;
    @FXML
    private Label usernameLabel;  
    
    /*
    public void initialize(URL url, ResourceBundle rb) {
        loadUsername();  // load the username based on the current user
    }
    private void loadUsername() {
        try {
            Connection conn = database.getConnection();  //DB connection
            String query = "SELECT username FROM users WHERE id = 1"; // baes on db scheme
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                usernameLabel.setText(rs.getString("username"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Error loading username: " + e.getMessage());
        }
    }
    */
    @FXML
    private void concernBtn(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ConcernScene.fxml");
    }

    @FXML
    private void ToConcernScene(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ConcernScene.fxml");
    }

    @FXML
    private void ToAccountScene(MouseEvent event) {
        SceneSwitcher.switchScene(event, "AccountScene.fxml");
    }
}