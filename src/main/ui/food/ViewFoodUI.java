package ui.food;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import javafx.scene.Scene;
import javafx.scene.control.Label;


public class ViewFoodUI extends FoodUI {

    static Button editInfoButton;

    public static void displayFoodInfo(Food givenFood) {

        food = givenFood;
        purposeString = "Food Info";
        initFood();
        initLabels();
        initButtons();
        initScene();
        initSceneForViewing();
        initWindow(600,600);
        window.show();
    }

    public static void initButtons() {
        // buttons
        editInfoButton = new Button("edit");
        editInfoButton.setOnAction(e -> EditFoodUI.displayEditMenu(food));
    }

    public static void initSceneForViewing() {

        GridPane.setConstraints(editInfoButton, 1,10);
        grid.getChildren().addAll(editInfoButton);
        grid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(grid);
    }

}

