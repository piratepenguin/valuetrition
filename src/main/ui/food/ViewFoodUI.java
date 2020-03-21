package ui.food;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import model.food.Food;


public class ViewFoodUI extends FoodUI {

    Button editInfoButton;

    public void displayFoodInfo(Food givenFood) {

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

    public void initButtons() {
        // buttons
        editInfoButton = new Button("edit");
        editInfoButton.setOnAction(e -> {
            EditFoodUI editFoodUI = new EditFoodUI(food);
            editFoodUI.displayEditMenu();
        });
    }

    public void initSceneForViewing() {

        GridPane.setConstraints(editInfoButton, 1,10);
        grid.getChildren().addAll(editInfoButton);
        grid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(grid);
    }

}

