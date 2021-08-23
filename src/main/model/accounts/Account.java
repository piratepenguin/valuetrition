package model.accounts;


import persistence.Saveable;
import persistence.readers.AccountReader;

import java.io.File;
import java.io.PrintWriter;

// Represents a user account
public class Account implements Saveable {

    public static final String ACCOUNT_DIRECTORY = "./data/accounts/";
    public static final String FOODS_FILE_NAME = "/foods.txt";
    public static final String MEALS_FILE_NAME = "/meals.txt";

    String username;
    String password;
    String currentDate;

    File  foodsFile;
    File  mealsFile;
    File  directoryFile;
    String foodsFileDir;
    String mealsFileDir;


    public Account(String username, String password) {

        this.username = username;
        this.password = password;
        foodsFileDir = ACCOUNT_DIRECTORY + username + FOODS_FILE_NAME;
        mealsFileDir = ACCOUNT_DIRECTORY + username + MEALS_FILE_NAME;
        createFiles();
    }

    public Account(String username, String password, String date) {

        this.username = username;
        this.password = password;
        this.currentDate = date;
        foodsFileDir = ACCOUNT_DIRECTORY + username + FOODS_FILE_NAME;
        mealsFileDir = ACCOUNT_DIRECTORY + username + MEALS_FILE_NAME;
        createFiles();
    }

    public void createFiles() {
        directoryFile = new File(ACCOUNT_DIRECTORY + username);
        foodsFile = new File(foodsFileDir);
        mealsFile = new File(mealsFileDir);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public File getFoodsFile() {
        return foodsFile;
    }

    public File getDirectoryFile() {
        return directoryFile;
    }

    public String getFoodsFileDir() {
        return foodsFileDir;
    }

    public File getMealsFile() {
        return mealsFile;
    }

    public String getMealsFileDir() {
        return mealsFileDir;
    }

    public String getDate() {
        return currentDate;
    }

    @Override
    public void save(PrintWriter printWriter) {

        printWriter.print(username);
        printWriter.print(AccountReader.DELIMITER);
        printWriter.print(password);
        printWriter.print(AccountReader.DELIMITER);
        printWriter.println(currentDate);
    }
}

