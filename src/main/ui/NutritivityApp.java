package ui;

import model.Food;
import model.FoodList;
import model.FoodNotFoundException;
import model.InvalidUserChoiceException;

import java.util.Scanner;

public class NutritivityApp {

    Scanner keyboard = new Scanner(System.in);
    FoodList database = new FoodList();
    FoodList log = new FoodList();
    boolean running = true;

    //  FOR TESTING
    Food banana = new Food("banana", 0.7, 100, 24, 0, 1);
    Food rice = new Food("rice", 5.0, 3000, 700, 11, 25);
    Food buckwheat = new Food("buckwheat", 0.7, 100, 24, 0, 1);
    Food apple = new Food("apple", 0.7, 100, 24, 0, 1);
    Food milk = new Food("milk", 5, 1500, 250, 40, 15);
    Food emptyFood = new Food("unnamed food", 0, 0, 0, 0, 0);


    public NutritivityApp() {
        database.add(banana);
        database.add(rice);
        database.add(buckwheat);
        database.add(apple);
        database.add(milk);
        database.add(emptyFood);
        runApplication();
    }

    public NutritivityApp(String str) {     // MADE EXPLICITLY FOR TESTING PURPOSES
        database.add(banana);
        database.add(rice);
        database.add(buckwheat);
        database.add(apple);
        database.add(milk);
        database.add(emptyFood);
    }

    public void runApplication() {
        String choice;
        while (running) {

            showMenu();
            choice = keyboard.next();

            if (choice.equals("exit")) {
                running = false;
            } else {
                try {
                    foodAction(choice);
                } catch (InvalidUserChoiceException e) {
                    System.out.println("please choose from the available 7 actions");
                }
            }
        }
    }

    public void showMenu() {
        System.out.println("               Menu \n ===================================\n"
                + "Enter a new food        - 'new'\nRemove a food           - 'remove'\n"
                + "View a food's info      - 'info'\nEdit a food's name      - 'edit'\n"
                + "View list of all foods  - 'list'\nAdd food to log         - 'add'\n"
                + "View log                - 'log'\nFinish today's log      - 'finish'"
                + "Exit                    - 'exit'");
    }

    public void foodAction(String choice) throws InvalidUserChoiceException {
        switch (choice) {
            case "new": newFood();
                break;
            case "remove": removeFood();
                break;
            case "info": info();
                break;
            case "edit": edit();
                break;
            case "list": list();
                break;
            case "add": addToLog();
                break;
            case "log": log();
                break;
            case "finish": newDay();
                break;
            case "exit": running = false;
                break;
            default:
                throw new InvalidUserChoiceException();
        }
    }

    public void newFood() {
        System.out.println("Please enter the foods name: ");
        keyboard.nextLine();
        String name = keyboard.nextLine();
        System.out.println("Please enter the foods calories: ");
        double calories = keyboard.nextDouble();
        System.out.println("Please enter the foods carbs: ");
        short carbs = keyboard.nextShort();
        System.out.println("Please enter the foods protein: ");
        short proteins = keyboard.nextShort();
        System.out.println("Please enter the foods fats: ");
        short fats = keyboard.nextShort();
        System.out.println("Please enter the foods cost in dollars: ");
        double cost = keyboard.nextDouble();
        Food food = new Food(name, cost, calories, carbs, fats, proteins);
        database.add(food);
        System.out.println("\nsweet! food " + name + " has been created\n");
    }

    public void removeFood() throws InvalidUserChoiceException {
        System.out.println("Please enter the name of the food you want to remove: ");
        keyboard.nextLine();
        String choice = keyboard.nextLine();
        print("choice is: " + choice + " tada\n");
        try {
            database.removeFood(choice);
            print("\nfood " + choice + " has been removed! \n");
        } catch (FoodNotFoundException ex) {
            errorMessage();
            choice = keyboard.next();
            switch (choice) {
                case "1":
                    foodAction("remove");
                    break;
                case "2":
                    break;
                default:
                    throw new InvalidUserChoiceException();
            }
        }
    }

    public void info() {
        print("enter the name of the food: ");
        keyboard.nextLine();
        String foodName = keyboard.nextLine();
        try {
            print(database.getFood(foodName).viewInfo());
        } catch (FoodNotFoundException ex) {
            errorMessage();
            String choice = keyboard.next();
            switch (choice) {
                case "1":
                    info();
                case "2":
                    break;
            }
        }
    }

    public void edit() {
        print("enter the food's current name: \n");
        keyboard.nextLine();
        String name = keyboard.nextLine();
        try {
            Food food = database.getFood(name);
            print("enter a new name:");
            keyboard.nextLine();
            name = keyboard.nextLine();
            food.editName(name);
            print("Food name successfully changed to " + name);
        } catch (FoodNotFoundException ex) {
            errorMessage();
            String choice = keyboard.next();
            switch (choice) {
                case "1":
                    edit();
                case "2":
                    break;
            }
        }
    }

    public void list() {
        print(database.toString());
    }

    public void addToLog() {
        Food food;
        print("enter the food's name: ");
        keyboard.nextLine();
        String name = keyboard.nextLine();
        try {
            food = database.getFood(name);
            log.add(food);
            print("Successfully added food " + name + " to log!\n");
        } catch (FoodNotFoundException ex) {
            errorMessage();
            String choice = keyboard.next();
            switch (choice) {
                case "1":
                    addToLog();
                case "2":
                    break;
            }
        }

    }

    public void log() {
        print(log.toString());
    }

    public void newDay() {
        print(log.toString());
    }


    public void print(String str) {
        System.out.println(str);
    }


    public void errorMessage() {
        print("Food not found\n'1' - try again\n'2' - back to main menu");
    }
}
