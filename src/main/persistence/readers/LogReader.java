package persistence.readers;

import model.food.Food;
import model.meal.Log;
import model.meal.Meal;
import model.meal.MealList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read meal data from a file
public class LogReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a Log parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static Log readLog(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings, each string
    // containing the content of one row of the file
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of meals parsed from list of strings
    // where each string contains data for one day
    private static Log parseContent(List<String> fileContent) {
        Log log = new Log();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            log.logMeal(parseDate(lineComponents), parseMeal(lineComponents));
        }

        return log;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 10
    // EFFECTS: returns an account constructed from components
    private static Meal parseMeal(List<String> components) {
        int day = Integer.parseInt(components.get(0));
        String name = (components.get(1));
        double foodWeight = Double.parseDouble(components.get(2));
        double foodCost = Double.parseDouble(components.get(3));
        double foodCalories = Double.parseDouble(components.get(4));
        double foodCarbs = Double.parseDouble(components.get(5));
        double foodFats = Double.parseDouble(components.get(6));
        double foodProteins = Double.parseDouble(components.get(7));
        double weight = Double.parseDouble(components.get(8));
        Food food = new Food(name, foodWeight, foodCost, foodCalories, foodCarbs, foodFats, foodProteins);
        return new Meal(food, weight, day);
    }

    private static int parseDate(List<String> components) {
        return Integer.parseInt(components.get(0));
    }

}