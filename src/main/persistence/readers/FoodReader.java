package persistence.readers;

import model.Food;
import model.FoodList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// A reader that can read food data from a file
public class FoodReader {
    public static final String DELIMITER = ",";

    // EFFECTS: returns a list of foods parsed from file; throws
    // IOException if an exception is raised when opening / reading from file
    public static FoodList readFoods(File file) throws IOException {
        List<String> fileContent = readFile(file);
        return parseContent(fileContent);
    }

    // EFFECTS: returns content of file as a list of strings
    //          each string = one line of text
    private static List<String> readFile(File file) throws IOException {
        return Files.readAllLines(file.toPath());
    }

    // EFFECTS: returns a list of foods parsed from list of strings
    // where each string contains data for one food
    private static FoodList parseContent(List<String> fileContent) {
        FoodList foods = new FoodList();

        for (String line : fileContent) {
            ArrayList<String> lineComponents = splitString(line);
            foods.add(parseFood(lineComponents));
        }

        return foods;
    }

    // EFFECTS: returns a list of strings obtained by splitting line on DELIMITER
    private static ArrayList<String> splitString(String line) {
        String[] splits = line.split(DELIMITER);
        return new ArrayList<>(Arrays.asList(splits));
    }

    // REQUIRES: components has size 7
    // EFFECTS: returns a Food constructed from components
    private static Food parseFood(List<String> components) {
        String name = (components.get(0));
        double weight = Double.parseDouble(components.get(1));
        double cost = Double.parseDouble(components.get(2));
        double calories = Double.parseDouble(components.get(3));
        double carbs = Double.parseDouble(components.get(4));
        double fats = Double.parseDouble(components.get(5));
        double proteins = Double.parseDouble(components.get(6));
        return new Food(name, weight, cost, calories, carbs, fats, proteins);

    }
}