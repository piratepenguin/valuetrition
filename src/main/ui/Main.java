package ui;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
import java.util.ArrayList;
import model.calendar.Date;
import java.util.List;

public class Main extends Application {
    //

    boolean retry;
    Date date2;
    int date = 1;

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
    Button prevDayButton;
    List<Button> buttons;

    Label welcomeLabel;
    Label dayLabel;

    public static final String ACCOUNTS_FILE_STRING = "./data/accounts/accountDatabase.txt";
    public static final File ACCOUNTS_FILE = new File(ACCOUNTS_FILE_STRING);
    public static final String MOST_RECENT_USER_FILE_STRING = "./data/accounts/mostRecentUser.txt";
    public static final File MOST_RECENT_USER_FILE = new File(MOST_RECENT_USER_FILE_STRING);
    File mealsFile;
    File foodsFile;

    Account account;

    static AccountList accountList;
    FoodList database;
    Log log;

    /**
     * first loads list of accounts in database,
     * then launches login screen and application
     */
    public static void main(String[] args) {
        Date.setDate(1, "JAN", 2021);
        loadAccountList();
        launch(args);
//        System.out.println(Font.getFamilies());
//        System.out.println(Font.getFontNames());
    }

    // load user data and launch GUI
    @Override
    public void start(Stage primaryStage) {
        LoginScreen loginScreen = new LoginScreen();
        account = loginScreen.display();
        foodsFile = account.getFoodsFile();
        mealsFile = account.getMealsFile();
        load();
        initialize(primaryStage);

    }

    // initialize all utilities
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
        ConfirmBox confirmBox = new ConfirmBox();
        boolean close = confirmBox.display("Confirm Quit", "Are you sure you want to quit?", 300,
                150);
        if (close) {
            save();
            window.close();
        }
    }

    public void initLabels() {
        welcomeLabel = new Label("Welcome to Nutritivity!");
        welcomeLabel.setFont(new Font("Arial", 40));
        welcomeLabel.setPadding(new Insets(30,0,10,0));
        welcomeLabel.setTextFill(Color.LIGHTSEAGREEN);

        dayLabel = new Label(Date.viewAsString());
        dayLabel.setFont(new Font("Arial", 40));
        dayLabel.setTextFill(Color.LIGHTSEAGREEN);
    }


    //   buttons

    public void setButtonNames() {

        // setting buttons for all future scenes
        openMenuButton = new Button("Open Menu");
        openDashboardButton = new Button("Return to Dashboard");
        prevDayButton = new Button();
        Image prevDayImg = new Image("/wedgeLeft.png");
        ImageView wedgeLeft = new ImageView(prevDayImg);
        prevDayButton.setGraphic(wedgeLeft);
        prevDayButton.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE,CornerRadii.EMPTY, Insets.EMPTY)));
        nextDayButton = new Button();
        Image nextDayImg = new Image("/wedgeRight.png");
        ImageView wedgeRightview = new ImageView(nextDayImg);
        nextDayButton.setGraphic(wedgeRightview);
        nextDayButton.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE,CornerRadii.EMPTY, Insets.EMPTY)));
        logMealButton = new Button("Log Meal");
        viewLogForTodayButton = new Button("View Log For Today");
        viewLogButton = new Button("View Log For Another Day");
        viewFoodButton = new Button("View Food Info");
        createFoodButton = new Button("Create a new Food");
        closeButton = new Button("Save & Exit");
    }


    public void setDefaultButtonFont(Button b) {
        b.setFont(new Font("Arial", 20));
        b.setTextFill(Color.SEAGREEN);
    }

    public void initializeButtons() {

        setButtonNames();
        setDefaultButtonFont(closeButton);
        closeButton.setOnAction(e -> closeProgram());

        setDefaultButtonFont(openMenuButton);
        openMenuButton.setOnAction(e -> {
//            window.setScene(menu);
            logMealButton.setVisible(false);
        });

        openDashboardButton.setTextFill(Color.SEAGREEN);
        openDashboardButton.setFont(new Font("Arial", 30));
        openDashboardButton.setOnAction(e -> window.setScene(dashboard));

        setViewLogButton();
        setViewLogForTodayButton();

        setLogMealButton();
        setDefaultButtonFont(logMealButton);

        setViewFoodButton();
        setCreateFoodButton();
        setPrevDayButton();
        setNextDayButton();

        buttons = new ArrayList<>();
//        buttons.add(logMealButton, viewLogButton, createFoodButton, viewLogForTodayButton, viewFoodButton);

    }

    public void setCreateFoodButton() {
        setDefaultButtonFont(createFoodButton);
        createFoodButton.setOnAction(e -> {
            CreateFoodUI createFoodUI = new CreateFoodUI();
            Food newFood = createFoodUI.display();
            if (null != newFood) {
                database.add(newFood);
            }
        });
    }

    public void setLogMealButton() {

        logMealButton.setOnAction(e -> {
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
                    if (foodName != null) {
                        Food foodToLog = database.getFood(foodName);
                        LogMealUI logMealUI = new LogMealUI();
                        logMealUI.display(foodToLog, log, Date.encode());
                        retry = false;
                    }
                } catch (FoodNotFoundException ex) {
                    ConfirmBox confirmBox = new ConfirmBox();
                    retry = confirmBox.display("error", "Food not found, would you like to try again?");
                }
            } while (retry);
        });
    }


    public void setViewLogForTodayButton() {
        setDefaultButtonFont(viewLogForTodayButton);
        viewLogForTodayButton.setOnAction(e -> AlertBox.display("View Log for Today", log.getLogForDayAsString(Date.encode()),
                600, 200));
    }

    public void setViewLogButton() {
        setDefaultButtonFont(viewLogButton);
        viewLogButton.setOnAction(e -> {
            String dayString = EnterTextBox.display("View Log for Day ...",
                    "View log for day: ", 500, 250);
            if (dayString != null) {
                AlertBox.display("View Log for Today", log.getLogForDayAsString(dayString),
                        600, 200);
            }
        });
    }

    public void setPrevDayButton() {
        setDefaultButtonFont(prevDayButton);
        prevDayButton.setOnAction(e -> {
            Date.previous();
            dayLabel.setText(Date.viewAsString());
        });
    }

    public void setNextDayButton() {
        setDefaultButtonFont(nextDayButton);
        nextDayButton.setOnAction(e -> {
            Date.next();
            dayLabel.setText(Date.viewAsString());
        });
    }


    public void setViewFoodButton() {
        setDefaultButtonFont(viewFoodButton);
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
                            ConfirmBox confirmBox = new ConfirmBox();
                            retry = confirmBox.display("error", "Food not found, would you like to try again?");
                        }
                    } while (retry);
                }
        );
    }


    //   scene

    public void initializeGridConstraints() {

        GridPane.setConstraints(welcomeLabel, 3,0);
        GridPane.setHalignment(welcomeLabel, HPos.CENTER);
        GridPane.setConstraints(dayLabel, 3,1);
        GridPane.setHalignment(dayLabel, HPos.CENTER);
        GridPane.setConstraints(nextDayButton, 4,1);
        GridPane.setHalignment(nextDayButton, HPos.CENTER);
        GridPane.setConstraints(prevDayButton, 2,1);
        GridPane.setHalignment(prevDayButton, HPos.CENTER);
        GridPane.setConstraints(openMenuButton, 3,3);
        GridPane.setHalignment(openMenuButton, HPos.CENTER);

        // useful ones
        GridPane.setConstraints(logMealButton, 3,4);
        GridPane.setHalignment(logMealButton, HPos.CENTER);
        GridPane.setConstraints(viewLogButton, 3,5);
        GridPane.setHalignment(viewLogButton, HPos.CENTER);
        GridPane.setConstraints(viewLogForTodayButton, 3,6);
        GridPane.setHalignment(viewLogForTodayButton, HPos.CENTER);
        GridPane.setConstraints(viewFoodButton, 3,7);
        GridPane.setHalignment(viewFoodButton, HPos.CENTER);
        GridPane.setConstraints(createFoodButton, 3,8);
        GridPane.setHalignment(createFoodButton, HPos.CENTER);

        // save & exit
        GridPane.setConstraints(closeButton, 3,9);
        GridPane.setHalignment(closeButton, HPos.CENTER);

        // remove soon
        GridPane.setConstraints(openDashboardButton, 2,1);
        GridPane.setHalignment(openDashboardButton, HPos.CENTER);
    }

    public void initializeScenes() {

        // Scene 1 for dashboard
        GridPane dashboardGrid = new GridPane();
        dashboardGrid.setPadding(new Insets(10,10,10,10));
        dashboardGrid.setVgap(8);
        dashboardGrid.setHgap(10);

        // add all buttons to main screen
        dashboardGrid.getChildren().addAll(welcomeLabel, openMenuButton, prevDayButton, nextDayButton, closeButton, dayLabel);
        dashboardGrid.getChildren().addAll(viewFoodButton, createFoodButton, viewLogForTodayButton, viewLogButton, logMealButton);

        dashboardGrid.setBackground(new Background(new BackgroundFill(Color.PALETURQUOISE, CornerRadii.EMPTY, Insets.EMPTY)));
        setStyle(dashboardGrid);


        // Scene 2 for menu
//        GridPane menuGrid = new GridPane();
//        menuGrid.setPadding(new Insets(10,10,10,10));
//        menuGrid.setVgap(50);
//        menuGrid.setHgap(15);
////        menuGrid.getChildren().addAll(openDashboardButton, viewFoodButton, createFoodButton, viewLogForTodayButton,
////                viewLogButton, logMealButton);
//        menuGrid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
//
//        setStyle(menuGrid);
    }


    public void setStyle(GridPane grid) {
        grid.setStyle("-fx-background: linear-gradient(to bottom, #a3f1c4, #6df1fa, #2980B9);");
        dashboard = new Scene(grid, 600, 600);
        dashboard.getStylesheets().add("./css/login.css");

    }

    //    load & save

    private void load() {
        try {
            log = LogReader.readLog(mealsFile);
            Date.setDate(log.getLastDay());
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
            accountList = AccountReader.readAccounts(new File(ACCOUNTS_FILE_STRING));
        } catch (IOException ignored) {
            System.out.println("Neo you've broken the matrix");
        }
    }

    private void save() {
        saveFoods();
        saveMeals();
        saveAccounts();
        saveMostRecentAccount();
    }

    private void saveMostRecentAccount() {
        try {
            Writer writer = new Writer(MOST_RECENT_USER_FILE);
            writer.write(account);
            writer.close();
            System.out.println("Most recent user file saved to: " + MOST_RECENT_USER_FILE.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save most recent user file to " + MOST_RECENT_USER_FILE.getPath());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
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
            Writer writer = new Writer(ACCOUNTS_FILE);
            writer.write(accountList);
            writer.close();
            System.out.println("Account database saved to file " + ACCOUNTS_FILE.getPath());
        } catch (FileNotFoundException e) {
            System.out.println("Unable to save account database to " + ACCOUNTS_FILE.getPath());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            // this is due to a programming error
        }
    }

}

