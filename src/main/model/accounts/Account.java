package model.accounts;

import model.food.Food;
import model.food.FoodList;
import model.meal.Meal;
import model.meal.MealList;

import java.io.File;
import java.io.IOException;
import java.util.List;


// Represents a user account
public class Account {

    String username;
    String password;


    FoodList foodDatabase;
    MealList log;

    File  foodsFile;
    File  mealsFile;
    File  directoryFile;
    String foodsFileDir;
    String mealsFileDir;

    public Account(String username, String password) {

        this.username = username;
        this.password = password;
        foodDatabase = new FoodList();
        log = new MealList();
        foodsFileDir = "./data/accounts/" + username + "/foods.txt";
        foodsFile = new File(foodsFileDir);
        mealsFileDir = "./data/accounts/" + username + "/meals.txt";
        mealsFile = new File(mealsFileDir);
    }

    public void createFiles() {
        directoryFile = new File("./data/accounts/" + username);
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


}

