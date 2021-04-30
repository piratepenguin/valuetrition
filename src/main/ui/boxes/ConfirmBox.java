package ui.boxes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    Button yesButton;
    Button noButton;

    Stage window;
    Scene scene;

    VBox layout;
    Label label;



    public boolean display(String title, String message) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        label = new Label(message);

        yesButton = new Button("yes");
        noButton = new Button("no");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        setScene();

        return answer;
    }

    public boolean display(String title, String message, int width, int height) {

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(width);
        window.setHeight(height);
        label = new Label(message);

        // create buttons
        yesButton = new Button("yes");
        noButton = new Button("no");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        setScene();

        return answer;
    }

    private void setScene() {
        layout = new VBox(10);
        layout.getChildren().addAll(label, noButton, yesButton);
        layout.setAlignment(Pos.CENTER);
        layout.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
