package com.communitask2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.fxml.FXML;
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
    private Label uploadPhotoButton; 

    private File selectedFile; 

    @FXML
    private Button cancelBtn;

    @FXML
    private ComboBox<String> TypeCB; 
    @FXML
    private TextField complainAddressField;
    @FXML
    private Label errMess;
    @FXML
    private Label successMes;

    public void initialize(){
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
    
    private byte[] getImageBytes(String imagePath) {
        try {
            File file = new File(imagePath);
            if (!file.exists()) { // Check if file exists before reading
                System.out.println("File not found: " + imagePath);
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            fis.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @FXML
private void recordInfo(MouseEvent event) {
    String type = TypeCB.getValue();
    String title = complaintTitleField.getText().trim();
    String description = complainDescription.getText().trim();
    String address = complainAddressField.getText().trim();
    String imagePath = uploadPhotoButton.getText().equals("No file selected") ? null : uploadPhotoButton.getText();

    if (type == null || title.isEmpty() || description.isEmpty()) {
        errMess.setText("Please select a concern type, provide a title, and a description.");
        return;
    } else {
        errMess.setText("");

        try (Connection conn = database.getConnection()) {
            String sql;
            if (imagePath != null) {
                sql = "INSERT INTO concern_db (title, concern_type, description, photos, address) VALUES (?, ?, ?, ?, ?)";
            } else {
                sql = "INSERT INTO concern_db (title, concern_type, description, address) VALUES (?, ?, ?, ?)";
            }

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, title); 
                stmt.setString(2, type);
                stmt.setString(3, description);

                if (imagePath != null) {
                    stmt.setBytes(4, getImageBytes(imagePath)); // Convert image to BLOB
                    stmt.setString(5, address);
                } else {
                    stmt.setString(4, address);
                }

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Complaint submitted successfully!");
                    successMes.setText("Complaint submitted successfully...");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    @FXML
    private void handleUploadPhoto() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));

        // Show the file chooser dialog
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            selectedFile = file; // Save the file reference
            uploadPhotoButton.setText(file.getAbsolutePath()); // Store full path
        } else {
            uploadPhotoButton.setText("No file selected");
        }
    }

}
