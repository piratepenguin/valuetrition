package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EnterTextBoxWithFooter {

    static String answer;
    static TextField answerField;
    static Label footerLabel;
    static Label messageLabel;
    static Stage window;
    static Button enterButton;

    public static String display(String title, String message, String footer)  {

        initWindow(title,message,footer);
        initButtons();
        initTextBox();
        initScene();

        return answer;
    }

    public static void initTextBox() {

        // create text box for user to input a string
        answerField = new TextField();
        answerField.setPromptText("Type here");
    }

    public static void initButtons() {
        // create buttons
        enterButton = new Button("enter");
        enterButton.setOnAction(e -> {
            answer = answerField.getText();
            window.close();
        });
    }

    public static void initWindow(String title, String message, String footer) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setHeight(200);
        messageLabel = new Label(message);
        footerLabel = new Label(footer);
    }

    public static void initScene() {
        GridPane layout = new GridPane();
        HBox layout1 = new HBox(10);
        HBox layout2 = new HBox(10);
        GridPane.setConstraints(layout1, 0,1);
        GridPane.setConstraints(layout2, 0,3);
        layout1.getChildren().addAll(messageLabel, answerField, enterButton);
        layout1.setAlignment(Pos.CENTER);
        layout2.getChildren().add(footerLabel);
        layout2.setAlignment(Pos.BASELINE_CENTER);
        layout.getChildren().addAll(layout1,layout2);
        layout.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
