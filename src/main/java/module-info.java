module com.example.exameninterfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.example.exameninterfaces to javafx.fxml;
    opens com.example.exameninterfaces.models to javafx.base;
    exports com.example.exameninterfaces;
}