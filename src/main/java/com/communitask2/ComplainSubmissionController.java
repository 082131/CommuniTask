package com.communitask2;

import java.io.File;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class ComplainSubmissionController{

    @FXML
    private ImageView backBtn;

    @FXML
    private TextField complainDescription;

    @FXML
    private TextField complaintTitleField;

    @FXML
    private Button submitButton;

    @FXML
    private Label uploadPhotoButton; // This is where the filename will be displayed

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> TypeCB; // ComboBox for complaint types

    public void initialize(){
        // Predefined complaint types
        TypeCB.getItems().addAll(
                "Noise", 
                "Waste", 
                "Health", 
                "Sanitation", 
                "Other"
        );
    }

    @FXML
    private void backToMainScene(MouseEvent event) {
        SceneSwitcher.switchScene(event, "ConcernScene.fxml");
    }

    @FXML
    void recordInfo(MouseEvent event) {
        // Implement this function as necessary for form submission or handling.
    }

    @FXML
    private void handleUploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Show the file chooser dialog
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            // If a file is selected, set the filename as text on the Label
            uploadPhotoButton.setText(file.getName());
        } else {
            // If no file is selected, display "No file selected"
            uploadPhotoButton.setText("No file selected");
        }
    }
}
