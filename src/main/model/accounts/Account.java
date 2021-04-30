package model.accounts;


import persistence.Saveable;

import java.io.File;
import java.io.PrintWriter;

// Represents a user account
public class Account implements Saveable {

    public static final String ACCOUNT_DIRECTORY = "./data/accounts/";
    public static final String FOODS_FILE_NAME = "/foods.txt";
    public static final String MEALS_FILE_NAME = "/meals.txt";

    String username;
    String password;

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

    @Override
    public void save(PrintWriter printWriter) {
        printWriter.println(username);
        printWriter.print(password);
    }
}

