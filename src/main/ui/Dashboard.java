package ui;

import model.food.FoodList;
import model.meal.MealList;

public class Dashboard {

    static FoodList database;
    static MealList log;


    public static void display(FoodList foodList, MealList mealList) {
        database = foodList;
        log = mealList;
    }
}
