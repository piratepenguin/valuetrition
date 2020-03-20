package model;

import model.Food;
import model.Meal;
import persistence.Saveable;
import persistence.readers.FoodReader;

import java.io.PrintWriter;
import java.util.*;

// represents a list of Food items
public class MealList implements Saveable {
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
    public void removeMeal(String foodname, int day) throws FoodNotFoundException {
        int size = mealList.size();
        mealList.removeIf(meal -> (meal.getName().equals(foodname) && meal.getDay() == day));
        if (mealList.size() == size) {
            throw new FoodNotFoundException();
        }
    }

    public void add(Meal meal) {
        mealList.add(meal);
    }

    // EFFECTS: returns Meal with given name and day from list,
    //          if not found returns FoodNotFoundException
    public Meal getMeal(String name, int day) throws FoodNotFoundException {
        for (Meal meal: mealList) {
            if (meal.getName().equals(name) && meal.getDay() == day) {
                return meal;
            }
        }
        throw new FoodNotFoundException();

    }

    // EFFECTS: returns meal at given index in MealList
    public Meal get(int index) {
        return mealList.get(index);
    }

    // EFFECTS: returns last meal in MealList
    public Meal getLast() {
        return mealList.get(mealList.size() - 1);
    }

    // REQUIRES: meals should already be sorted increasingly by day
    // EFFECTS: returns a string of all the foods contained in the list
    public String toString() {
        StringBuilder list = new StringBuilder();
        int currentDay = 0;
        for (Meal each : mealList) {
            if (mealList.indexOf(each) == mealList.size() - 1) {
                if (each.getDay() != currentDay) {
                    list.append("\n| Day ").append(each.getDay()).append("| ");
                    currentDay = each.getDay();
                }
                list.append(each.getName()).append(" - ");
                list.append(each.getRoundedCalories()).append("Cal");
            } else if (mealList.indexOf(each) == 0) {
                list.append("| Day ").append(each.getDay()).append("| ");
                currentDay = each.getDay();
                checkStyleMethodLengthRuleIsABitch(list, each);
            } else {
                if (each.getDay() != currentDay) {
                    list.append("\n| Day ").append(each.getDay()).append("| ");
                    currentDay = each.getDay();
                }
                checkStyleMethodLengthRuleIsABitch(list, each);
            }
        }
        return list.toString();
    }

    // my condolences
    static void checkStyleMethodLengthRuleIsABitch(StringBuilder list, Meal each) {
        list.append(each.getName()).append(" - ");
        list.append(each.getRoundedCalories()).append("Cal, ");
    }


    // for printing to file
    @Override
    public void save(PrintWriter printWriter) {
        for (Meal meal : mealList) {
            printWriter.print(meal.getName());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getFoodWeight());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getCost());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getCalories());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getCarbs());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getFats());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getProteins());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.print(meal.getAmount());
            printWriter.print(FoodReader.DELIMITER);
            printWriter.println(meal.getDay());
        }
    }
}
