package ui.login;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.AccountList;
import model.food.FoodList;
import model.meal.MealList;
import ui.Dashboard;
import ui.boxes.AlertBox;

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

    Stage window;

    FoodList foodList;
    MealList mealList;

    public LoginScreen() {
        username = "";
        password = "";
        window = new Stage();

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
    }

    public void initButtons() {
        // create buttons
        loginButton = new Button("log in");
        loginButton.setOnAction(e -> {
            username = usernameField.getText();
            password = passwordField.getText();
            if (verify(username, password)) {
                account = AccountList.getAccount(username);
                window.close();
            } else {
                AlertBox.display("Invalid username/password combination");
            }
        });

        signUpButton = new Button("sign up");
        signUpButton.setOnAction(e -> {
            signUp(username, password);
        });
    }

    public void initWindow(String title) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(500);
        window.setHeight(300);
    }

    public void initScene() {
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);
        initGridConstraints();
        grid.getChildren().addAll(usernameLabel, passwordLabel, usernameField, passwordField, signUpButton,loginButton);
        Scene scene = new Scene(grid);
        window.setScene(scene);
        window.showAndWait();
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
        return (AccountList.contains(username)
                && AccountList.getAccount(username).getPassword().equals(password));
    }

    public void signUp(String username, String password) {
        boolean success = AccountList.add(username, new Account(username, password));
        if (success) {
            AlertBox.display("successfully created account for " + username);
        } else {
            AlertBox.display("could not create account, username already taken");
        }
    }

}
