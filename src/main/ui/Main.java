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
import javafx.stage.Stage;

import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.FoodNotFoundException;
import model.food.Food;
import model.food.FoodList;
import model.meal.Log;
import model.meal.MealList;
import persistence.readers.AccountReader;
import persistence.readers.FoodReader;
import persistence.readers.LogReader;
import persistence.writers.Writer;
import ui.boxes.ConfirmBox;
import ui.boxes.EnterTextBoxWithFooter;
import ui.food.CreateFoodUI;
import ui.food.ViewFoodUI;
import ui.login.LoginScreen;
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

    Account account;


    private static final String ACCOUNTS_FILE = "./data/accounts/accountDatabase.txt";
    public File mealsFile;
    public File foodsFile;
    static AccountList accountList;
    static FoodList database;
    static Log log;
    static int date = 1;


    public static void main(String[] args) throws IOException {

        loadAccountList();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        LoginScreen loginScreen = new LoginScreen();
        account = loginScreen.display();
        foodsFile = account.getFoodsFile();
        mealsFile = account.getMealsFile();
        load();
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
            CreateFoodUI createFoodUI = new CreateFoodUI();
            createFoodUI.display(database);
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
                            LogMealUI logMealUI = new LogMealUI();
                            logMealUI.display(foodToLog, log, date);
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
                    ViewFoodUI viewFoodUI = new ViewFoodUI();
                    viewFoodUI.displayFoodInfo(foodToView);
                    retry = false;
                } catch (FoodNotFoundException ex) {
                    retry = ConfirmBox.display("error", "Food not found, would you like to try again?");
                }
            } while (retry);
        }
        );
    }

    private void load() {
        try {
            log = LogReader.readLog(new File(".data/accounts/deniskov/meals.txt"));
        } catch (IOException e) {
            System.out.println("no meal save file found; starting from scratch");
            log = new Log();
        }
        try {
            database = FoodReader.readFoods(new File(".data/accounts/deniskov/foods.txt"));
        } catch (IOException e) {
            System.out.println("no food save file found; starting from scratch");
            database = new FoodList();
        }
    }

    private static void loadAccountList() {
        AccountList.newAccountList();
        try {
            accountList = AccountReader.readAccounts(new File(ACCOUNTS_FILE));
        } catch (IOException ignored) {
            System.out.println("Neo you've broken the matrix");
        }
    }

    private void save() {
        saveFoods();
        saveMeals();
    }

    private void saveFoods() {
        try {
            Writer writer = new Writer(foodsFile);
            writer.write(database);
            writer.close();
            System.out.println("Food database saved to file " + foodsFile.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save foods to " + foodsFile.getPath());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

    // EFFECTS: saves log
    private void saveMeals() {
        try {
            Writer writer = new Writer(mealsFile);
            writer.write(log);
            writer.close();
            System.out.println("Log saved to file " + mealsFile.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save log to " + mealsFile.getPath());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }
}

