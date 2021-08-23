package ui.meal;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.calendar.Date;
import model.food.Food;
import model.meal.Log;
import model.meal.Meal;


public class LogMealUI {

    Log log;
    double amount;
    Date date;

    Button addToLogButton;
    GridPane grid;
    Scene scene;
    Stage window;

    TextField amountField;
    TextField dateField;

    Label dateLabel;
    Label amountLabel;
    Label headerLabel;

    public void display(Food food, Log log, Date date) {

        initParams(date, log);
        initTextFields();
        initLabels();
        initButtons(food);
        initGridLines();
        initScene();
        initWindow();

    }

    private void initGridLines() {
        GridPane.setConstraints(headerLabel,1,0);
        GridPane.setConstraints(amountLabel,0,1);
        GridPane.setConstraints(amountField,0,2);
        GridPane.setConstraints(dateLabel,3,1);
        GridPane.setConstraints(dateField,3,2);
        GridPane.setConstraints(addToLogButton,2,4);
    }

    public void initLabels() {
        headerLabel = new Label("Enter meal info");
        amountLabel = new Label("Weight:");
        dateLabel = new Label("Day:");
    }

    public void initButtons(Food food) {

        addToLogButton = new Button("Add Meal to Log");
        addToLogButton.setOnAction(e -> {
            log.logMeal(date, createMeal(food));
            window.close();
        });
    }

    private Meal createMeal(Food food) {
        amount = Double.parseDouble(amountField.getText());
        date = Integer.parseInt(dateField.getText());
        return new Meal(food, amount, date);
    }

    public void initTextFields() {
        amountField = new TextField();
        amountField.setPromptText("grams");
        dateField = new TextField(Date.viewAsString());
    }

    public void initScene() {
        grid = new GridPane();
        grid.getChildren().addAll(headerLabel, amountLabel, amountField, dateLabel, dateField, addToLogButton);
        grid.setAlignment(Pos.BASELINE_CENTER);
        scene = new Scene(grid);
    }

    public void initWindow() {
        // initializing window
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Log Meals");
        window.setWidth(600);
        window.setHeight(200);
        window.setScene(scene);
        window.show();
    }

    public void initParams(Date day, Log givenLog) {
        date = day;
        log = givenLog;
    }

















}
