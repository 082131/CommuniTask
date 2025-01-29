
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.communitask2;
import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class LoginController implements Initializable {
    @FXML
    private AnchorPane scene1AnchorPane;
    @FXML
    private Button btnLogin;
    @FXML
    private Button RegisterBtn;
    @FXML
    private ComboBox<Integer> AgeCB;
    @FXML
    private TextField PasswordRegisField;
    @FXML
    private Button CreateAccBtn;
    @FXML
    private Pane RegisterPane1;
    @FXML
    private Button AgreeBtn;
    @FXML
    private Pane LoginPane;
    @FXML
    private ComboBox<String> MaritalCB;
    @FXML
    private Button disagreeBtn;
    @FXML
    private TextField FirstnameField;
    @FXML
    private TextField MiddlenameField;
    @FXML
    private TextField Barangay;
    @FXML
    private TextField EmailRegisterField;
    @FXML
    private TextField ConfirmPasswordField;
    @FXML
    private TextField emailLogInField;
    @FXML
    private TextField passwordLoginField;
    @FXML
    private Label errMess2;
    @FXML
    private Pane RegisterPane2;
    @FXML
    private ImageView backBtn2;
    @FXML
    private ImageView backBtn;
    @FXML
    private Label errMess1;
    @FXML
    private TextField SurnameField;
    @FXML
    private DatePicker DatePicker;
    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Integer> ages = FXCollections.observableArrayList();
        ObservableList<String> status = FXCollections.observableArrayList();
        String maritalStatus[] = {"Married", "Single", "Divorce", "Separated", "Widowed"};

        for (int i = 18; i <= 100; i++) {
            ages.add(i);
        }
        for (String statusEntry : maritalStatus) {
            status.add(statusEntry);
        }
        MaritalCB.setItems(status);
        AgeCB.setItems(ages);
        AgeCB.setValue(18);
    }

    private static String loggedInUserEmail; // Store logged-in email

    public static String getLoggedInUserEmail() {
        return loggedInUserEmail;
    }

    @FXML
    private void buttonHandler(ActionEvent event) throws IOException {
        String email = emailLogInField.getText().trim();
        String password = passwordLoginField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            errMess1.setText("Please fill all blank fields");
            return;
        }

        if (validateLogin(email, password)) {
            loggedInUserEmail = email;  // Store email when login is successful
            SceneSwitcher.switchScene(event, "HomeScene.fxml");
        } else {
            errMess1.setText("Invalid Credentials");
        }
    }

    private boolean validateLogin(String email, String password) {
        String selectUserSQL = "SELECT password FROM users WHERE email = ?;";
        try (Connection connect = database.getConnection();
             PreparedStatement prepare = connect.prepareStatement(selectUserSQL)) {

            prepare.setString(1, email);
            try (ResultSet rs = prepare.executeQuery()) {
                if (rs.next()) {
                    String storedHashedPassword = rs.getString("password");

                    // Debugging output
                    System.out.println("Stored Hash: " + storedHashedPassword);
                    System.out.println("Entered Password: " + password);

                    if (BCrypt.checkpw(password, storedHashedPassword)) {
                        System.out.println("Login successful!");
                        return true; // Password is correct
                    } else {
                        System.out.println("Incorrect password!");
                        return false; // Incorrect password
                    }
                } else {
                    System.out.println("User not found!");
                    return false; // User does not exist
                }
            } catch (SQLException e) {
                System.err.println("Error during ResultSet processing: " + e.getMessage());
                return false; // Handle any issues with ResultSet access
            }
        } catch (SQLException e) {
            System.err.println("Error during login validation: " + e.getMessage());
            errMess1.setText("An error occurred during login. Please try again.");
            return false; // Handle connection or query issues
        }
    }

    private boolean extracted() {
        System.err.println("Database connection is null!");
        return false;
    }

    @FXML
    private void ToRegisterPane1(MouseEvent event) {
        RegisterPane1.toFront();
    }

    @FXML
    private void ToLoginPane(MouseEvent event) {
        LoginPane.toFront();
    }

    @FXML
    private void ToRegisterAcc(MouseEvent event) {
        RegisterPane2.toFront();
    }

    @FXML
    private void CreateAccount(MouseEvent event) {
        System.out.println("CreateAccount button clicked!"); // Debugging
        if(toDatabase()){
            SceneSwitcher.switchScene(event, "HomeScene.fxml");
        }
    }

    private boolean toDatabase() {
        String firstName = FirstnameField.getText().trim();
        String middleName = MiddlenameField.getText().trim();
        String surname = SurnameField.getText().trim();
        String email = EmailRegisterField.getText().trim();
        String password = PasswordRegisField.getText().trim();
        String confirmPassword = ConfirmPasswordField.getText().trim();
        String barangay = Barangay.getText().trim();
        Integer age = AgeCB.getValue();
        String maritalStatus = MaritalCB.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String birthday = DatePicker.getValue()!= null ? DatePicker.getValue().format(formatter) : null;

        if (firstName.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                age == null || maritalStatus == null || birthday == null) {
            errMess2.setText("Please fill all required fields.");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            errMess2.setText("Passwords do not match.");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String insertSQL = "INSERT INTO users (first_name, middle_name, surname, email, password, age, marital_status, birthday, barangay) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = database.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, middleName);
            preparedStatement.setString(3, surname);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, hashedPassword);
            preparedStatement.setInt(6, age);
            preparedStatement.setString(7, maritalStatus);
            preparedStatement.setString(8, birthday);
            preparedStatement.setString(9, barangay);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                return true;
            } else {
                errMess2.setText("Failed to create account. Please try again.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errMess2.setText("An error occurred: " + e.getMessage());
            return false;
        }
    }

}


