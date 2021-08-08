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
import javafx.scene.text.Font;
import model.food.Food;
import model.food.FoodList;
import model.exceptions.NullFoodException;
import ui.boxes.AlertBox;

public class CreateFoodUI extends FoodUI {


    // text fields
    TextField nameField;
    TextField weightField;
    TextField costField;
    TextField caloriesField;
    TextField carbsField;
    TextField fatsField;
    TextField proteinField;

    Button createButton;
    Button uploadImageButton;

    Label enterBelowLabel;
    Label foodInfoLabel;

    String name;
    double weight;
    double cost;
    double calories;
    double carbs;
    double fats;
    double protein;

    public Food display() {

        purposeString = "New Food Creator";
        initTextFields();
        initLabels();
        initButtons();
        initScene();
        initWindow(500,500);
        window.showAndWait();
        return food;
    }

    public void initTextFields() {

        nameField = new TextField("");
        weightField = new TextField(Integer.toString(0));
        costField = new TextField(Double.toString(0));
        caloriesField = new TextField(Integer.toString(0));
        carbsField = new TextField(Integer.toString(0));
        fatsField = new TextField(Integer.toString(0));
        proteinField = new TextField(Integer.toString(0));
    }

    public void initLabels() {

        nameLabel = new Label("Name");
        weightLabel = new Label("Weight");
        caloriesLabel = new Label("Calories");
        costLabel = new Label("Cost");
        carbsLabel = new Label("Carbs");
        fatsLabel = new Label("Fats");
        proteinLabel = new Label("Protein");
        enterBelowLabel = new Label("Enter Below");
        enterBelowLabel.setFont(new Font("Arial", 20));
        foodInfoLabel = new Label("Food Info   ");
        foodInfoLabel.setFont(new Font("Arial", 20));
    }

    public void initButtons() {
        createButton = new Button("Create Food");
        createButton.setOnAction(e -> create());
        createButton.setFont(new Font("Arial", 20));
        createButton.setTextFill(Color.MEDIUMSEAGREEN);
        uploadImageButton = new Button("Upload\nImage");
        uploadImageButton.setOnAction(e -> uploadImage());
        uploadImageButton.setFont(new Font("Arial", 20));
        uploadImageButton.setTextFill(Color.MEDIUMSEAGREEN);
    }

    public void initScene() {
        grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();

        grid.getChildren().addAll(nameLabel, weightLabel, costLabel, caloriesLabel,
                carbsLabel, fatsLabel, proteinLabel, foodInfoLabel, enterBelowLabel);
        grid.getChildren().addAll(nameField, weightField, costField);
        grid.getChildren().addAll(caloriesField, carbsField, fatsField, proteinField, createButton);
//        grid.getChildren().add(uploadImageButton);
        grid.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle(grid);
    }


    public void initGridConstraints() {

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
        GridPane.setConstraints(uploadImageButton,7,4);
    }

    public void create() {

        name = nameField.getText();
        weight = Double.parseDouble(weightField.getText());
        cost = Double.parseDouble(costField.getText());
        calories = Double.parseDouble(caloriesField.getText());
        carbs = Double.parseDouble(carbsField.getText());
        fats = Double.parseDouble(fatsField.getText());
        protein = Double.parseDouble(proteinField.getText());

        try {
            checkFormNotBlank();
            food = new Food(name, weight, cost, calories, carbs, fats, protein);
        } catch (NullFoodException ex) {
            AlertBox.display("Incomplete", "Please enter all necessary values", 400, 120);
        }
    }

    public void setFields(String name, double weight, double cost, double calories, double carbs, double fats,
                          double protein) {
        this.name = name;
        this.weight = weight;
        this.cost = cost;
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.protein = protein;
    }


    public void checkFormNotBlank() throws NullFoodException {
        if (name.equals("") || (carbs == 0.0 && fats == 0.0 && protein == 0.0) || calories == 0.0) {
            throw new NullFoodException();
        }
    }

    private void uploadImage() {
    }

}
