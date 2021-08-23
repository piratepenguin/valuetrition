package ui.boxes;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public abstract class AbstractBox {


    static Scene scene;

    public static void setStyle(VBox grid) {
        grid.setStyle("-fx-background: linear-gradient(to bottom, #a3f1c4, #6df1fa, #2980B9);");
        scene = new Scene(grid, 600, 600);
        scene.getStylesheets().add("./css/login.css");

    }
    public static void setStyle(GridPane grid) {
        grid.setStyle("-fx-background: linear-gradient(to bottom, #a3f1c4, #6df1fa, #2980B9);");
        scene = new Scene(grid, 600, 600);
        scene.getStylesheets().add("./css/login.css");

    }

}
