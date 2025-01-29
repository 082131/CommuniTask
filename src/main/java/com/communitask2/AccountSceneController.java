/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.communitask2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class AccountSceneController implements Initializable {
@FXML
    private TextField AddressField;

    @FXML
    private TextField AgeField;

    @FXML
    private TextField BdayField;

    @FXML
    private Button EditAccBtn;

    @FXML
    private Button EditAccBtn11;

    @FXML
    private Button EditAccBtn111;

    @FXML
    private TextField EmailField;

    @FXML
    private TextField FirstNameField;

    @FXML
    private TextField NumField;

    @FXML
    private Button PersonalButton;

    @FXML
    private Pane PersonalPane;

    @FXML
    private Button SecuirtyPaneBtn;

    @FXML
    private Pane SecurityPane;

    @FXML
    private ImageView goBackBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void goBackBtn(MouseEvent event) {
        SceneSwitcher.switchScene(event, "HomeScene.fxml");
    }

    @FXML
    void recordInfo(MouseEvent event) {

    }
    
    @FXML
    void toPersonalPane(MouseEvent event) {
        PersonalPane.toFront();
    }

    @FXML
    void toSecurityPane(MouseEvent event) {
        SecurityPane.toFront();
    }

}
