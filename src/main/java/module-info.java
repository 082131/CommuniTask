module com.communitask {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires javafx.graphics;

    opens com.communitask2 to javafx.fxml;
    exports com.communitask2;
    requires jbcrypt;
}