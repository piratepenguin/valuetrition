package model.meal;

import model.food.Food;


// represents a meal, which consists of:
//  - the food being eaten
//  - how much of that food is being eaten (weight)
//  - the day it is being eaten on

public class Meal {

    Food food;
    int day;

    String name;
    double weight;
    double cost;
    double calories;
    double carbs;
    double fats;
    double proteins;
    String primaryType;

    double foodWeight;
    double foodCost;
    double foodCalories;
    double foodCarbs;
    double foodFats;
    double foodProteins;

    public Meal(Food food, double weight, int day) {
        this.food = food;
        this.weight = weight;
        this.day = day;

        foodWeight = food.getWeight();
        foodCost = food.getCost();
        foodCalories = food.getCalories();
        foodCarbs = food.getCarbs();
        foodFats = food.getFats();
        foodProteins = food.getProteins();

        name = food.getName();
        cost = food.getCost() * (weight / foodWeight);
        calories = food.getCalories() * (weight / foodWeight);
        carbs = food.getCarbs() * (weight / foodWeight);
        fats = food.getFats() * (weight / foodWeight);
        proteins = food.getProteins() * (weight / foodWeight);
        primaryType = food.getPrimaryType();



    }

    public Meal(int ignored, Food food, double weight, int day) {
        this.food = food;
        this.weight = weight;
        this.day = day;
        name = food.getName();
        foodWeight = food.getWeight();
        cost = food.getCost();
        calories = food.getCalories();
        carbs = food.getCarbs();
        fats = food.getFats();
        proteins = food.getProteins();
        primaryType = food.getPrimaryType();
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public double getCalories() {
        return calories;
    }

    public int getRoundedCalories() {
        return (int) calories;
    }

    public double getCarbs() {
        return carbs;
    }

    public double getFats() {
        return fats;
    }

    public double getProteins() {
        return proteins;
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public double getWeight() {
        return weight;
    }

    public int getDay() {
        return day;
    }


    // FOR FOODS NOT MEAL

    public double getFoodCost() {
        return foodCost;
    }

    public double getFoodCalories() {
        return foodCalories;
    }

    public double getFoodCarbs() {
        return foodCarbs;
    }

    public double getFoodFats() {
        return foodFats;
    }

    public double getFoodProteins() {
        return foodProteins;
    }

    public double getFoodWeight() {
        return foodWeight;
    }








    // EDITING METHODS

    public void editCost(double newCost) {
        this.cost = newCost;
    }

    public void editAmount(double newAmount) {
        refactor(newAmount / weight);
        this.weight = newAmount;
    }

    public void refactor(double scale) {
        cost = cost * scale;
        calories = calories * scale;
        carbs = carbs * scale;
        fats = fats * scale;
        proteins = proteins * scale;
    }

    public void editDay(int day) {
        this.day = day;
    }
}
