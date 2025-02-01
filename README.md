## About CommuniTask
CommuniTASK is a complaint management system that allows users to submit complaints with corresponding photo, descriptions, and the type. The application provides an interface for users to report issues, track complaints, and manage responses efficiently. Created specifically for barangays to streamline the process of complaints within communities.
## System Flow
![low-level-diagram](https://img.plantuml.biz/plantuml/png/lLPDRzim3BqRy7yWSkuoTBi5oD2aBh33i0xDrilGWo8pgL3RylJH8Vz-aYnPyXTosoqcdyZ79vBAdLH4A5rNUKQeuWAUKB8I6nM-TsNDcZnh3OfHrf96mMgdrQk1C4eKumqSBb9XlG8YmNf6u10Gll8QMt961mnMQnn7jncX8FBro8aekxZMPaqZx3slsuemypCXqHlb4fKVWfzOXUcXuFEkF2k98aSY4LQ5_-LmnJxFycoxxG_0DtusVCqvYbB2TfjdlUl35wV81XuQXGAeG6jcf3BChCC2Ylq6db6mqsNeB_Rn-1CgBHgGcjeS7q-4LLfWZ14IRZoP2J1bb4flGvTC85MWE4okQbngiCDZQsz-MArr3iaKJ9gBk03twD-tQpXWKyORke5FQzXLLXml_0ryNeTwRSINCGSR8BdZYU3nzZTmqCUQAQ31b6VH6xG_c2x75D89duQvvys9YHgaWJfLXb5YmP6LT9aLbcFwhbZPT-2O_QWDDqPfR7hqGyaCXNWYsA-g_girxOnHOwEwwHIkbPSt8TX1KuRNAFhWhJzGeZ9j9sTPMi4zN8IGTj22wfFOGNhj3bsfF1WxS3afpPGiuL6hQKrXpFveb0eg9yrql7fMFr6OVMIha6RCZCfNYAL3vjV5p7HrNIyjg1-q9V6xuVrEtjdPqZWI-kR3TUkpos9FTz__QnEvR8jhA789-toepMZr3ohghOFbqmTAaiyDGD1DkjwFy-lMpM0KVBfdXd_Zgo3Dsg7kiHj8QXKpYWY6xqb3pudNCVFgpICdoPCjCvizNL6XTMdozartNSyrXKttdQUv76iaT0eSzY3NQgu9bmOjQOkP4Pkxw-N9kZFlXFjVymy0)
## How CommuniTask works
CommuniTask highlights the principle of encapsulation through various components, with a focus on the most crucial aspects.
1. Encapsulation in Database Management (database.java)
The `connect()` method is a private function that establishes a secure connection to the database, preventing direct access from other classes.
```java
private static Connection connect() {
    Connection conn = null;
    try {
        conn = DriverManager.getConnection("jdbc:sqlite:communitask.db");
    } catch (SQLException e) {
        System.out.println("Database Connection Failed: " + e.getMessage());
    }
    return conn;
}
```

The `validateLogin()` method encapsulates the authentication process by using a prepared statement to verify user credentials, reducing the risk of SQL injection.

```java
public static String validateLogin(String username, String password) {
    String role = null;
    String query = "SELECT role FROM users WHERE username=? AND password=?";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            role = rs.getString("role");
        }
    } catch (SQLException e) {
        System.out.println("Login validation error: " + e.getMessage());
    }
    return role;
}
```

The `addComplaint()` method ensures complaints are safely stored by encapsulating the SQL execution inside a prepared statement.

```java
public static boolean addComplaint(int userId, String details, String date) {
    String query = "INSERT INTO complaints (user_id, details, date) VALUES (?, ?, ?)";
    try (Connection conn = connect();
         PreparedStatement pstmt = conn.prepareStatement(query)) {
        pstmt.setInt(1, userId);
        pstmt.setString(2, details);
        pstmt.setString(3, date);
        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        System.out.println("Complaint submission failed: " + e.getMessage());
        return false;
    }
}
```

## 2. Encapsulation in Scene Management (SceneSwitcher.java)

The `switchScene()` method centralizes UI transitions, preventing redundant navigation logic across the application.

```java
public static void switchScene(ActionEvent event, String fxmlFile) {
    try {
        Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlFile));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    } catch (IOException e) {
        System.out.println("Scene switching error: " + e.getMessage());
    }
}
```

## 3. Encapsulation in User Data Management (User.java)

The `User` class keeps attributes private, allowing controlled access through getter methods to prevent unauthorized modification.

```java
public class User {
    private int id;
    private String username;
    private String role;

    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}
```

## 4. Encapsulation in Complaint Submission (ComplaintSubmissionController.java)

The `submitComplaint()` method ensures complaint validation before database insertion, enforcing data integrity.

```java
public void submitComplaint() {
    String details = complaintDetailsField.getText();
    int userId = getCurrentUserId();
    String date = LocalDate.now().toString();

    if (!details.isEmpty()) {
        boolean success = database.addComplaint(userId, details, date);
        if (success) {
            showMessage("Complaint submitted successfully.");
        } else {
            showMessage("Submission failed.");
        }
    } else {
        showMessage("Please enter complaint details.");
    }
}
```

## 5. Encapsulation in Admin Dashboard (AdminController.java)

The `fetchAllComplaints()` method retrieves complaint records securely, using encapsulated database access.

```java
public ObservableList<Complaint> fetchAllComplaints() {
    ObservableList<Complaint> complaintsList = FXCollections.observableArrayList();
    String query = "SELECT * FROM complaints";

    try (Connection conn = database.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(query)) {
        while (rs.next()) {
            int id = rs.getInt("id");
            String details = rs.getString("details");
            String status = rs.getString("status");
            complaintsList.add(new Complaint(id, details, status));
        }
    } catch (SQLException e) {
        System.out.println("Error fetching complaints: " + e.getMessage());
    }

    return complaintsList;
}
```
## How to use CommuniTask

# User Registration & Login

Open the application and navigate to the login page.

Click "Register" if you are a new user.

Provide your full name, email, and a secure password.

Once registered, log in with your credentials.

# Submitting a Complaint

Navigate to the "Submit Complaint" section.

Provide a detailed description of the issue.

Select the type of complaint (e.g., sanitation, noise, health, etc.).

Attach a photo for better context (optional but recommended).

# Admin Dashboard 

Admins login to access the dashboard.

View a list of submitted complaints with filtering options.
