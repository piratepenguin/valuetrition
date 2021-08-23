package model.meal;

import model.exceptions.FoodNotFoundException;
import persistence.Saveable;
import persistence.readers.LogReader;

import java.io.PrintWriter;
import java.util.*;

// represents a list of Meal items
// - intended for representing a list of all meals eaten on a given day

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
    public void removeMeal(String foodname, String day) throws FoodNotFoundException {
        int size = mealList.size();
        mealList.removeIf(meal -> (meal.getName().equals(foodname) && meal.getDate().equals(day)));
        if (mealList.size() == size) {
            throw new FoodNotFoundException();
        }
    }

    public void add(Meal meal) {
        mealList.add(meal);
    }

    // EFFECTS: returns Meal with given name and day from list,
    //          if not found returns FoodNotFoundException
    public Meal getMeal(String name, String day) throws FoodNotFoundException {
        for (Meal meal: mealList) {
            if (meal.getName().equals(name) && meal.getDate().equals(day)) {
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




    // REMOVE OR UPGRADE !!!
    // REQUIRES: meals should already be sorted increasingly by day
    // EFFECTS: returns a string of all the foods contained in the list
    public String toString() {
        StringBuilder list = new StringBuilder();
        String currentDay = "JAN002021";
        for (Meal each : mealList) {
            if (mealList.indexOf(each) == mealList.size() - 1) { // if last food of day
                if (each.getDate() != currentDay) {
                    list.append("\n| Day ").append(each.getDate()).append("| ");
                }
                list.append(each.getName()).append(" - ");
                list.append(each.getRoundedCalories()).append("Cal");
            } else if (mealList.indexOf(each) == 0) {
                list.append("| Day ").append(each.getDate()).append("| ");
                currentDay = each.getDate();
                checkStyleMethodLengthRuleIsABitch(list, each);
            } else {
                if (each.getDate() != currentDay) {
                    list.append("\n| Day ").append(each.getDate()).append("| ");
                    currentDay = each.getDate();
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
            printWriter.print(meal.getDate());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getName());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodWeight());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodCost());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodCalories());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodCarbs());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodFats());
            printWriter.print(LogReader.DELIMITER);
            printWriter.print(meal.getFoodProteins());
            printWriter.print(LogReader.DELIMITER);
            printWriter.println(meal.getWeight());
        }
    }
}
