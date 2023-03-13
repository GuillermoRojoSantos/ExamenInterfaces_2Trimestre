module com.example.exameninterfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires mysql.connector.java;
    requires jasperreports;
    requires java.sql;
    requires java.desktop;


    opens com.example.exameninterfaces to javafx.fxml;
    opens com.example.exameninterfaces.models to javafx.base;
    exports com.example.exameninterfaces;
}