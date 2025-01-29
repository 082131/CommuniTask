/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.communitask2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class ConcernSceneController implements Initializable {


    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private Pane scrollPane;
    @FXML
    private Button reportIssue;
    @FXML
    private Button pendingConcern;
    @FXML
    private ImageView homeBtn;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    private void ConcernEvent(ActionEvent event) {
        SceneSwitcher.switchScene(event, "ComplainSubmission.fxml");
    }


    @FXML
    private void backToHome(MouseEvent event) {
        SceneSwitcher.switchScene(event, "HomeScene.fxml");
    }

    @FXML
    private void pendings(MouseEvent event) {
        SceneSwitcher.switchScene(event, "PendingScene.fxml");
    }

    @FXML
    private void ReportEvent(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ComplainSubmission.fxml");
    }

    @FXML
    private void ToAccountScene(MouseEvent event) {
        SceneSwitcher.switchScene(event, "AccountScene.fxml");
    }
}
