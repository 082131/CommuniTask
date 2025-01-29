package com.communitask2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ComplainSubmissionController {

    @FXML
    private ImageView backBtn;

    @FXML
    private TextField complainDescription;

    @FXML
    private TextField complaintTitleField;

    @FXML
    private Button submitButton;


    @FXML
    private Button uploadPhotoButton;
    @FXML
    private Button cancelBtn;

    @FXML
    void backToMain(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ConcernScene.fxml");
    }

    @FXML
    void recordInfo(MouseEvent event) {

    }

    @FXML
    private void backToMainScene(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ConcernScene.fxml");
    }

}
