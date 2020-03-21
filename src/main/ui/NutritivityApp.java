package ui;

import model.exceptions.FoodNotFoundException;
import model.exceptions.InvalidUserChoiceException;
import model.food.Food;
import model.food.FoodList;
import model.meal.Log;
import model.meal.Meal;
import model.meal.MealList;
import persistence.readers.*;
import persistence.writers.Writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class NutritivityApp {

//    private static final String MEALS_FILE = "./data/log.txt";
//    private static final String FOODS_FILE = "./data/foods.txt";
//    Scanner keyboard = new Scanner(System.in);
//    FoodList database;
//    Log log;
//    boolean running = true;
//    boolean testing = false;
//    int today = 1;
//
//    //  FOR TESTING
//    Food banana = new Food("banana", 1, 0.7, 100, 24, 0, 1);
//    Food rice = new Food("rice", 1, 5.0, 3000, 700, 11, 25);
//    Food buckwheat = new Food("buckwheat", 1, 0.7, 100, 24, 0, 1);
//    Food apple = new Food("apple", 1, 0.7, 100, 24, 0, 1);
//    Food milk = new Food("milk", 1, 5, 1500, 250, 40, 15);
//    Food emptyFood = new Food("unnamed food", 1, 0, 0, 0, 0, 0);
//
//
//    public NutritivityApp() {
////        database.add(banana);
////        database.add(rice);
////        database.add(buckwheat);
////        database.add(apple);
////        database.add(milk);
////        database.add(emptyFood);
//        runApplication();
//    }
//
//    public NutritivityApp(String str) {     // MADE EXPLICITLY FOR TESTING PURPOSES
//        testing = true;
//        initFoods();
//        initMeals();
//        database.add(banana);
//        database.add(rice);
//        database.add(buckwheat);
//        database.add(apple);
//        database.add(milk);
//        database.add(emptyFood);
//    }
//
//    // EFFECTS: runs the application
//    public void runApplication() {
//        String choice;
//        if (!testing) {
//            load();
//        }
//
//        while (running) {
//
//            showMenu();
//            choice = keyboard.nextLine().toLowerCase();
//
//            if (choice.equals("exit")) {
//                running = false;
//                print("see ya!");
//            } else {
//                try {
//                    foodAction(choice);
//                } catch (InvalidUserChoiceException e) {
//                    System.out.println("please choose from the available 7 actions");
//                }
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads log from ACCOUNTS_FILE, if that file exists;
//    // otherwise initializes accounts with default values
//    private void load() {
//        try {
//            log = LogReader.readLog(new File(MEALS_FILE));
//        } catch (IOException e) {
//            print("no meal save file found; starting from scratch");
//            initMeals();
//        }
//        try {
//            database = FoodReader.readFoods(new File(FOODS_FILE));
//        } catch (IOException e) {
//            print("no food save file found; starting from scratch");
//            initFoods();
//        }
//    }
//
//    public void initFoods() {
//        database = new FoodList();
//    }
//
//    public void initMeals() {
//        log = new Log();
//        today = 1;
//    }
//
//    public void showMenu() {
//        System.out.println("               Menu \n ===================================\n"
//                + "Enter a new food        - 'new'\nRemove a food           - 'remove'\n"
//                + "View a food's info      - 'info'\nEdit a food's info      - 'edit'\n"
//                + "View list of all foods  - 'list'\nAdd food to log         - 'add'\n"
//                + "Remove meal from log    - 'remove meal'\nClear log               - 'clear'\n"
//                + "View log                - 'log'\nFinish today's log      - 'finish'\n"
//                + "Save                    - 'save'\nExit                    - 'exit'");
//    }
//
////    public void foodAction(String choice) throws InvalidUserChoiceException {
////        switch (choice) {
////            case "new": newFood();
////                break;
////            case "remove": removeFood();
////                break;
////            case "info": info();
////                break;
////            case "edit": getReadyForEdit();
////                break;
////            case "list": list();
////                break;
////            case "add": addToLog();
////                break;
////            case "log": log();
////                break;
////            case "finish": newDay();
////                break;
////            case "exit":
////                running = false;
////                break;
////            default:
////                secondaryOptions(choice);
////        }
////    }
//
////    public void secondaryOptions(String choice) throws InvalidUserChoiceException {
////        switch (choice) {
////            case "remove meal":
////                removeMeal();
////                break;
//////            case "clear":
//////                log.clear();
//////                break;
////            case "save": save();
////                break;
////            default:
////                throw new InvalidUserChoiceException();
////        }
////    }
//
////    public void removeMeal() throws InvalidUserChoiceException {
////        System.out.println("Please enter the name of the meal you want to remove: ");
////        String choice = keyboard.nextLine();
////        System.out.println("Please enter the day it is logged on: ");
////        int day = keyboard.nextInt();
////        keyboard.nextLine();
////        try {
////            log.removeMeal(choice, day);
////            print("\nmeal " + choice + " on day " + day + " has been removed! \n");
////        } catch (FoodNotFoundException ex) {
////            errorMessage("meal");
////            choice = keyboard.nextLine();
////            switch (choice) {
////                case "1":
////                    removeMeal();
////                    break;
////                case "2":
////                    break;
////                default:
////                    throw new InvalidUserChoiceException();
////            }
////        }
////    }
//
//    public void newFood() {
//        System.out.println("Please enter the foods name: ");
//        String name = keyboard.nextLine();
//        System.out.println("Please enter the foods weight: ");
//        double weight = keyboard.nextDouble();
//        System.out.println("Please enter the foods calories: ");
//        double calories = keyboard.nextDouble();
//        System.out.println("Please enter the foods carbs: ");
//        double carbs = keyboard.nextDouble();
//        System.out.println("Please enter the foods fats: ");
//        double fats = keyboard.nextDouble();
//        System.out.println("Please enter the foods protein: ");
//        double proteins = keyboard.nextDouble();
//        System.out.println("Please enter the foods cost in dollars: ");
//        double cost = keyboard.nextDouble();
//        keyboard.nextLine();
//        Food food = new Food(name, weight, cost, calories, carbs, fats, proteins);
//        database.add(food);
//        System.out.println("\nsweet! food " + name + " has been created\n");
//    }
//
//    public void removeFood() throws InvalidUserChoiceException {
//        System.out.println("Please enter the name of the food you want to remove: ");
//        String choice = keyboard.nextLine();
//        try {
//            database.removeFood(choice);
//            print("\nfood " + choice + " has been removed! \n");
//        } catch (FoodNotFoundException ex) {
//            errorMessage("Food");
//            choice = keyboard.next();
//            keyboard.nextLine();
//            switch (choice) {
//                case "1":
//                    foodAction("remove");
//                    break;
//                case "2":
//                    break;
//                default:
//                    throw new InvalidUserChoiceException();
//            }
//        }
//    }
//
//    public void info() {
//        print("enter the name of the food: ");
//        String foodName = keyboard.nextLine();
//        try {
//            print(database.getFood(foodName).viewInfo());
//        } catch (FoodNotFoundException ex) {
//            errorMessage("Food");
//            String choice = keyboard.next();
//            keyboard.nextLine();
//            switch (choice) {
//                case "1":
//                    info();
//                case "2":
//                    break;
//            }
//        }
//    }
//
//    public void getReadyForEdit() {
//        print("Enter the food's name");
//        String name = keyboard.nextLine();
//        try {
//            Food food = database.getFood(name);
//            print("what information about the food would you like to change?\n"
//                    + "Choose from:   'name'   'weight'   'cost'   'calories'   'macros'");
//            String info = keyboard.nextLine().toLowerCase();
//            edit(food, info);
//        } catch (FoodNotFoundException ex) {
//            errorMessage("Food");
//            String choice = keyboard.next();
//            keyboard.nextLine();
//            switch (choice) {
//                case "1":
//                    getReadyForEdit();
//                case "2":
//                    break;
//            }
//        }
//    }
//
//    public void edit(Food food, String choice) {
//        switch (choice) {
//            case ("name"):
//                editName(food);
//                break;
//            case ("cost"):
//                editCost(food);
//                break;
//            case ("weight"):
//                editWeight(food);
//                break;
//            case ("calories"):
//                editCalories(food);
//                break;
//            case ("macros"):
//                editMacros(food);
//                break;
//        }
//    }
//
//    public void editCost(Food food) {
//        print("enter a new cost:");
//        double cost = keyboard.nextDouble();
//        keyboard.nextLine();
//        food.editCost(cost);
//        print("Food cost successfully changed to " + cost);
//    }
//
//    public void editName(Food food) {
//        print("enter a new name:");
//        String name = keyboard.nextLine();
//        food.editName(name);
//        print("Food name successfully changed to " + name);
//    }
//
//    public void editWeight(Food food) {
//        print("enter a new weight:");
//        double weight = keyboard.nextDouble();
//        keyboard.nextLine();
//        food.editWeight(weight);
//        print("Food weight successfully changed to " + weight);
//    }
//
//    public void editCalories(Food food) {
//        print("enter new calorie amount:");
//        double calories = keyboard.nextDouble();
//        keyboard.nextLine();
//        food.editCalories(calories);
//        print("Food calories successfully changed to " + calories);
//    }
//
//    public void editMacros(Food food) {
//        print("enter either new values or leave blank to remain unchanged");
//        print("enter new carb amount (g):");
//        String carbs = keyboard.nextLine();
//        print("enter new fat amount (g):");
//        String fats = keyboard.nextLine();
//        print("enter new protein amount (g):");
//        String proteins = keyboard.nextLine();
//        if (!carbs.equals("")) {
//            food.editCarbs(Double.parseDouble(carbs));
//        }
//        if (!fats.equals("")) {
//            food.editFats(Double.parseDouble(fats));
//        }
//        if (!proteins.equals("")) {
//            food.editProteins(Double.parseDouble(proteins));
//        }
//        if (!carbs.equals("") || !fats.equals("") || !proteins.equals("")) {
//            print("change successful!");
//        }
//        if (carbs.equals("") && fats.equals("") && proteins.equals("")) {
//            print("nothing has been changed, good one.");
//        }
//    }
//
//    public void list() {
//        print(database.toString());
//    }
//
////    public void addToLog() {
////        Meal meal;
////        print("enter the food's name: ");
////        keyboard.nextLine();                         // nextLine doesn't work properly unless this is put beforehand
////        String name = keyboard.nextLine();
////        try {
////            Food food = database.getFood(name);
////            print("enter the amount (g): ");
////            double amount = keyboard.nextDouble();
////            print("enter the day: ");
////            int day = keyboard.nextInt();
////            meal = new Meal(food, amount, day);
////            log.add(meal);
////            print("Successfully added meal " + name + " to log!\n");
////        } catch (FoodNotFoundException ex) {
////            errorMessage("Food");
////            String choice = keyboard.next();
////            switch (choice) {
////                case "1":
////                    addToLog();
////                case "2":
////                    break;
////            }
////        }
////
////    }
//
//    public void log() {
//        print(log.toString());
//    }
//
//    public void newDay() {
//        today++;
//    }
//
//    // EFFECTS: saves log and food database
//    private void save() {
//        saveFoods();
//        saveMeals();
//    }
//
//    // EFFECTS: saves food database
//    private void saveFoods() {
//        try {
//            Writer writer = new Writer(new File(FOODS_FILE));
//            writer.write(database);
//            writer.close();
//            System.out.println("Food database saved to file " + FOODS_FILE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save foods to " + FOODS_FILE);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            // this is due to a programming error
//        }
//    }
//
//    // EFFECTS: saves log
//    private void saveMeals() {
//        try {
//            Writer writer = new Writer(new File(MEALS_FILE));
//            writer.write(log);
//            writer.close();
//            System.out.println("Log saved to file " + MEALS_FILE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to save foods to " + MEALS_FILE);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            // this is due to a programming error
//        }
//    }
//
//    public void print(String str) {
//        System.out.println(str);
//    }
//
//
//    public void errorMessage(String item) {
//        print(item + " not found\n'1' - try again\n'2' - back to main menu");
//    }
}
