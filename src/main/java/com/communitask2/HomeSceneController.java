package com.communitask2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

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
    private Connection connection;

    public void initialize(){
        
    String query = "SELECT first_name FROM users WHERE email = ?";    
    try (Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery()) {

    if (rs.next()) {  
        String firstName = rs.getString("first_name");  

        Platform.runLater(() -> {
            usernameLabel.setText(firstName); // Set actual name, not a string literal
        });
    }
    } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        
    
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