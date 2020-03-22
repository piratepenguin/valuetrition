package ui.login;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;
import model.food.FoodList;
import model.meal.MealList;
import ui.boxes.AlertBox;

import java.io.File;
import java.io.IOException;

public class LoginScreen {

    String username;
    String password;

    Account account;

    TextField usernameField;
    TextField passwordField;

    Label usernameLabel;
    Label passwordLabel;

    Button loginButton;
    Button signUpButton;

    ImageView nutritivityImageView;
    Image nutritivityIcon;

    Scene scene;
    Stage window;

    FoodList foodList;
    MealList mealList;

    public LoginScreen() {
        username = "";
        password = "";
    }


    public Account display() {

        initTextBoxes();
        initLabels();
        initButtons();
        initScene();
        initWindow("Nutritivity - Log In");

        return account;
    }

    public void initTextBoxes() {

        // create text box for user to input a string
        usernameField = new TextField();
        usernameField.setPromptText("Type here");
        passwordField = new TextField();
        passwordField.setPromptText("Type here");
    }

    public void initLabels() {

        usernameLabel = new Label("username: ");
        passwordLabel = new Label("password: ");
        nutritivityImageView = new ImageView("File:./data/NutritivityLogo.png");
        nutritivityIcon = new Image("File:./data/NutritivityLogo.png");
    }

    public void initButtons() {
        // create buttons
        loginButton = new Button("log in");
        loginButton.setOnAction(e -> {
            username = usernameField.getText();
            password = passwordField.getText();
            if (verify(username, password)) {
                try {
                    account = AccountList.getAccount(username);
                } catch (AccountNotFoundException ignored) {
                    AlertBox.display("Error", "Account seems to exist but is inaccessible/corrupted",
                            600, 150);
                    // this should NEVER happen b/c wrapped in verify 'if' statement
                }
                window.close();
            } else {
                AlertBox.display("Invalid Entry", "Invalid username/password combination",
                        300, 100);
            }
        });

        signUpButton = new Button("sign up");
        signUpButton.setOnAction(e -> {
            username = usernameField.getText();
            password = passwordField.getText();
            signUp(username, password);
        });
    }

    public void initWindow(String title) {

        window = new Stage();
        window.setOnCloseRequest(e -> System.exit(1));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setHeight(400);
        window.getIcons().add(nutritivityIcon);
        window.setScene(scene);
        window.showAndWait();
    }

    public void initScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();
        grid.getChildren().addAll(usernameLabel, passwordLabel, usernameField, passwordField, signUpButton,loginButton);

        GridPane mainGrid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        GridPane.setConstraints(grid, 0,1);
        GridPane.setConstraints(nutritivityImageView, 0,0);
        GridPane.setHalignment(nutritivityImageView, HPos.CENTER);
        mainGrid.getChildren().addAll(grid, nutritivityImageView);
        mainGrid.setBackground(new Background(new BackgroundFill(Color.PALEGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        scene = new Scene(mainGrid);

    }

    public void initGridConstraints() {
        GridPane.setConstraints(usernameLabel, 1, 1);
        GridPane.setConstraints(passwordLabel, 3, 1);
        GridPane.setConstraints(usernameField, 2, 1);
        GridPane.setConstraints(passwordField, 4, 1);
        GridPane.setConstraints(loginButton, 2, 3);
        GridPane.setConstraints(signUpButton, 4, 3);
    }

    public boolean verify(String username, String password) {
        try {
            return (AccountList.contains(username)
                    && AccountList.getAccount(username).getPassword().equals(password));
        } catch (AccountNotFoundException ignored) {
            return false;
        }
    }

    public void signUp(String username, String password) {
        account = new Account(username, password);
        boolean success = AccountList.add(username, account);
        if (success) {
            AlertBox.display("success!", "successfully created account for " + username,
                    400, 140);
            createFiles();
            AccountList.add(username,account);
        } else {
            AlertBox.display("Username Taken", "Could not create account, username already taken",
                    400, 120);
        }
    }

    public void createFiles() {
        File directoryFile = new File("./data/accounts/" + username);
        File foodsFile = new File(account.getFoodsFileDir());
        File mealsFile = new File(account.getMealsFileDir());
        boolean success = directoryFile.mkdir();
        if (success) {
            try {
                boolean a = foodsFile.createNewFile();
                boolean b = mealsFile.createNewFile();
                success = a & b;
                if (success) {
                    System.out.println("created food and meal files");
                } else {
                    System.out.println("unable to create food and meal files");
                }
            } catch (IOException ignored) {
                System.out.println("directory " + username + " created, unable to create files");
            }
        } else {
            System.out.println("couldn't make directory for " + username);
        }
    }

}
