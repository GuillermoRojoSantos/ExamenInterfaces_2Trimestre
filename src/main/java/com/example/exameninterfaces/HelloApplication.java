package com.example.exameninterfaces;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1162, 654);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setMinHeight(690);
        stage.setMinWidth(1162);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}