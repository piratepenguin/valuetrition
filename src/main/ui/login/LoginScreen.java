package ui.login;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.accounts.Account;
import model.accounts.AccountList;
import model.exceptions.AccountNotFoundException;
import model.exceptions.IncorrectPasswordException;
import persistence.readers.AccountReader;
import ui.Main;
import ui.boxes.AlertBox;
import ui.boxes.ConfirmBox;


import java.io.File;
import java.io.IOException;

public class LoginScreen {

    String username;
    String password;
    final String savedUserFilePath = Main.MOST_RECENT_USER_FILE_STRING;

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

    // basic constructor
    public LoginScreen() {
        username = "";
        password = "";
    }


    /**
     * attempts to load last logged in user profile, asks confirmation
     * otherwise displays the GUI and prompts user for username and password
     * @return the account signed in to
     */
    public Account display() {

        try {
            return checkSavedUser();
        } catch (IOException ignored) {
            initTextBoxes();
            initLabels();
            initButtons();
            initScene();
            initWindow("Nutritivity - Log In");
        }

        return account;
    }

    /**
     * @return the account of the most recently logged in user, if found
     * @throws IOException if reading from file fails for any reason
     */
    public Account checkSavedUser() throws IOException {
        File savedUserFile = new File(savedUserFilePath);
        Account tempAccount = AccountReader.readMostRecentAccount(savedUserFile);
        try {
            Account account = attemptLogin(tempAccount.getUsername(), tempAccount.getPassword());
            ConfirmBox useSavedAccount = new ConfirmBox();
            if (useSavedAccount.display("Confirm Load Saved Account", "We have found a saved account for:\n\n"
                    + "                    " + account.getUsername() + "\n\nwould you like to login with it?", 500,250)) {
                return account;
            }
        } catch (AccountNotFoundException ignored) {
            AlertBox.display("Error", "Saved user info found, but username is invalid",
                    600, 150);
        } catch (IncorrectPasswordException ignored) {
            AlertBox.display("Error", "Saved user info found, but password is invalid",
                    600, 150);
        }
        throw new IOException();    // IOException here just to signal failure to our caller
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
        usernameLabel.getStyleClass().add("label");
        usernameLabel.setFont(new Font("SansSerif", 22));
        passwordLabel = new Label("password: ");
        usernameLabel.getStyleClass().add("label");
        passwordLabel.setFont(new Font("SansSerif", 22));
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
            try {
                account = attemptLogin(username, password);
                window.close();
            } catch (AccountNotFoundException ignored) {
                AlertBox.display("Error", "This account does not exist",
                        600, 150);
            } catch (IncorrectPasswordException ignored) {
                AlertBox.display("Error", "Account exists, but password is incorrect",
                        600, 150);
            }
        });
    }

    public void initWindow(String title) {

        window = new Stage();
        window.setOnCloseRequest(e -> System.exit(1));
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(1000);
        window.setHeight(800);
        window.getIcons().add(nutritivityIcon);
        window.setScene(scene);
        window.showAndWait();
    }

    /**
     * scene consists of a gridPane (grid) nested into the center of another gridPane (mainGrid)
     * this way, we add columns and rows around in mainGrid which are around grid, and tell them to resize
     * result: grid (ie. all content) stays fixed size, borders grow evenly as window is resized
     */
    public void initScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);
        grid.getChildren().addAll(usernameLabel, passwordLabel, usernameField, passwordField, signUpButton, loginButton);

        GridPane mainGrid = new GridPane();
//        mainGrid.setGridLinesVisible(true);   // for debugging

        /* setting column constraints */
        ColumnConstraints column0 = new ColumnConstraints(0,0,1000);
        mainGrid.getColumnConstraints().add(0, column0);
        column0.setHgrow(Priority.ALWAYS);

        ColumnConstraints column1 = new ColumnConstraints(600,700,700);
        mainGrid.getColumnConstraints().add(1, column1);

        ColumnConstraints column2 = new ColumnConstraints(0,0,1000);
        mainGrid.getColumnConstraints().add(2, column2);
        column2.setHgrow(Priority.ALWAYS);

        /* setting row constraints */
        RowConstraints row0 = new RowConstraints(0,50,800);
        mainGrid.getRowConstraints().add(0, row0);
        row0.setVgrow(Priority.ALWAYS);

        RowConstraints row1 = new RowConstraints(300,300,300);
        mainGrid.getRowConstraints().add(1, row1);

        RowConstraints row2 = new RowConstraints(100,100,100);
        mainGrid.getRowConstraints().add(2, row2);


        RowConstraints row3 = new RowConstraints(0,200,800);
        mainGrid.getRowConstraints().add(3, row3);
        row3.setVgrow(Priority.ALWAYS);


        /* init grid constraints of all the content, set up css*/
        initGridConstraints();
        GridPane.setConstraints(grid, 1, 2);
        GridPane.setConstraints(nutritivityImageView, 1, 1);
        GridPane.setHalignment(nutritivityImageView, HPos.CENTER);
        mainGrid.getChildren().addAll(grid, nutritivityImageView);

        // set style
        setStyle(mainGrid);



    }

    public void setStyle(GridPane grid) {
        grid.setStyle("-fx-background: linear-gradient(to bottom, #a3f1c4, #6df1fa, #2980B9);");
        scene = new Scene(grid);
        scene.getStylesheets().add("./css/login.css");

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

    public Account attemptLogin(String username, String password) throws IncorrectPasswordException, AccountNotFoundException {

        if (AccountList.getAccount(username).getPassword().equals(password)) {
            return AccountList.getAccount(username);
        } else {
            throw new IncorrectPasswordException();
        }
    }

    public void signUp(String username, String password) {
        account = new Account(username, password, "SEP012021");
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