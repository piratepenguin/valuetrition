package model;

public class Meal {

    Food food;
    double amount;
    int day;

    String name;
    double foodWeight;
    double cost;
    double calories;
    double carbs;
    double fats;
    double proteins;
    String primaryType;

    public Meal(Food food, double amount, int day) {
        this.food = food;
        this.amount = amount;
        this.day = day;
        name = food.getName();
        foodWeight = food.getWeight();
        cost = food.getCost() * (amount / foodWeight);
        calories = food.getCalories() * (amount / foodWeight);
        carbs = food.getCarbs() * (amount / foodWeight);
        fats = food.getFats() * (amount / foodWeight);
        proteins = food.getProteins() * (amount / foodWeight);
        primaryType = food.getPrimaryType();
    }

    public Meal(String name, double weight, double cost, double calories, double carbs, double fats, double proteins, double amount, int day) {
        this.food = new Food(name, weight, cost, calories, carbs, fats, proteins);
        this.amount = amount;
        this.day = day;
        this.name = name;
        this.foodWeight = weight;
        this.cost = cost;
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
        this.primaryType = food.getPrimaryType();
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

    public double getAmount() {
        return amount;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public int getDay() {
        return day;
    }

    // EDITING METHODS

    public void editCost(double newCost) {
        this.cost = newCost;
    }

    public void editAmount(double newAmount) {
        refactor(newAmount / amount);
        this.amount = newAmount;
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
