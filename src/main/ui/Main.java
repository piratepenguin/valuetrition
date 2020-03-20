package ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.*;
import persistence.readers.FoodReader;
import persistence.readers.MealReader;
import persistence.writers.Writer;
import ui.food.CreateFoodUI;
import ui.food.ViewFoodUI;
import ui.meal.LogMealUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Main extends Application {

    Stage window;
    Scene dashboard;
    Scene menu;
    static Button viewLogButton;
    static Button openMenuButton;
    static Button openDashboardButton;
    static Button logMealButton;
    static Button closeButton;
    static Button viewFoodButton;
    static Button createFoodButton;

    // imported from nutritivityApp
    private static final String MEALS_FILE = "./data/log.txt";
    private static final String FOODS_FILE = "./data/foods.txt";
    static FoodList database;
    static MealList log;
    static int date = 1;


    public static void main(String[] args) {
//        NutritivityApp = new NutritivityApp();
        load();
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        initialize(primaryStage);

    }

    public void initialize(Stage primaryStage) {
        initializeButtons();
        initializeScenes();
        initializeMainWindow(primaryStage);
    }

    public void initializeMainWindow(Stage primaryStage) {

        // setting window icon, title, border, close functionality
        window = primaryStage;
        window.setTitle("Nutritivity");
        window.getIcons().add(new Image(Main.class.getResourceAsStream("/NutritivityLogo.png")));
        window.setOnCloseRequest(e -> {
            save();
        });

        // choosing which scene to start window on
        window.setScene(dashboard);
        window.show();
    }

    public void closeProgram() {
        boolean close = ConfirmBox.display("Confirm Quit", "Are you sure you want to quit?", 300, 150);
        if (close) {
            save();
            window.close();
        }
    }

    public void initializeScenes() {

        // Scene 1 - uses vertical layout
        VBox dashboardLayout = new VBox(40);
        Label welcomeLabel = new Label("Welcome to Nutritivity!");
        welcomeLabel.setFont(new Font("Arial", 40));
        welcomeLabel.setTextFill(Color.LIGHTSEAGREEN);
        dashboardLayout.getChildren().addAll(welcomeLabel, openMenuButton, closeButton);
        dashboardLayout.setAlignment(Pos.BASELINE_CENTER);
        dashboardLayout.setBackground(new
                Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        dashboard = new Scene(dashboardLayout, 500, 300);


        // Scene 2 - uses horizontal layout
        HBox menuLayout = new HBox(15);
        menuLayout.getChildren().addAll(openDashboardButton, logMealButton, viewLogButton,
                createFoodButton, viewFoodButton);
        menuLayout.setAlignment(Pos.CENTER);
        menuLayout.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        menu = new Scene(menuLayout, 700, 150);
    }

    public static void setButtonNames() {

        // setting buttons for all future scenes
        openMenuButton = new Button("Open Menu");
        openDashboardButton = new Button("Return to Dashboard");
        logMealButton = new Button("Log Meal");
        viewLogButton = new Button("View Log");
        viewFoodButton = new Button("View Food Info");
        createFoodButton = new Button("Create a new Food");
        closeButton = new Button("Save & Exit");
    }

    public void initializeButtons() {

        setButtonNames();

        closeButton.setFont(new Font("Arial", 20));
        closeButton.setTextFill(Color.SEAGREEN);
        closeButton.setOnAction(e -> closeProgram());

        openMenuButton.setFont(new Font("Arial", 20));
        openMenuButton.setTextFill(Color.SEAGREEN);
        openMenuButton.setOnAction(e -> window.setScene(menu));

        openDashboardButton.setTextFill(Color.SEAGREEN);
        openDashboardButton.setFont(new Font("Arial", 20));
        openDashboardButton.setOnAction(e -> window.setScene(dashboard));

        setViewLogButton();
        setLogMealButton();
        setViewFoodButton();
        setCreateFoodButton();

    }

    private void setCreateFoodButton() {
        createFoodButton.setOnAction(e -> {
            CreateFoodUI.display(database);
        });
    }

    public static void setLogMealButton() {
        logMealButton.setOnAction(e -> {
                    boolean retry = false;
                    do {
                        String databaseString = "Current available foods: " + database.toString();
                        String foodName = EnterTextBoxWithFooter.display("Log Meal", "Enter Food Name",
                                databaseString);
                        try {
                            Food foodToLog = database.getFood(foodName);
                            LogMealUI.display(foodToLog, log, date);
                            retry = false;
                        } catch (FoodNotFoundException ex) {
                            retry = ConfirmBox.display("error", "Food not found, would you like to try again?");
                        }
                    } while (retry);
                }
        );
    }

    public static void setViewLogButton() {
        viewLogButton.setOnAction(e -> {
            System.out.println(log);
        });
    }

    public static void setViewFoodButton() {
        viewFoodButton.setOnAction(e -> {
            boolean retry = false;
            do {
                String databaseString = "Current available foods: " + database.toString();
                String foodName = EnterTextBoxWithFooter.display("View Food", "Enter Food Name",
                        databaseString);
                try {
                    Food foodToView = database.getFood(foodName);
                    ViewFoodUI.displayFoodInfo(foodToView);
                    retry = false;
                } catch (FoodNotFoundException ex) {
                    retry = ConfirmBox.display("error", "Food not found, would you like to try again?");
                }
            } while (retry);
        }
        );
    }

    private static void load() {
        try {
            log = MealReader.readMeals(new File(MEALS_FILE));
            date = log.getLast().getDay();
        } catch (IOException e) {
            System.out.println("no meal save file found; starting from scratch");
            log = new MealList();
        }
        try {
            database = FoodReader.readFoods(new File(FOODS_FILE));
        } catch (IOException e) {
            System.out.println("no food save file found; starting from scratch");
            database = new FoodList();
        }
    }

    private static void save() {
        saveFoods();
        saveMeals();
    }

    private static void saveFoods() {
        try {
            Writer writer = new Writer(new File(FOODS_FILE));
            writer.write(database);
            writer.close();
            System.out.println("Food database saved to file " + FOODS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save foods to " + FOODS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: saves log
    private static void saveMeals() {
        try {
            Writer writer = new Writer(new File(MEALS_FILE));
            writer.write(log);
            writer.close();
            System.out.println("Log saved to file " + MEALS_FILE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save foods to " + MEALS_FILE);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }
}

