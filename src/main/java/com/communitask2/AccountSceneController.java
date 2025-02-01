package com.communitask2;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

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

    private Connection connection;
    @FXML
    private Button logoutbtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initializing AccountSceneController");
        try {
            connection = database.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (connection == null) {
            System.out.println("Database connection failed!");
        } else {
            System.out.println("Database connected successfully.");
        }

        // Fetch logged-in user's email from LoginController
        String userEmail = LoginController.getLoggedInUserEmail();
        
        if (userEmail != null && !userEmail.isEmpty()) {
            loadUserData(userEmail);
        } else {
            System.out.println("No logged-in user email found.");
        }
    }

    private void loadUserData(String userEmail) {
        if (connection == null) {
            System.out.println("Error: Database connection is null.");
            return;
        }

        String query = "SELECT first_name, email, age, contactnum, birthday, barangay FROM users WHERE email = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, userEmail);
            System.out.println("Fetching data for user: " + userEmail);

            try (ResultSet rs = stmt.executeQuery()) {  // Ensures ResultSet is closed after usage
                if (rs.next()) {
                    System.out.println("User found: " + rs.getString("first_name"));

                    // ✅ Extract data BEFORE calling Platform.runLater
                    String firstName = rs.getString("first_name");
                    String email = rs.getString("email");
                    int age = rs.getInt("age");
                    String birthday = rs.getString("birthday");
                    String address = rs.getString("barangay");
                    int contactNum = rs.getInt("contactnum");

                    // Now safely update the UI on the JavaFX application thread
                    Platform.runLater(() -> {
                        FirstNameField.setText(firstName);
                        EmailField.setText(email);
                        AgeField.setText("        "+String.valueOf(age));
                        BdayField.setText(birthday);
                        AddressField.setText(address);
                        NumField.setText(String.valueOf(contactNum));
                    });

                } else {
                    System.out.println("No user data found!");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goBackBtn(MouseEvent event) {
        SceneSwitcher.switchScene(event, "HomeScene.fxml");
    }

    @FXML
    void recordInfo(MouseEvent event) {
        // Logic for saving updated user information
    }
    
    @FXML
    void toPersonalPane(MouseEvent event) {
        PersonalPane.toFront();
    }

    @FXML
    void toSecurityPane(MouseEvent event) {
        SecurityPane.toFront();
    }

    @FXML
    private void logout(MouseEvent event) {
        Platform.exit();
    }
}
