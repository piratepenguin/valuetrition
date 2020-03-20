package ui.food;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import model.Food;
import model.FoodList;

public class CreateFoodUI extends FoodUI {


    // text fields
    static TextField nameField;
    static TextField weightField;
    static TextField costField;
    static TextField caloriesField;
    static TextField carbsField;
    static TextField fatsField;
    static TextField proteinField;
    static Button createButton;
    static Label enterBelowLabel;
    static Label foodInfoLabel;

    static FoodList database;


    public static void display(FoodList database1) {

        purposeString = "New Food Creator";
        database = database1;
        initTextFields();
        initLabels();
        initButtons();
        initScene();
        initWindow(350,400);
        window.showAndWait();
    }

    public static void initTextFields() {

        nameField = new TextField("");
        weightField = new TextField(Integer.toString(0));
        costField = new TextField(Double.toString(0));
        caloriesField = new TextField(Integer.toString(0));
        carbsField = new TextField(Integer.toString(0));
        fatsField = new TextField(Integer.toString(0));
        proteinField = new TextField(Integer.toString(0));
    }

    public static void initLabels() {

        nameLabel = new Label("Name");
        weightLabel = new Label("Weight");
        caloriesLabel = new Label("Calories");
        costLabel = new Label("Cost");
        carbsLabel = new Label("Carbs");
        fatsLabel = new Label("Fats");
        proteinLabel = new Label("Protein");
        enterBelowLabel = new Label("Enter Below");
        foodInfoLabel = new Label("Food Info");
    }

    public static void initButtons() {
        createButton = new Button("Create Food");
        createButton.setOnAction(e -> create());
    }

    public static void initScene() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();

        grid.getChildren().addAll(nameLabel, weightLabel, costLabel, caloriesLabel,
                carbsLabel, fatsLabel, proteinLabel, foodInfoLabel, enterBelowLabel);
        grid.getChildren().addAll(nameField, weightField, costField);
        grid.getChildren().addAll(caloriesField, carbsField, fatsField, proteinField, createButton);
        grid.setBackground(new Background(new BackgroundFill(Color.LIGHTSALMON, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(grid);
    }

    public static void initGridConstraints() {

        GridPane.setConstraints(foodInfoLabel,0,0);
        GridPane.setConstraints(enterBelowLabel,1,0);
        GridPane.setConstraints(nameLabel,0,1);
        GridPane.setConstraints(weightLabel,0,2);
        GridPane.setConstraints(costLabel,0,3);
        GridPane.setConstraints(caloriesLabel,0,4);
        GridPane.setConstraints(carbsLabel,0,5);
        GridPane.setConstraints(fatsLabel,0,6);
        GridPane.setConstraints(proteinLabel,0,7);
        GridPane.setConstraints(nameField,1,1);
        GridPane.setConstraints(weightField,1,2);
        GridPane.setConstraints(costField,1,3);
        GridPane.setConstraints(caloriesField,1,4);
        GridPane.setConstraints(carbsField,1,5);
        GridPane.setConstraints(fatsField,1,6);
        GridPane.setConstraints(proteinField,1,7);
        GridPane.setConstraints(createButton,1,9);
    }

    public static void create() {
        String name = nameField.getText();
        double weight = Double.parseDouble(weightField.getText());
        double cost = Double.parseDouble(costField.getText());
        double calories = Double.parseDouble(caloriesField.getText());
        double carbs = Double.parseDouble(carbsField.getText());
        double fats = Double.parseDouble(fatsField.getText());
        double protein = Double.parseDouble(proteinField.getText());

        food = new Food(name, weight, cost, calories, carbs, fats, protein);
        database.add(food);
        window.close();
    }


}
