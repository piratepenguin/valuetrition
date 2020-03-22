package ui;

import javafx.application.Application;
import javafx.geometry.HPos;
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
import persistence.readers.AccountReader;
import persistence.readers.FoodReader;
import persistence.readers.LogReader;
import persistence.writers.Writer;
import ui.boxes.*;
import ui.food.CreateFoodUI;
import ui.food.ViewFoodUI;
import ui.login.LoginScreen;
import ui.meal.LogMealUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class Main extends Application {

    Stage window;
    Scene dashboard;
    Scene menu;

    Button viewLogForTodayButton;
    Button viewLogButton;
    Button openMenuButton;
    Button openDashboardButton;
    Button logMealButton;
    Button closeButton;
    Button viewFoodButton;
    Button createFoodButton;
    Button nextDayButton;

    Label welcomeLabel;
    Label dayLabel;

    Account account;


    private static final String ACCOUNTS_FILE = "./data/accounts/accountDatabase.txt";
    public File mealsFile;
    public File foodsFile;
    public static final File accountsFile = new File("./data/accounts/accountDatabase.txt");
    static AccountList accountList;
    FoodList database;
    Log log;
    int date = 1;


    public static void main(String[] args) {
        loadAccountList();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        LoginScreen loginScreen = new LoginScreen();
        account = loginScreen.display();
        foodsFile = account.getFoodsFile();
        mealsFile = account.getMealsFile();
        load();
        initialize(primaryStage);

    }

    public void initialize(Stage primaryStage) {

        initLabels();
        initializeButtons();
        initializeGridConstraints();
        initializeScenes();
        initializeMainWindow(primaryStage);
    }


    //   window

    public void initializeMainWindow(Stage primaryStage) {

        // setting window icon, title, border, close functionality
        window = primaryStage;
        window.setTitle("Nutritivity");
        window.getIcons().add(new Image(Main.class.getResourceAsStream("/NutritivityLogo.png")));
        window.setOnCloseRequest(e -> save());

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

    public void initLabels() {
        welcomeLabel = new Label("Welcome to Nutritivity!");
        welcomeLabel.setFont(new Font("Arial", 40));
        welcomeLabel.setTextFill(Color.LIGHTSEAGREEN);

        dayLabel = new Label("day: " + date);
        dayLabel.setFont(new Font("Arial", 40));
        dayLabel.setTextFill(Color.LIGHTSEAGREEN);
    }


    //   buttons

    public void setButtonNames() {

        // setting buttons for all future scenes
        openMenuButton = new Button("Open Menu");
        openDashboardButton = new Button("Return to Dashboard");
        nextDayButton = new Button("Next Day");
        logMealButton = new Button("Log Meal");
        viewLogForTodayButton = new Button("View Log For Today");
        viewLogButton = new Button("View Log For Another Day");
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
        openDashboardButton.setFont(new Font("Arial", 30));
        openDashboardButton.setOnAction(e -> window.setScene(dashboard));

        setViewLogButton();
        setViewLogForTodayButton();
        setLogMealButton();
        setViewFoodButton();
        setCreateFoodButton();
        setNextDayButton();

    }

    public void setCreateFoodButton() {
        createFoodButton.setFont(new Font("Arial", 20));
        createFoodButton.setTextFill(Color.SEAGREEN);
        createFoodButton.setOnAction(e -> {
            CreateFoodUI createFoodUI = new CreateFoodUI();
            createFoodUI.display(database);
        });
    }

    public void setLogMealButton() {

        logMealButton.setFont(new Font("Arial", 20));
        logMealButton.setTextFill(Color.SEAGREEN);
        logMealButton.setOnAction(e -> {
            boolean retry;
            do {
                String databaseString;
                if (database.toString().equals("")) {
                    databaseString = "Food database empty, please create some foods first";
                } else {
                    databaseString = "Current available foods: " + database.toString();
                }
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
        });
    }

    public void setViewLogForTodayButton() {
        viewLogForTodayButton.setFont(new Font("Arial", 20));
        viewLogForTodayButton.setTextFill(Color.SEAGREEN);
        viewLogForTodayButton.setOnAction(e -> AlertBox.display("View Log for Today", log.getLogForDayAsString(date),
                    600, 200));
    }

    public void setViewLogButton() {
        viewLogButton.setFont(new Font("Arial", 20));
        viewLogButton.setTextFill(Color.SEAGREEN);
        viewLogButton.setOnAction(e -> {
            int day = Integer.parseInt(EnterTextBox.display("View Log for Day ...",
                    "View log for day: ", 500, 250));
            AlertBox.display("View Log for Today", log.getLogForDayAsString(day),
                    600, 200);
        });
    }

    public void setNextDayButton() {
        nextDayButton.setFont(new Font("Arial", 20));
        nextDayButton.setTextFill(Color.SEAGREEN);
        nextDayButton.setOnAction(e -> {
            date++;
            dayLabel.setText("day: " + date);
        });
    }

    public void setViewFoodButton() {
        viewFoodButton.setFont(new Font("Arial", 20));
        viewFoodButton.setTextFill(Color.SEAGREEN);
        viewFoodButton.setOnAction(e -> {
            boolean retry;
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


    //   scene

    public void initializeGridConstraints() {

        GridPane.setConstraints(welcomeLabel, 3,0);
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);
        GridPane.setConstraints(openMenuButton, 3,3);
        GridPane.setHalignment(openMenuButton, HPos.CENTER);
        GridPane.setConstraints(nextDayButton, 3,5);
        GridPane.setHalignment(nextDayButton, HPos.CENTER);
        GridPane.setConstraints(dayLabel, 3,1);
        GridPane.setHalignment(dayLabel, HPos.CENTER);
        GridPane.setConstraints(closeButton, 3,7);
        GridPane.setHalignment(closeButton, HPos.CENTER);


        GridPane.setConstraints(openDashboardButton, 2,1);
        GridPane.setHalignment(openDashboardButton, HPos.CENTER);
        GridPane.setConstraints(logMealButton, 3,2);
        GridPane.setHalignment(logMealButton, HPos.CENTER);
        GridPane.setConstraints(viewLogButton, 2,2);
        GridPane.setHalignment(viewLogButton, HPos.CENTER);
        GridPane.setConstraints(viewLogForTodayButton, 2,4);
        GridPane.setHalignment(viewLogForTodayButton, HPos.CENTER);
        GridPane.setConstraints(viewFoodButton, 1,2);
        GridPane.setHalignment(viewFoodButton, HPos.CENTER);
        GridPane.setConstraints(createFoodButton, 1,4);
        GridPane.setHalignment(createFoodButton, HPos.CENTER);
    }

    public void initializeScenes() {

        // Scene 1 for dashboard
        GridPane dashboardGrid = new GridPane();
        dashboardGrid.setPadding(new Insets(10,10,10,10));
        dashboardGrid.setVgap(8);
        dashboardGrid.setHgap(10);
        dashboardGrid.getChildren().addAll(welcomeLabel, openMenuButton, nextDayButton, closeButton, dayLabel);
        dashboardGrid.setBackground(new
                Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        dashboard = new Scene(dashboardGrid, 500, 400);


        // Scene 2 for menu
        GridPane menuGrid = new GridPane();
        menuGrid.setPadding(new Insets(10,10,10,10));
        menuGrid.setVgap(50);
        menuGrid.setHgap(15);
        menuGrid.getChildren().addAll(openDashboardButton, viewFoodButton, createFoodButton, viewLogForTodayButton,
                viewLogButton, logMealButton);
        menuGrid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        menu = new Scene(menuGrid, 800, 500);
    }



    //    load & save

    private void load() {
        try {
            log = LogReader.readLog(mealsFile);
            date = log.getLastDay();
        } catch (IOException e) {
            System.out.println("no meal save file found; starting from scratch");
            log = new Log();
            date = 1;
        }
        try {
            database = FoodReader.readFoods(foodsFile);
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
        saveAccounts();
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

    public static void saveAccounts() {
        try {
            Writer writer = new Writer(accountsFile);
            writer.write(accountList);
            writer.close();
            System.out.println("Account database saved to file " + accountsFile.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save account database to " + accountsFile.getPath());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

}

