package ui;

import model.Food;
import model.FoodList;
import model.InvalidUserChoiceException;

import java.util.Scanner;

public class NutritivityApp {


    Scanner keyboard = new Scanner(System.in);
    FoodList foodlist = new FoodList();

    public NutritivityApp() {
        runApplication();
    }

    public void runApplication() {
        boolean running = true;
        String choice;
        while (running) {

            showOptions();
            choice = keyboard.next();
            // optional:  choice = choice.toLowerCase();

            if (choice.equals("exit")) {
                running = false;
            } else {
                try {
                    foodAction(choice);
                } catch (InvalidUserChoiceException e) {
                    System.out.println("please choose from the available 4 actions");
                }
            }
        }
    }


    public void showOptions() {
        System.out.println("Enter a new food - 'add' \nRemove a food - 'remove'\n"
                + "View the list of all foods, type 'list'\nAdd food to the log - 'add to log'\n"
                + "View log - 'log'");
    }


    public void addFood() {

        System.out.println("Please enter the foods name: ");
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
        short cost = keyboard.nextShort();
        Food food = new Food(name, cost, calories, carbs, fats, proteins);
        System.out.println("sweet! food " + name + " has been created");

    }




    public void foodAction(String choice) throws InvalidUserChoiceException {

        if (choice.equals("add")) {
            addFood();
        } else if (choice.equals("remove")) {
            System.out.println("Please enter the name of the food you want to remove");
            choice = keyboard.next();
            foodlist.removeFood(choice);
        } else {
            throw new InvalidUserChoiceException();
        }

    }
}
