package com.zkurdya.chapter5homework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainScreen extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.setMinWidth(900);
            stage.setTitle("Student Management System");
            stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("students.png"))));
            stage.show();
        } catch (IOException ignored) {}
    }

    public static void main(String[] args) {
        launch(args);
    }
}
// Zaki Kurdya, 120200706