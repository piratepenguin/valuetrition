package model;

import model.Food;
import model.Meal;
import java.util.*;

// represents a list of Food items
public class MealList {
    private ArrayList<Meal> mealList;

    public MealList() {
        mealList = new ArrayList<>();
    }

    public int size() {
        return mealList.size();
    }

    public void clear() {
        mealList.clear();
    }

    // EFFECTS: removes meal from list
    public void removeFood(String foodname, int day) throws FoodNotFoundException {
        int size = mealList.size();
        mealList.removeIf(meal -> (meal.getName().equals(foodname) && meal.getDay() == day));
        if (mealList.size() == size) {
            throw new FoodNotFoundException();
        }
    }

    public void add(Meal meal) {
        mealList.add(meal);
    }

    // EFFECTS: returns food with given name from list,
    //          if not found returns FoodNotFoundException
    public Meal getMeal(String name, int day) throws FoodNotFoundException {
        for (Meal meal: mealList) {
            if (meal.getName().equals(name) && meal.getDay() == day) {
                return meal;
            }
        }
        throw new FoodNotFoundException();

    }

    // EFFECTS: returns a string of all the foods contained in the list
    public String toString() {
        StringBuilder list = new StringBuilder();
        int currentDay = 1;
        for (Meal each : mealList) {
            if (mealList.indexOf(each) == mealList.size() - 1) {
                if (each.getDay() != currentDay) {
                    list.append("| Day ").append(each.getDay()).append("| ");
                }
                list.append(each.getName()).append(" - ");
                list.append(each.getCalories()).append("Cal");
            } else {
                list.append(each.getName()).append(" - ");
                list.append(each.getCalories()).append("Cal, ");
            }
        }
        return list.toString();
    }
}
