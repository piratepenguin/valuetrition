package persistence.readers;

import model.Food;
import model.Meal;
import model.MealList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read meal data from a file
public class MealReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of meals parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static MealList readMeals(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of meals parsed from list of strings
    // where each string contains data for one meal
    private static MealList parseContent(List<String> fileContent) {
        MealList meals = new MealList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            meals.add(parseMeal(lineComponents));
        }

        return meals;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 10
    // EFFECTS: returns an account constructed from components
    private static Meal parseMeal(List<String> components) {
        String name = (components.get(0));
        double weight = Double.parseDouble(components.get(1));
        double cost = Double.parseDouble(components.get(2));
        double calories = Double.parseDouble(components.get(3));
        double carbs = Double.parseDouble(components.get(4));
        double fats = Double.parseDouble(components.get(5));
        double proteins = Double.parseDouble(components.get(6));
        double amount = Double.parseDouble(components.get(7));
        int day = Integer.parseInt(components.get(8));
        Food food = new Food(name, weight, cost, calories, carbs, fats, proteins);
        return new Meal(food, amount, day);
    }
}