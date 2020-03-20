package ui.meal;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Food;
import model.Meal;
import model.MealList;


public class LogMealUI {

    static Food food;
    static Meal meal;
    static MealList log;
    static double amount;
    static int date;

    static Button addToLogButton;
    static GridPane grid;
    static Scene scene;
    static Stage window;

    static TextField amountField;
    static TextField dateField;

    static Label dateLabel;
    static Label amountLabel;
    static Label headerLabel;

    public static void display(Food food, MealList log, int date) {

        initParams(food, date, log);
        initTextFields();
        initLabels();
        initButtons();
        initGridLines();
        initScene();
        initWindow();

    }

    private static void initGridLines() {
        GridPane.setConstraints(headerLabel,1,0);
        GridPane.setConstraints(amountLabel,0,1);
        GridPane.setConstraints(amountField,0,2);
        GridPane.setConstraints(dateLabel,3,1);
        GridPane.setConstraints(dateField,3,2);
        GridPane.setConstraints(addToLogButton,2,4);
    }

    public static void initLabels() {
        headerLabel = new Label("Enter meal info");
        amountLabel = new Label("Weight:");
        dateLabel = new Label("Day:");
    }

    public static void initButtons() {

        addToLogButton = new Button("Add Meal to Log");
        addToLogButton.setOnAction(e -> {
            createMeal();
            log.add(meal);
            window.close();
        });
    }

    private static void createMeal() {
        amount = Double.parseDouble(amountField.getText());
        date = Integer.parseInt(dateField.getText());
        meal = new Meal(food, amount, date);
    }

    public static void initTextFields() {
        amountField = new TextField();
        amountField.setPromptText("grams");
        dateField = new TextField(Integer.toString(date));
    }

    public static void initScene() {
        grid = new GridPane();
        grid.getChildren().addAll(headerLabel, amountLabel, amountField, dateLabel, dateField, addToLogButton);
        grid.setAlignment(Pos.BASELINE_CENTER);
        scene = new Scene(grid);
    }

    public static void initWindow() {
        // initializing window
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Log Meals");
        window.setWidth(600);
        window.setHeight(200);
        window.setScene(scene);
        window.show();
    }

    public static void initParams(Food food1, int day, MealList mealList) {
        food = food1;
        date = day;
        log = mealList;
    }

















}
