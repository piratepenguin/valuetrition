package model;

public class Meal {

    Food food;
    double amount;
    int day;

    String name;
    double weight;
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
        weight = food.getWeight();
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
        return cost * (amount / weight);
    }

    public double getCalories() {
        return calories * (amount / weight);
    }

    public double getCarbs() {
        return carbs * (amount / weight);
    }

    public double getFats() {
        return fats * (amount / weight);
    }

    public double getProteins() {
        return proteins * (amount / weight);
    }

    public String getPrimaryType() {
        return primaryType;
    }

    public double getAmount() {
        return amount;
    }

    public double getWeight() {
        return weight;
    }

    public int getDay() {
        return day;
    }

    // EDITING METHODS

    public void editCost(double price) {
        this.cost = price * weight / amount;
    }

    public void editAmount(double amount) {
        this.amount = amount;
    }

    public void editDay(int day) {
        this.day = day;
    }
}
