package model.accounts;

import model.food.Food;
import model.food.FoodList;
import model.meal.Meal;
import model.meal.MealList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Account {

    String username;
    String password;


    FoodList foodDatabase;
    MealList log;

    File  foodsFile;
    File  mealsFile;
    String foodsFileDir;
    String  mealsFileDir;

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
        foodsFile = new File(".data/accounts/" + username + "/foods.txt");
        mealsFile = new File(".data/accounts/" + username + "/meals.txt");
        boolean success = foodsFile.mkdir();
        if (success) {
            try {
                boolean a = foodsFile.createNewFile();
                boolean b = mealsFile.createNewFile();
                success = a & b;
                System.out.println(success);
            } catch (IOException ignored) {
                System.out.println("directory " + username + " created, unable to create files");
            }
        } else {
            System.out.println("couldn't make directory for " + username);
        }
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

