package ui.boxes;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(400);
        window.setHeight(200);
        Label label = new Label(message);

        // create buttons
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label, noButton, yesButton);
        layout1.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }

    public static boolean display(String title, String message, int width, int height) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(width);
        window.setHeight(height);
        Label label = new Label(message);

        // create buttons
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(label, noButton, yesButton);
        layout1.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(layout1);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
