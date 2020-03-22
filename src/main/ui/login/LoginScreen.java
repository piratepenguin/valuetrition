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
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;
import model.food.FoodList;
import model.meal.MealList;
import persistence.writers.Writer;
import ui.Main;
import ui.boxes.AlertBox;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

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

    public static final File accountsFile = new File("./data/accounts/accountDatabase.txt");
    static AccountList accountList;

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
        usernameField.setFont(new Font("Arial", 15));
        usernameField.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(7),
                Insets.EMPTY)));
        passwordField = new TextField();
        passwordField.setPromptText("Type here");
        passwordField.setFont(new Font("Arial", 15));
        passwordField.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(7),
                Insets.EMPTY)));
    }

    public void initLabels() {

        usernameLabel = new Label("username: ");
        usernameLabel.setFont(new Font("AcmeFont", 18));
        passwordLabel = new Label("password: ");
        passwordLabel.setFont(new Font("AcmeFont", 18));
//        passwordLabel.setTextFill(Color.TURQUOISE);
        nutritivityImageView = new ImageView("File:./data/NutritivityLogo.png");
        nutritivityIcon = new Image("File:./data/NutritivityLogo.png");
    }

    public void initButtons() {
        // create buttons
        initLoginButton();
        initSignUpButton();
    }

    private void initSignUpButton() {
        signUpButton = new Button("sign up");
        signUpButton.setFont(new Font("Arial", 18));
        signUpButton.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5),
                Insets.EMPTY)));
        signUpButton.setOnAction(e -> {
            username = usernameField.getText();
            password = passwordField.getText();
            signUp(username, password);
        });
    }

    public void initLoginButton() {
        loginButton = new Button("log in");
        loginButton.setFont(new Font("Arial", 18));
        loginButton.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, new CornerRadii(5),
                Insets.EMPTY)));
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
                        300, 120);
            }
        });
    }

    public void initWindow(String title) {

        window = new Stage();
        window.setOnCloseRequest(e -> System.exit(1));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(640);
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
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setConstraints(signUpButton, 4, 3);
        GridPane.setHalignment(signUpButton, HPos.CENTER);
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
            saveAccounts();
        } else {
            AlertBox.display("Username Taken", "Could not create account, username already taken",
                    400, 120);
        }
    }

    private void saveAccounts() {
//        try {
//            Writer writer = new Writer(accountsFile);
//            writer.write(accountList);
//            writer.close();
//            System.out.println("Account database saved to file " + accountsFile.getPath());
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save account database to " + accountsFile.getPath());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            // this is due to a programming error
//        }
        Main.saveAccounts();
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
