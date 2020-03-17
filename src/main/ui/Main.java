package ui;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Scanner;

import model.*;

public class Main extends Application {

    Stage window;
    Scene scene1;
    Scene scene2;
    Button button;
    Button button2;
    Button button3;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        // setting window icon, title, border
        window = primaryStage;
        window.setTitle("Nutritivity");
        window.getIcons().add(new Image(Main.class.getResourceAsStream("/NutritivityLogo.png")));

        // setting buttons for all future scenes
        button = new Button("Let's Go!");
        button2 = new Button("Go to window 2");
        button3 = new Button("Go back to window 1");

        button.setOnAction(e -> {
            System.out.println("hello");
            System.out.println("yo lambda is sick dude");
        });
        button2.setOnAction(e -> window.setScene(scene2));
        button3.setOnAction(e -> window.setScene(scene1));

        // Scene 1 - uses vertical layout
        VBox layout1 = new VBox(10);
        Label label1 = new Label("Welcome Wizards!");
        layout1.getChildren().addAll(label1, button, button2);
        scene1 = new Scene(layout1, 300, 200);


        // Scene 2 - uses horizontal layout
        HBox layout2 = new HBox(15);
        layout2.getChildren().add(button3);
        scene2 = new Scene(layout2, 300, 250);


        // initialize
        window.setScene(scene1);
        window.show();
    }


//    @Override
//    public void handle(ActionEvent event) {
//        if (event.getSource() == button) {
//            System.out.println("helo s");
//         //NutritivityApp app = new NutritivityApp();
//        }
//    }

}

