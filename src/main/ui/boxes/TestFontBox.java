package ui.boxes;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;

public class TestFontBox {



    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Alert");
        window.setWidth(400);
        window.setHeight(3000);


        // create buttons
        Button okButton = new Button("ok");
        okButton.setOnAction(e -> System.exit(1));


        VBox layout = new VBox(2);
        layout.getChildren().add(okButton);
        List<String> list = javafx.scene.text.Font.getFamilies();
        for (String str : list) {
            Label label = new Label("username: in font" + str);
            label.setFont(new Font(str, 15));
            layout.getChildren().add(label);
        }
        layout.setAlignment(Pos.BASELINE_CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }


}
