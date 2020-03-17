package ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.Scanner;

import model.*;

public class Main extends Application {

    Button button;


    public static void main(String[] args) {

        launch(args);
        //NutritivityApp app = new NutritivityApp();

    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Nutritivity");
        button = new Button();
        button.setText("Let's Go!");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/NutritivityLogo.png")));

        Scene scene = new Scene(layout, 300, 250);
        primaryStage.setScene(scene);

        primaryStage.show();

    }
}

