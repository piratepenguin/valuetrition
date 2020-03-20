package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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

    public static void initWindow(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        label = new Label(message);
    }

    public static void initScene() {
        HBox layout1 = new HBox(10);
        layout1.getChildren().addAll(label, answerField, enterButton);
        layout1.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
    }

}
