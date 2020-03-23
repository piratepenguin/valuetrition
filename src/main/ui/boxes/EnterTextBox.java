package ui.boxes;

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


public class EnterTextBox {

    static String answer;
    static TextField answerField;
    static Label label;
    static Stage window;
    static Button enterButton;

    public static String display(String title, String message) {

        initWindow(title,message);
        initButtons();
        initTextBox();
        initScene();

        return answer;
    }


    public static String display(String title, String message, int width, int height) {

        initWindow(title,message,width,height);
        initButtons();
        initTextBox();
        initScene();

        return answer;
    }

    public static void initTextBox() {

        // create text box for user to input a string
        answerField = new TextField();
        answerField.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5),
                Insets.EMPTY)));
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

    public static void initWindow(String title, String message, int width, int height) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(width);
        window.setHeight(height);
        label = new Label(message);
    }

    public static void initWindow(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        label = new Label(message);
    }

    public static void initScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(30,10,10,30));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY,
                Insets.EMPTY)));

        GridPane.setConstraints(label, 0,0);
        GridPane.setConstraints(answerField, 0,1);
        GridPane.setConstraints(enterButton, 0,2);

        grid.getChildren().addAll(label, answerField, enterButton);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
    }

}
